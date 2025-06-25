package entities;

import entities.cargaDatos.CargaDatos;
import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;


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
        System.out.println(directores.size());
        System.out.println(actores.get(13295));
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
        OpenHashTable<Integer, Integer> conteoCalificaciones = new OpenHashTable<>(peliculas.size());
        OpenHashTable<Integer, Float> sumaPuntajes = new OpenHashTable<>(peliculas.size());

        // Paso 1 y 2: Recorremos las calificaciones para acumular
        for (Calificacion cal : calificaciones) {
            int idPelicula = cal.getIdPelicula();
            float puntaje = cal.getPuntaje();

            if (!conteoCalificaciones.contains(idPelicula)) {
                conteoCalificaciones.put(idPelicula, 1);
                sumaPuntajes.put(idPelicula, puntaje);
            } else {
                float sumaActual = sumaPuntajes.get(idPelicula);
                sumaPuntajes.delete(idPelicula);
                sumaPuntajes.put(idPelicula, sumaActual + puntaje);
                int conteoActual = conteoCalificaciones.get(idPelicula);
                conteoCalificaciones.delete(idPelicula);
                conteoCalificaciones.put(idPelicula, conteoActual + 1);
            }
        }

        // Crear lista auxiliar con promedio
        ArrayList<Pelicula> candidatas = new ArrayList<>();

        ArrayList<Pelicula> todasLasPeliculas = peliculas.getValues();
        for (Pelicula pelicula : todasLasPeliculas) {
            int id = pelicula.getIdPelicula();

            if (conteoCalificaciones.contains(id)) {
                int total = conteoCalificaciones.get(id);
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
        // Obtener todas las colecciones
        ArrayList<Coleccion> collections = colecciones.getValues();

        // Calcular ingreso total por colección
        for (Coleccion c : collections) {
            long ingresoTotal = 0;

            ArrayList<Integer> idsPelis = c.getIdsPeliculas();
            for (int id : idsPelis) {
                Pelicula p = peliculas.get(id);
                if (p != null) {
                    ingresoTotal += p.getIngreso();
                }
            }

            c.setIngresoTotal(ingresoTotal);
        }

        collections.sort();

        // Mostrar top 5
        for (int i = 0; i < 5 && i < collections.length(); i++) {
            Coleccion c = collections.get(i);
            ArrayList<Integer> ids = c.getIdsPeliculas();

            System.out.println(c.getId() + ",");
            System.out.print(c.getNombre() + ",");
            System.out.print(ids.length() + ",[");
            for (int j = 0; j < ids.length(); j++) {
                System.out.print(ids.get(j));
                if (j < ids.length() - 1) System.out.print(",");
            }
            System.out.println("]," + c.getIngresoTotal());
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

    public void ActorMasCalificacionesPorMes(){

        /*
        Actor con más calificaciones recibidas en cada mes del año.
         Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

         <mes>,<nombre_actor>,<cantidad_peliculas>,<cantidad de calificaciones>
         Tiempo de ejecución de la consulta: <tiempo_ejecucion>
         */
    }

    public void usuariosMasCalificacionesPorGenero(){

        /*
        Usuarios con más calificaciones por género
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <id_usuario>,<genero>,<cantidad de califaciones sobre ese género>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */
    }


}