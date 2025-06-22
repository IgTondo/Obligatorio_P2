package entities;

import tads.list.ArrayList;

import java.time.LocalDate;

public class Pelicula implements Comparable<Pelicula>{
    private int idPelicula;
    private String nombre;
    private LocalDate fechaDeEstreno;
    private String idiomaOriginal;
    private ArrayList<String> genero;
    private String director;
    private long ingreso;

    //Constructor de la clase Película
    public Pelicula(int idPelicula, String nombre, LocalDate fechaDeEstreno, String idiomaOriginal, ArrayList<String> genero, String director, long ingreso) {
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.fechaDeEstreno = fechaDeEstreno;
        this.idiomaOriginal = idiomaOriginal;
        this.genero = genero;
        this.director = director;
        this.ingreso = ingreso;
    }

    //Getter del id de la película
    public int getIdPelicula() {
        return idPelicula;
    }

    //Setter del id de la película
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter de la fecha de estreno de la película
    public LocalDate getFechaDeEstreno() {
        return fechaDeEstreno;
    }

    //Setter de la fecha de estreno de la película
    public void setFechaDeEstreno(LocalDate fechaDeEstreno) {
        this.fechaDeEstreno = fechaDeEstreno;
    }

    //Getter del idioma original de la película
    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    //Setter del idioma original de la película
    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    //Getter del género de la película
    public ArrayList<String> getGenero() {
        return genero;
    }

    //Setter del género de la película
    public void setGenero(ArrayList<String> genero) {
        this.genero = genero;
    }

    //Getter del director de la película
    public String getDirector() {
        return director;
    }

    //Setter del director de la película
    public void setDirector(String director) {
        this.director = director;
    }

    //Getter del ingreso de la película
    public long getIngreso() {
        return ingreso;
    }

    //Setter del director de la película
    public void setIngreso(long ingreso) {
        this.ingreso = ingreso;
    }

    @Override
    public int compareTo(Pelicula pelicula) {
        return 0;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "idPelicula=" + idPelicula +
                ", nombre='" + nombre + '\'' +
                ", fechaDeEstreno=" + fechaDeEstreno +
                ", idiomaOriginal='" + idiomaOriginal + '\'' +
                ", genero=" + genero +
                ", director='" + director + '\'' +
                ", ingreso=" + ingreso +
                '}';
    }
}
