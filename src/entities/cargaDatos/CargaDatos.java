package entities.cargaDatos;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import entities.*;
import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CargaDatos {

    private static final OpenHashTable<Integer, Pelicula> peliculas = new OpenHashTable<>(45587);
    private static final OpenHashTable<Integer, Coleccion> colecciones = new OpenHashTable<>(45587);
    private static final OpenHashTable<Integer, Usuario> usuarios = new OpenHashTable<>(170000);
    private static final OpenHashTable<Integer, Director> directores = new OpenHashTable<>(32768);
    private static final OpenHashTable<Integer, Actor> actores = new OpenHashTable<>(250000);
    private static final ArrayList<Calificacion> calificaciones = new ArrayList<>(5000000);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static DataResult cargaDatos() {
        cargaPelis();
        cargaReviews();
        cargaCreditos();
        DataResult res = new DataResult();
        res.setPeliculas(peliculas);
        res.setColecciones(colecciones);
        res.setUsuarios(usuarios);
        res.setCalificaciones(calificaciones);
        res.setDirectores(directores);
        res.setActores(actores);
        return res;
    }

    private static void cargaPelis(){
        String csvFilePath = "src/data/movies_metadata.csv";
        int processedLines = 0;
        long startTime = System.currentTimeMillis();
        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(csvFilePath)))) {
            String[] nextLine;
            reader.skip(1);
            while ((nextLine = reader.readNext()) != null) {
                processedLines++;
                // Process the line
                //Verifica si la linea tiene el formato correcto, por ahora si no lo tienen paso a la próxima
                if (!nextLine[0].equals("FALSE") && !nextLine[0].equals("TRUE")) {
                    continue;
                }
                if (nextLine.length == 19) {
                    int id = 0;
                    try {
                        id = Integer.parseInt(nextLine[5]);
                    }catch (NumberFormatException e){
                        System.err.println(e.getMessage());
                    }

                    String nombre = nextLine[18];
                    if (nextLine[12].isEmpty()){continue;}
                    LocalDate fechaEstreno = LocalDate.parse(nextLine[12], DATE_FORMATTER);
                    String idiomaOriginal = nextLine[7];
                    ArrayList<String> generos = new ArrayList<>();
                    String genreJsonString = nextLine[3];
                    if (genreJsonString != null && !genreJsonString.isEmpty()) {
                        String validJsonString = repairPythonDictLikeString(genreJsonString);
                        try {
                            JsonNode rootNode = OBJECT_MAPPER.readTree(validJsonString);
                            if (rootNode.isArray()) {
                                for (JsonNode node : rootNode) {
                                    if (node.has("name")) {
                                        generos.add(node.get("name").asText());
                                    }
                                }
                            }
                        } catch (Exception jsonE) {
                            System.err.println("JSON parsing error for genres on line " + processedLines + ": " + jsonE.getMessage());
                        }
                    }
                    long ingreso = 0;
                    try {
                        ingreso = Long.parseLong(nextLine[13]);
                    }catch (Exception ignored){
                        continue;
                    }

                    Pelicula p = new Pelicula(id, nombre, fechaEstreno, idiomaOriginal, generos, ingreso);
                    try {
                        peliculas.put(id, p);
                    }catch (Exception e){
                        continue;
                    }

                    String collectionJsonString = nextLine[1]; // Get collection string
                    if (collectionJsonString != null && !collectionJsonString.isEmpty()) {
                        String validCollectionJson = repairPythonDictLikeString(collectionJsonString);
                        try {
                            JsonNode collectionNode = OBJECT_MAPPER.readTree(validCollectionJson);

                            // Collections can sometimes be 'null' in the data or empty JSON
                            if (collectionNode != null && collectionNode.isObject() && collectionNode.has("id") && collectionNode.has("name")) {
                                int colId = collectionNode.get("id").asInt();
                                String colName = collectionNode.get("name").asText();

                                // Get or create the Collection object from the map
                                Coleccion col = colecciones.get(colId);
                                if (col == null) {
                                    col = new Coleccion(colId, colName);
                                    colecciones.put(colId, col);
                                }

                                // Add the current movie to this collection's list
                                col.addPeliculaToCollection(id);
                            }
                        } catch (Exception collectionJsonE) {
                            System.err.println("JSON parsing error for collection on line " + processedLines + " in " + csvFilePath + ": " + collectionJsonE.getMessage());
                        }
                    }else {
                        Coleccion c = new Coleccion(id, nombre);
                        colecciones.put(id, c);
                    }


                }

            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    private static void cargaReviews(){
        String csvFilePath = "src/data/ratings_1mm.csv";
        long processedLines = 0;

        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(csvFilePath)))) {

            String[] line;
            while ((line = reader.readNext()) != null) {
                processedLines++;

                // Basic validation: ensure enough columns
                if (line.length < 4) { // userId, movieId, rating, timestamp
                    System.err.println("Skipping malformed ratings line " + processedLines + ": Not enough columns. Line: " + String.join(",", line));
                    continue;
                }

                try {
                    int userId = Integer.parseInt(line[0]);
                    int movieId = Integer.parseInt(line[1]);
                    float ratingValue = Float.parseFloat(line[2]);
                    Instant i = Instant.ofEpochSecond(Long.parseLong(line[3]));
                    LocalDate ratingDate = i.atZone(ZoneId.systemDefault()).toLocalDate();

                    // --- Create Rating object ---
                    Calificacion cal = new Calificacion(userId, movieId, ratingValue, ratingDate);
                    calificaciones.add(cal); // Add to global list of all ratings
//                    System.out.println(calificaciones);

                    // --- Update User object ---
                    Usuario user = usuarios.get(userId);
                    if (user == null) {
                        user = new Usuario(userId);
                        usuarios.put(userId, user);
                    }
                    user.addCalificacion(cal);

                    // --- Update Movie object ---
                    Pelicula pelicula = peliculas.get(movieId);
                    if (pelicula == null) {
                    } else{
                        pelicula.addNumRating();
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Data parsing error on ratings line " + processedLines + ": " + e.getMessage() + " - Line: " + String.join(",", line));
                }
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
            System.err.println("Error reading ratings CSV file " + csvFilePath + ": " + e.getMessage());
        }
    }

    private static void cargaCreditos(){
        String csvFilePath = "src/data/credits.csv";
        long processedLines = 0;

        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(csvFilePath)))) {

            String[] line;
            reader.skip(1);
            while ((line = reader.readNext()) != null) {
                processedLines++;

                if (line.length < 3) {
                    System.err.println("Skipping malformed credits line " + processedLines + ": Not enough columns. Line: " + String.join(",", line));
                    continue;
                }

                try {
                    int movieId = Integer.parseInt(line[2]);
                    Pelicula movie = peliculas.get(movieId);
                    if (movie == null){
                        continue;
                    }

                    String actorsJsonString = line[0]; // Get actors string
                    if (actorsJsonString != null && !actorsJsonString.isEmpty()) {
                        try {
                            ArrayList<Actor> parsedActors = extractActorsFromString(actorsJsonString);
                            for (Actor actor : parsedActors) {
                                Actor existing = actores.get(actor.getId());
                                if (existing == null) {
                                    actores.put(actor.getId(), actor);
                                    existing = actor;
                                }
                                existing.addPelicula(movieId);
                            }
                        }catch (Exception parseE){
                            System.err.println("Parsing error for actor on line " + processedLines + ": " + parseE.getMessage());
                        }
                    }

                    String directorsJson = line[1];
                    if (directorsJson != null && !directorsJson.isEmpty() && !directorsJson.equals("[]")) {
                        try {
                            ArrayList<Director> directoresPelicula = extractDirectorFast(directorsJson);
                            if (directoresPelicula.isEmpty()) {
                                continue;
                            }
                            for (Director d : directoresPelicula) {
                                Director dir = directores.get(d.getIdDirector());
                                if (dir == null) {
                                    dir = d;
                                    directores.put(dir.getIdDirector(), dir);
                                }
                                movie.addDirector(dir.getIdDirector());
                                dir.addPelicula(movieId);
                            }
                        }catch (Exception parseE){
                            System.err.println("Parsing error for director on line " + processedLines + ": " + parseE.getMessage());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Data parsing error on credits line " + processedLines + ": " + e.getMessage() + " - Line: " + String.join(",", line));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error reading credits CSV file " + csvFilePath + ": " + e.getMessage());
        }
    }

    private static String repairPythonDictLikeString(String input) {
        String jsonInput = input;

        jsonInput = jsonInput.replace("None", "null")
                .replace("True", "true")
                .replace("False", "false");

        jsonInput = jsonInput.replaceAll("'(\\w+)':", "\"$1\":");  // Keys
        jsonInput = jsonInput.replaceAll(": '([^']*)'", ": \"$1\"");

        return jsonInput;
    }

    private static ArrayList<Actor> extractActorsFromString(String castField) {
        ArrayList<Actor> actors = new ArrayList<>();
        int idx = 0;
        int length = castField.length();

        while (idx < length) {
            int idKey = castField.indexOf("'id':", idx);
            if (idKey == -1) break;

            int idStart = castField.indexOf(":", idKey) + 1;
            int idEnd = castField.indexOf(",", idStart);
            if (idStart == 0 || idEnd == -1) break;

            int id = Integer.parseInt(castField.substring(idStart, idEnd).trim());

            int nameKey = castField.indexOf("'name':", idEnd);
            if (nameKey == -1) break;

            int nameStart = castField.indexOf("'", nameKey + 7) + 1;
            int nameEnd = nameStart;
            boolean escape = false;
            while (nameEnd < castField.length()) {
                char c = castField.charAt(nameEnd);
                if (c == '\'' && !escape) break;
                escape = (c == '\\');
                nameEnd++;
            }

            String name = castField.substring(nameStart, nameEnd);
            name = name.replace("\\\"", "\"").replace("\\'", "'");

            actors.add(new Actor(id, name));
            idx = nameEnd + 1;
        }

        return actors;
    }

    private static ArrayList<Director> extractDirectorFast(String crewStr) {
        int pos = 0;
        ArrayList<Director> directores = new ArrayList<>(10);
        while (true) {

            // Parse department
            int deptIndex = crewStr.indexOf("'department'", pos);
            if (deptIndex == -1) break;
            int deptValueStart = crewStr.indexOf("'", deptIndex + 12) + 1;
            int deptValueEnd = crewStr.indexOf("'", deptValueStart);
            String department = crewStr.substring(deptValueStart, deptValueEnd);

            if (!"Directing".equals(department)) {
                pos = deptValueEnd;
                continue;
            }

            int idIndex = crewStr.indexOf("'id'", deptValueEnd);
            if (idIndex == -1) break;

            // Parse id
            int idValueStart = crewStr.indexOf(":", idIndex) + 1;
            int idValueEnd = crewStr.indexOf(",", idValueStart);
            int id = Integer.parseInt(crewStr.substring(idValueStart, idValueEnd).trim());

            // Parse job
            int jobIndex = crewStr.indexOf("'job'", idValueEnd);
            if (jobIndex == -1) break;
            int jobValueStart = crewStr.indexOf("'", jobIndex + 5) + 1;
            int jobValueEnd = crewStr.indexOf("'", jobValueStart);
            String job = crewStr.substring(jobValueStart, jobValueEnd);

            if (!"Director".equals(job)) {
                pos = jobValueEnd;
                continue;
            }

            // Parse name
            int nameIndex = crewStr.indexOf("'name'", jobValueEnd);
            if (nameIndex == -1) break;
            int nameValueStart = crewStr.indexOf("'", nameIndex + 6) + 1;

            StringBuilder nameBuilder = new StringBuilder();
            int i = nameValueStart;
            for (i = nameValueStart; i < crewStr.length(); i++) {
                char c = crewStr.charAt(i);
                if (c == '\\' && i + 1 < crewStr.length() && crewStr.charAt(i + 1) == '\'') {
                    nameBuilder.append('\'');
                    i++;
                } else if (c == '\'') {
                    nameValueStart = i + 1;
                    break;
                } else {
                    nameBuilder.append(c);
                }
            }
            directores.add(new Director(id, nameBuilder.toString()));
            pos = i + 1;
        }
        return directores;
    }
}
