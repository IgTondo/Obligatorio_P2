package entities;

import entities.cargaDatos.CargaDatos;
import tads.hashTable.HashEntry;
import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;
import tads.list.linked.LinkedList;

import java.sql.Array;


public class UMovieImpl implements UMovie{
    public OpenHashTable<Integer, Pelicula> peliculas;
    public OpenHashTable<Integer, Actor> actores;
    public OpenHashTable<Integer, Usuario> usuarios;
    public OpenHashTable<Integer, Coleccion> colecciones;
    public OpenHashTable<Integer, Director> directores;
    public ArrayList<Calificacion> calificaciones;

    public UMovieImpl(){
        this.peliculas = new OpenHashTable<>();
        this.actores = new OpenHashTable<>();
        this.usuarios = new OpenHashTable<>();
        this.colecciones = new OpenHashTable<>();
        this.directores = new OpenHashTable<>();
        this.calificaciones = new ArrayList<>();
    }

    public void cargaDatos(){
        DataResult dr = CargaDatos.cargaDatos();
        this.peliculas = dr.getPeliculas();
        this.colecciones = dr.getColecciones();
        this.usuarios = dr.getUsuarios();
        this.calificaciones = dr.getCalificaciones();
        this.actores = dr.getActores();
        this.directores = dr.getDirectores();
//        System.out.println(actores.get(1898248));
    }


    public void topPeliculasMasCalificacionesPorIdioma(){

        /*
        Top 5 de las películas que más calificaciones por idioma.
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <id_pelicula>, <titulo_pelicula>,<total_calificaciones>,<idioma>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */


    }

    public void topPeliculasMejorCalificacionMedia() {
        /*
        Top 10 de las películas que mejor calificación media tienen por parte de los usuarios.
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <id_pelicula>, <titulo_pelicula>,<calificacion_media>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */

        // Mapas para acumular suma de puntuaciones y conteo de calificaciones por película
        OpenHashTable<Integer, Contador> conteoCalificaciones = new OpenHashTable<>(peliculas.size());
        OpenHashTable<Integer, Float> sumaPuntajes = new OpenHashTable<>(peliculas.size());

        // Paso 1 y 2: Recorremos las calificaciones para acumular
        for (Calificacion cal : calificaciones) {
            int idPelicula = cal.getIdPelicula();
            float puntaje = cal.getPuntaje();

            Contador cont = conteoCalificaciones.get(idPelicula);
            if (cont == null) {
                cont = new Contador();
                conteoCalificaciones.put(idPelicula, cont);
                sumaPuntajes.put(idPelicula, puntaje);
            }else {
                puntaje += sumaPuntajes.get(idPelicula);
                sumaPuntajes.delete(idPelicula);
                sumaPuntajes.put(idPelicula, puntaje);
            }
            cont.incrementar();
        }

        // Crear lista auxiliar con promedio
        ArrayList<Pelicula> candidatas = new ArrayList<>();

        ArrayList<Pelicula> todasLasPeliculas = peliculas.getValues();
        for (Pelicula pelicula : todasLasPeliculas) {
            int id = pelicula.getIdPelicula();

            if (conteoCalificaciones.contains(id)) {
                int total = conteoCalificaciones.get(id).getCount();
                if (total > 100) {
                    float suma = sumaPuntajes.get(id);
                    float promedio = suma / total;
                    pelicula.setPromedioCalificaciones(promedio);
                    candidatas.add(pelicula);
                }
            }
        }

        candidatas.sort();

        for (int i = 0; i < 10; i++) {
            Pelicula p = candidatas.get(i);
            float promedio = p.getPromedioCalificaciones();
            System.out.println(p.getIdPelicula()+", "+p.getNombre()+", "+promedio);
        }
    }

    public void topColeccionesMasIngresos(){

        /*
        Top 5 de las colecciones que más ingresos generaron.
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <id_coleccion>,
        <titulo_coleccion>,<cantidad_peliculas>,[id_pelicula_1,id_pelicula_2]<ingreso_generado>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */
        ArrayList<Coleccion> collections = colecciones.getValues();
        ArrayList<Coleccion> filtered = new ArrayList<>(collections.length());
        // Calcular ingreso total por colección
        for (int i = 0; i<collections.length(); i++) {
            long ingresoTotal = 0;
            Coleccion c = collections.get(i);
            ArrayList<Integer> idsPelis = c.getIdsPeliculas();
            for (int id : idsPelis) {
                Pelicula p = peliculas.get(id);
                if (p != null) {
                    ingresoTotal += p.getIngreso();
                }
            }


            if (ingresoTotal > 0){
                c.setIngresoTotal(ingresoTotal);
                filtered.add(c);
            }
        }

        filtered.sort();

        // Mostrar top 5
        for (int i = 0; i < 5 && i < filtered.length(); i++) {
            Coleccion c = filtered.get(i);
            ArrayList<Integer> ids = c.getIdsPeliculas();

            System.out.println(c.getId() + ",");
            System.out.print(c.getNombre() + ", ");
            System.out.print(ids.length() + ", [");
            for (int j = 0; j < ids.length(); j++) {
                System.out.print(ids.get(j));
                if (j < ids.length() - 1) System.out.print(",");
            }
            System.out.println("], " + c.getIngresoTotal());
        }
    }

