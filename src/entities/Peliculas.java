package entities;

import java.util.Date;

public class Peliculas {
    private int idPelicula;
    private Date fechaDeEstreno;
    private String idiomaOriginal;
    private String[] genero;
    private String director;
    private int ingreso;

    public Peliculas(int idPelicula, Date fechaDeEstreno, String idiomaOriginal, String[] genero, String director, int ingreso) {
        this.idPelicula = idPelicula;
        this.fechaDeEstreno = fechaDeEstreno;
        this.idiomaOriginal = idiomaOriginal;
        this.genero = genero;
        this.director = director;
        this.ingreso = ingreso;
    }
    public int getIdPelicula() {
        return idPelicula;
    }
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
    public Date getFechaDeEstreno() {
        return fechaDeEstreno;
    }
    public void setFechaDeEstreno(Date fechaDeEstreno) {
        this.fechaDeEstreno = fechaDeEstreno;
    }
    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }
    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }
    public String[] getGenero() {
        return genero;
    }
    public void setGenero(String[] genero) {
        this.genero = genero;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public int getIngreso() {
        return ingreso;
    }
    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }
}

