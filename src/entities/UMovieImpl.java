package entities;

import tads.hashTable.OpenHashTable;

public class UMovieImpl implements UMovie{
    public OpenHashTable peliculas;
    public OpenHashTable actores;
    public OpenHashTable usuarios;
    public OpenHashTable colecciones;
    public OpenHashTable calificaciones;

    public UMovieImpl(){
        this.peliculas = new OpenHashTable(469181);
        this.actores = new OpenHashTable();
        this.usuarios = new OpenHashTable();
        this.colecciones = new OpenHashTable();
        this.calificaciones = new OpenHashTable();
    }

    public void topPeliculasMasCalificacionesPorIdioma(){

        /*
        Top 5 de las películas que más calificaciones por idioma.
        Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

        <id_pelicula>, <titulo_pelicula>,<total_calificaciones>,<idioma>
        Tiempo de ejecución de la consulta: <tiempo_ejecucion>
        */
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
}