    public void topDirectoresMejorCalificaciones(){

        /*
        Top 10 de los directores que mejor calificación tienen.
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <nombre_director>,<cantidad_peliculas>,<mediana_calificacion>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */
    }

    public void actorMasCalificacionesPorMes(){

        /*
        Actor con más calificaciones recibidas en cada mes del año.
         Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

         <mes>,<nombre_actor>,<cantidad_peliculas>,<cantidad de calificaciones>
         Tiempo de ejecución de la consulta: <tiempo_ejecucion>
         */
    }

    public void usuariosMasCalificacionesPorGenero(){

        /*
        Usuario con más calificaciones por género
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <id_usuario>,<genero>,<cantidad de califaciones sobre ese género>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */
        OpenHashTable<String, OpenHashTable<Integer, Contador>> generoUsuarioCount = new OpenHashTable<>(30);
        OpenHashTable<String, Contador> generoContador = new OpenHashTable<>(30);

        ArrayList<Usuario> usuariosList = usuarios.getValues();

        for (int i = 0; i < usuariosList.length(); i++) {
            Usuario user = usuariosList.get(i);
            int userId = user.getId();
            ArrayList<Calificacion> calificaciones = user.getCalificaciones();

            for (int j = 0; j < calificaciones.length(); j++) {
                Calificacion cal = calificaciones.get(j);
                Pelicula pelicula = peliculas.get(cal.getIdPelicula());

                if (pelicula == null) continue;

                ArrayList<String> generos = pelicula.getGenero();
                for (int g = 0; g < generos.length(); g++) {
                    String genero = generos.get(g);

                    Contador cont = generoContador.get(genero);
                    if (cont == null){
                        cont = new Contador();
                        generoContador.put(genero, cont);
                    }
                    cont.incrementar();

                    // Get or create the genre -> user map
                    OpenHashTable<Integer, Contador> usuarioCountMap = generoUsuarioCount.get(genero);
                    if (usuarioCountMap == null) {
                        usuarioCountMap = new OpenHashTable<>(30000);
                        generoUsuarioCount.put(genero, usuarioCountMap);
                    }

                    // Increment user's count
                    Contador cont2 = usuarioCountMap.get(userId);
                    if (cont2 == null){
                        cont2 = new Contador();
                        usuarioCountMap.put(userId, cont2);
                    }
                    cont2.incrementar();
                }
            }
        }

        ArrayList<GeneroCount> generoCounts = new ArrayList<>(50);
        for (LinkedList<HashEntry<String, Contador>> bucket : generoContador.getTable()) {
            if (bucket == null) continue;
            for (HashEntry<String, Contador> entry : bucket) {
                generoCounts.add(new GeneroCount(entry.getKey(), entry.getValue().getCount()));
            }
        }

        generoCounts.sort();

        for (int i = 0; i < 10; i++) {
            GeneroCount generoCount = generoCounts.get(i);
            String genero = generoCount.genero;
            OpenHashTable<Integer, Contador> userContadores = generoUsuarioCount.get(genero);

            int maxCount = 0;
            ArrayList<Integer> topUsers = new ArrayList<>();

            for (LinkedList<HashEntry<Integer, Contador>> userBucket : userContadores.getTable()) {
                if (userBucket == null) continue;
                for (HashEntry<Integer, Contador> entry : userBucket) {
                    int count = entry.getValue().getCount();

                    if (count > maxCount) {
                        maxCount = count;
                        topUsers = new ArrayList<>();
                        topUsers.add(entry.getKey());
                    } else if (count == maxCount) {
                        topUsers.add(entry.getKey());
                    }
                }
            }

            for (int k = 0; k < topUsers.length(); k++) {
                System.out.println(topUsers.get(k) + ", " + genero + ", " + maxCount);
            }
            System.out.println();
        }
    }


}