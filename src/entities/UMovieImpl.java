package entities;

import entities.aux.CargaDatos;
import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;

public class UMovieImpl implements UMovie{
    public OpenHashTable<Integer, Pelicula> peliculas;
    public OpenHashTable<Integer, Actor> actores;
    public OpenHashTable<Integer, Usuario> usuarios;
    public OpenHashTable<Integer, Coleccion> colecciones;
    public OpenHashTable<Integer, Calificacion> calificaciones;

    public UMovieImpl(){
        this.peliculas = new OpenHashTable<>(469181);
        this.actores = new OpenHashTable<>(469181);
        this.usuarios = new OpenHashTable<>(469181);
        this.colecciones = new OpenHashTable<>(469181);
        this.calificaciones = new OpenHashTable<>(469181);
    }

    @SuppressWarnings("unchecked")
    public void cargaDatos(){
        OpenHashTable[] temp = (OpenHashTable[]) CargaDatos.cargaDatos(); // Cast explícito
        this.peliculas = temp[0];
        this.colecciones = temp[1];
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
        long inicio = System.currentTimeMillis();

        ArrayList<Coleccion> lista = new ArrayList<>();

        // Obtener todas las colecciones
        ArrayList<Coleccion> todas = colecciones.values();

        // Calcular ingreso total por colección
        for (int i = 0; i < todas.length(); i++) {
            Coleccion c = todas.get(i);
            long ingresoTotal = 0;

            ArrayList<Integer> idsPelis = c.getIdsPeliculas();
            for (int j = 0; j < idsPelis.length(); j++) {
                Pelicula p = peliculas.get(idsPelis.get(j));
                if (p != null) {
                    ingresoTotal += p.getIngreso();
                }
            }

            c.setIngresoTotal(ingresoTotal);
            lista.add(c);
        }

        // Ordenar lista por ingresoTotal (burbuja descendente)
        for (int i = 0; i < lista.length(); i++) {
            for (int j = i + 1; j < lista.length(); j++) {
                if (lista.get(j).getIngresoTotal() > lista.get(i).getIngresoTotal()) {
                    Coleccion temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }

        // Mostrar top 5
        for (int i = 0; i < 5 && i < lista.length(); i++) {
            Coleccion c = lista.get(i);
            ArrayList<Integer> ids = c.getIdsPeliculas();

            System.out.print(c.getId() + ",");
            System.out.print(c.getNombre() + ",");
            System.out.print(ids.length() + ",[");
            for (int j = 0; j < ids.length(); j++) {
                System.out.print(ids.get(j));
                if (j < ids.length() - 1) System.out.print(",");
            }
            System.out.println("]," + c.getIngresoTotal());
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución de la consulta: " + (fin - inicio) + " ms");
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