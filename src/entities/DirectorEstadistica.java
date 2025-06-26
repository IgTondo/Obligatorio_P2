package entities;

import tads.list.ArrayList;

public class DirectorEstadistica implements Comparable<DirectorEstadistica> {
    private int idDirector;
    private String nombre;
    private ArrayList<Float> puntajes;
    private ArrayList<Pelicula> peliculas;
    private float mediana;

    public DirectorEstadistica(String nombre, int idDirector) {
        this.nombre = nombre;
        this.idDirector = idDirector;
        this.puntajes = new ArrayList<>(50);
        this.mediana = 0;
        this.peliculas = new ArrayList<>();
    }

    @Override
    public int compareTo(DirectorEstadistica otro) {
        return Float.compare(otro.mediana, this.mediana);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getMediana() {
        return mediana;
    }

    public void setMediana(float mediana) {
        this.mediana = mediana;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public ArrayList<Float> getPuntajes() {
        return puntajes;
    }

    public void addPuntaje(float puntaje) {
        this.puntajes.add(puntaje);
    }

    public void setPuntajes(ArrayList<Float> puntajes) {
        this.puntajes = puntajes;
    }

    public void addPelicula(Pelicula peli){
        peliculas.add(peli);
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public void calcularMediana(){
        puntajes.sort();
        int size = puntajes.length();
        if (puntajes.length() % 2 == 0){
            mediana = (puntajes.get(size/2) + puntajes.get(size/2 - 1)) / 2;
        }else {
            mediana = puntajes.get(puntajes.length()/2);
        }
    }
}
