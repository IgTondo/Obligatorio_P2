package entities;

import tads.list.ArrayList;

public class Director implements Comparable<Director>{
    private int idDirector;
    private String nombre;
    private ArrayList<Integer> peliculasDirigidas;

    //Constructor de la clase Director
    public Director(int idDirector, String nombre, ArrayList<Integer> peliculasDirigidas) {
        this.idDirector = idDirector;
        this.nombre = nombre;
        this.peliculasDirigidas = peliculasDirigidas;
    }

    public Director(int idDirector, String nombre) {
        this.idDirector = idDirector;
        this.nombre = nombre;
        this.peliculasDirigidas = new ArrayList<>(1000);
    }

    //Getter de la id del director
    public int getIdDirector() {
        return idDirector;
    }

    //Setter de la id del director
    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    //Getter del nombre del director
    public String getNombre() {
        return nombre;
    }

    //Setter del nombre del director
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter de la lista de las películas que dirigió el director
    public ArrayList<Integer> getPeliculasDirigidas() {
        return peliculasDirigidas;
    }

    //Setter de la lista de las películas que dirigió el director
    public void setPeliculasDirigidas(ArrayList<Integer> peliculasDirigidas) {
        this.peliculasDirigidas = peliculasDirigidas;
    }

    @Override
    public int compareTo(Director director) {
        return 0;
    }

    @Override
    public String toString() {
        return "Director{" +
                "idDirector=" + idDirector +
                ", nombre='" + nombre + '\'' +
                ", peliculasDirigidas=" + peliculasDirigidas +
                '}';
    }
}
