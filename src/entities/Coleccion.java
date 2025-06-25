package entities;

import tads.list.ArrayList;

public class Coleccion implements Comparable<Coleccion>{
    private int id;
    private String nombre;
    private ArrayList<Integer> idsPeliculas;
    private long ingresoTotal;

    //Constructor de la clase Colección
    public Coleccion(int id, String nombre, ArrayList<Integer> idsPeliculas) {
        this.id = id;
        this.nombre = nombre;
        this.idsPeliculas = idsPeliculas;
        this.ingresoTotal = 0;
    }

    public Coleccion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.idsPeliculas = new ArrayList<>(10);
    }

    //Getter del id de la colección
    public int getId() {
        return id;
    }

    //Setter del id de la colección
    public void setId(int id) {
        this.id = id;
    }

    //Getter del nombre de la colección
    public String getNombre() {
        return nombre;
    }

    //Setter del nombre de la colección
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter de la lista de las ids de las películas de la colección
    public ArrayList<Integer> getIdsPeliculas() {
        return idsPeliculas;
    }

    //Setter de la lista de las ids de las películas de la colección
    public void setIdsPeliculas(ArrayList<Integer> idsPeliculas) {
        this.idsPeliculas = idsPeliculas;
    }

    public void addPeliculaToCollection(int idPelicula){
        this.idsPeliculas.add(idPelicula);
    }

    @Override
    public int compareTo(Coleccion coleccion) {
        return Long.compare(coleccion.ingresoTotal ,this.ingresoTotal);
    }

    public long getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(long ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    @Override
    public String toString() {
        return "Coleccion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ingresoTotal=" + ingresoTotal +
                ", idsPeliculas=" + idsPeliculas +
                '}';
    }

}
