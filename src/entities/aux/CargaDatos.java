package entities.aux;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import entities.Coleccion;
import entities.Pelicula;
import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class CargaDatos {

    private static OpenHashTable<Integer, Pelicula> peliculas = new OpenHashTable<>(45587);
    private static OpenHashTable<Integer, Coleccion> colecciones = new OpenHashTable<>(45587);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static OpenHashTable[] cargaDatos() {
        return cargaPelis();

    }

    private static OpenHashTable[] cargaPelis(){
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

                    int id = Integer.parseInt(nextLine[5]);
                    String nombre = nextLine[18];
                    if (nextLine[12].isEmpty()){continue;}
                    LocalDate fechaEstreno = LocalDate.parse(nextLine[12], DATE_FORMATTER);
                    String idiomaOriginal = nextLine[7];
                    ArrayList<String> generos = new ArrayList<>();
                    String genreJsonString = nextLine[3];
                    if (genreJsonString != null && !genreJsonString.isEmpty()) {
                        // Fix single quotes to double quotes for valid JSON
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
                            // Handle error: generos will be an empty list, or set to null, etc.
                        }
                    }
                    long ingreso = 0;
                    try {
                        ingreso = Long.parseLong(nextLine[13]);
                    }catch (Exception ignored){
                        continue;
                    }

                    Pelicula p = new Pelicula(id, nombre, fechaEstreno, idiomaOriginal, generos, "", ingreso);
                    try {
                        peliculas.put(id, p);
                    }catch (Exception e){
//                        System.err.println("Error al agregar la pelicula en la linea "+processedLines+" con id "+id+" al HashTable. \nError Message: "+e.getMessage());
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

                                // Optional: If Pelicula also needs a reference to its collection
                                // p.setCollection(collection);

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

        long finalTime = System.currentTimeMillis();
        System.out.println("\n--- Resumen del procesamiento ---");
        System.out.println("Total de lineas procesadas: " + processedLines);
        System.out.println("Tiempo total de carga: " + (finalTime - startTime) + " ms");
//        System.out.println(peliculas.size());
//        System.out.println(colecciones.size());
        System.out.println("-----------------------------------\n");
//        System.out.println(colecciones.get(415931).getIdsPeliculas().length());

        OpenHashTable[] res = new OpenHashTable[2];
        res[0] = peliculas;
        res[1] = colecciones;

        return res;
    }

    private static String repairPythonDictLikeString(String input) {
        String jsonInput = input;

        jsonInput = jsonInput.replace("None", "null")
                .replace("True", "true")
                .replace("False", "false");

        jsonInput = jsonInput.replaceAll("(?<![\\w])'([\\w]+)'(?=\\s*:)", "\"$1\"");  // Keys
        jsonInput = jsonInput.replaceAll("(?<=:\\s*)'(.*?)'", "\"$1\"");

        return jsonInput;
    }
}
