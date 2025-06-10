package entities;

import tads.list.ArrayList;
import java.util.Date;

public class Pelicula implements Comparable<Pelicula>{
    private int idPelicula;
    private Date fechaDeEstreno;
    private String idiomaOriginal;
    private ArrayList<String> genero;
    private String director;
    private int ingreso;

    //Constructor de la clase Película
    public Pelicula(int idPelicula, Date fechaDeEstreno, String idiomaOriginal, ArrayList<String> genero, String director, int ingreso) {
        this.idPelicula = idPelicula;
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

    //Getter de la fecha de estreno de la película
    public Date getFechaDeEstreno() {
        return fechaDeEstreno;
    }

    //Setter de la fecha de estreno de la película
    public void setFechaDeEstreno(Date fechaDeEstreno) {
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
    public int getIngreso() {
        return ingreso;
    }

    //Setter del director de la película
    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }

    @Override
    public int compareTo(Pelicula pelicula) {
        return 0;
    }
}

