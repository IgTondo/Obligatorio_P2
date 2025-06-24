package entities;

import entities.cargaDatos.CargaDatos;
import tads.hashTable.HashEntry;
import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;
import tads.list.linked.LinkedList;
import tads.tree.heap.HeapArray;


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
    }

    public void topPeliculasMasCalificacionesPorIdioma() {
        String[] idiomas = {"en", "es", "fr", "pt", "it"};
        String[] nombresIdiomas = {"inglés", "español", "francés", "portugués", "italiano"};

        for (int i = 0; i < idiomas.length; i++) {
            mostrarTop5Idioma(idiomas[i], nombresIdiomas[i]);
        }

        /*
        Top 5 de las películas que más calificaciones por idioma.
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <id_pelicula>, <titulo_pelicula>,<total_calificaciones>,<idioma>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */

    }

    private void mostrarTop5Idioma(String idioma, String nombreIdioma) {
        HeapArray<WrapperPelicula> peliculasIdioma = buscarPeliculasPorIdioma(idioma);

        if (peliculasIdioma == null || peliculasIdioma.isEmpty()) {
            System.out.println("No hay películas en " + nombreIdioma);
            System.out.println();
            return;
        }

        System.out.println("Top 5 películas en " + nombreIdioma + ":");

        // Extraer hasta 5 películas (las de mayor rating)
        for (int i = 1; i <= 5; i++) {
            try {
                Integer idPelicula = peliculasIdioma.pop().getIdPelicula();
                Pelicula pelicula = peliculas.get(idPelicula);

                if (pelicula != null) {
                    System.out.println("<" + i + ">, <" + pelicula.getNombre() + ">, <" + pelicula.getNumRating() + ">, <" + nombreIdioma + ">");
                }
            } catch (Exception e) {
                // No hay más películas en la cola
                break;
            }
        }
        System.out.println();
    }

    public void topPeliculasMejorCalificacionMedia(){

        /*
        Top 10 de las películas que mejor calificación media tienen por parte de los usuarios.
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <id_pelicula>, <titulo_pelicula>,<calificacion_media>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */
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

    public HeapArray<WrapperPelicula> buscarPeliculasPorIdioma(String idioma){

        HeapArray<WrapperPelicula> peliculasIdioma = new HeapArray<>(false) {
        };

        if (idioma == null || idioma.isBlank()){
            return peliculasIdioma;
        }


        for (int i = 0; i < peliculas.capacity(); i++) {
            LinkedList<HashEntry<Integer,Pelicula>> bucket = peliculas.getTable()[i];
            Pelicula p;
            if (bucket != null) {                                   // Si el bucket es nulo se pasa al siguiente bucket
                for (HashEntry<Integer,Pelicula> entry : bucket) {
                    p = entry.getValue();
                    if (p.getIdiomaOriginal().equals(idioma)) {
                        WrapperPelicula pelicula = new WrapperPelicula(p.getIdPelicula(), p.getNumRating());
                        peliculasIdioma.add(pelicula);
                    }
                }
            }

        }

        return peliculasIdioma;
    }


}