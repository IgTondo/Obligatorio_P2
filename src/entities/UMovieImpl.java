package entities;

import entities.cargaDatos.CargaDatos;
import org.apache.commons.lang3.tuple.Pair;
import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;

import java.util.Map;


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

    @SuppressWarnings("unchecked")
    public void cargaDatos(){
        CargaDatos.cargaDatos();
//        DataResult dr = CargaDatos.cargaDatos();
//        this.peliculas = dr.getPeliculas();
//        this.colecciones = dr.getColecciones();
//        this.usuarios = dr.getUsuarios();
//        this.calificaciones = dr.getCalificaciones();
//        this.actores = dr.getActores();
//        this.directores = dr.getDirectores();
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
        ArrayList<PeliculaPromedio> candidatas = new ArrayList<>();

        ArrayList<Pelicula> todasLasPeliculas = peliculas.getValues();
        for (int i = 0; i < todasLasPeliculas.length(); i++) {
            Pelicula pelicula = todasLasPeliculas.get(i);
            int id = pelicula.getIdPelicula();

            if (conteoCalificaciones.contains(id)) {
                int total = conteoCalificaciones.get(id);
                if (total > 100) {
                    float suma = sumaPuntajes.get(id);
                    float promedio = suma / total;
                    candidatas.add(new PeliculaPromedio(pelicula, promedio));
                }
            }
        }

        candidatas.sort();

        // Seleccionar top 10
        ArrayList<Pelicula> top10 = new ArrayList<>();
        int cantidad = Math.min(10, candidatas.length());
        for (int i = 0; i < cantidad; i++) {
            top10.add(candidatas.get(i).pelicula);
        }

        for (int i = 0; i < 10; i++) {
            PeliculaPromedio pp = candidatas.get(i);
            Pelicula p = pp.pelicula;
            float promedio = pp.promedio;
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