package entities;

import tads.hashTable.OpenHashTable;
import tads.list.ArrayList;

public class DataResult {

    private OpenHashTable<Integer, Pelicula> peliculas;
    private OpenHashTable<Integer, Coleccion> colecciones;
    private OpenHashTable<Integer, Usuario> usuarios;
    private OpenHashTable<Integer, Actor> actores;
    private OpenHashTable<Integer, Director> directores;
    private ArrayList<Calificacion> calificaciones;

    public DataResult() {
        this.peliculas = new OpenHashTable<>(16);
        this.colecciones = new OpenHashTable<>(16);
        this.usuarios = new OpenHashTable<>(16);
        this.actores = new OpenHashTable<>(16);
        this.directores = new OpenHashTable<>(16);
        this.calificaciones = new ArrayList<>(16);
    }

    public OpenHashTable<Integer, Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(OpenHashTable<Integer, Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public OpenHashTable<Integer, Coleccion> getColecciones() {
        return colecciones;
    }

    public void setColecciones(OpenHashTable<Integer, Coleccion> colecciones) {
        this.colecciones = colecciones;
    }

    public OpenHashTable<Integer, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(OpenHashTable<Integer, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public OpenHashTable<Integer, Actor> getActores() {
        return actores;
    }

    public void setActores(OpenHashTable<Integer, Actor> actores) {
        this.actores = actores;
    }

    public OpenHashTable<Integer, Director> getDirectores() {
        return directores;
    }

    public void setDirectores(OpenHashTable<Integer, Director> directores) {
        this.directores = directores;
    }
}
