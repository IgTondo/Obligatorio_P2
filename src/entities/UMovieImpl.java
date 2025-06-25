package entities;

import entities.cargaDatos.CargaDatos;
import org.apache.commons.lang3.tuple.Pair;
import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;
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
        this.peliculas = new OpenHashTable<>(16);
        this.actores = new OpenHashTable<>(16);
        this.usuarios = new OpenHashTable<>(16);
        this.colecciones = new OpenHashTable<>(16);
        this.directores = new OpenHashTable<>(16);
        this.calificaciones = new ArrayList<>(16);

    }

    public void cargaDatos(){
        DataResult dr = CargaDatos.cargaDatos();
        this.peliculas = dr.getPeliculas();
        this.colecciones = dr.getColecciones();
        this.usuarios = dr.getUsuarios();
        this.calificaciones = dr.getCalificaciones();
        this.actores = dr.getActores();
        this.directores = dr.getDirectores();
        //OpenHashTable[] temp = (OpenHashTable[]) CargaDatos.cargaDatos(); // Cast explícito
        //this.peliculas = temp[0];
        //this.colecciones = temp[1];
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

        lista.sort();


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

    public void topDirectoresMejorCalificaciones() {
    /*
    Top 10 de los directores que mejor calificación tienen.
    Al seleccionar dicha opción se deberán mostrar los datos de la siguiente manera:

    <nombre_director>,<cantidad_peliculas>,<mediana_calificacion>
    Tiempo de ejecución de la consulta: <tiempo_ejecucion>
    */

        long inicio = System.currentTimeMillis();


        OpenHashTable<Integer, ListaDeFloats> calificacionesPorPelicula = new OpenHashTable<>(peliculas.size());

        for (int i = 0; i < calificaciones.length(); i++) {
            Calificacion c = calificaciones.get(i);
            int idPeli = c.getIdPelicula();

            ListaDeFloats lista = calificacionesPorPelicula.get(idPeli);
            if (lista == null) {
                lista = new ListaDeFloats();
                calificacionesPorPelicula.put(idPeli, lista);
            }

            lista.add(c.getPuntaje());
        }


        ArrayList<DirectorEstadistica> estadisticas = new ArrayList<>();
        ArrayList<Director> todos = directores.getValues();

        for (int i = 0; i < todos.length(); i++) {
            Director d = todos.get(i);
            ArrayList<Integer> pelis = d.getPeliculasDirigidas();

            if (pelis.length() <= 1) continue;

            ArrayList<Float> califs = new ArrayList<>();

            for (int j = 0; j < pelis.length(); j++) {
                int idPeli = pelis.get(j);
                ListaDeFloats lista = calificacionesPorPelicula.get(idPeli);

                if (lista != null) {
                    ArrayList<Float> valores = lista.getValores();
                    for (int k = 0; k < valores.length(); k++) {
                        califs.add(valores.get(k));
                    }
                }
            }

            if (califs.length() > 100) {
                califs.sort();
                float mediana;
                int mid = califs.length() / 2;
                if (califs.length() % 2 == 0) {
                    mediana = (califs.get(mid - 1) + califs.get(mid)) / 2;
                } else {
                    mediana = califs.get(mid);
                }

                estadisticas.add(new DirectorEstadistica(d.getNombre(), pelis.length(), mediana));
            }
        }


        estadisticas.sort();

        for (int i = 0; i < 10 && i < estadisticas.length(); i++) {
            DirectorEstadistica d = estadisticas.get(i);
            System.out.println(d.getNombre() + "," + d.getCantPeliculas() + "," + d.getMediana());
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución de la consulta: " + (fin - inicio) + " ms");
    }

            //cambie compareTo en coleccion, agegue la clase auxiliar, y esta funcion, no se si directores se esta cargando completamente

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