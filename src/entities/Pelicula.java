package entities;

import tads.list.ArrayList;

import java.time.LocalDate;

public class Pelicula implements Comparable<Pelicula>{
    private int idPelicula;
    private String nombre;
    private LocalDate fechaDeEstreno;
    private String idiomaOriginal;
    private ArrayList<String> genero;
    private ArrayList<Integer> directores;
    private long ingreso;
    private int numRating;
    private float promedioCalificaciones;

    //Constructor de la clase Película
    public Pelicula(int idPelicula, String nombre, LocalDate fechaDeEstreno, String idiomaOriginal, ArrayList<String> genero, long ingreso) {
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.fechaDeEstreno = fechaDeEstreno;
        this.idiomaOriginal = idiomaOriginal;
        this.genero = genero;
        this.directores = new ArrayList<>(5);
        this.actores = new ArrayList<>(50);
        this.ingreso = ingreso;
        this.numRating = 0;
    }

    public int getIdPelicula() {
        return idPelicula;
    }
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeEstreno() {
        return fechaDeEstreno;
    }
    public void setFechaDeEstreno(LocalDate fechaDeEstreno) {
        this.fechaDeEstreno = fechaDeEstreno;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }
    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public ArrayList<String> getGenero() {
        return genero;
    }
    public void setGenero(ArrayList<String> genero) {
        this.genero = genero;
    }

    public ArrayList<Integer> getDirectores() {
        return directores;
    }
    public void addDirector(int director) {
        this.directores.add(director);
    }

    public long getIngreso() {
        return ingreso;
    }
    public void setIngreso(long ingreso) {
        this.ingreso = ingreso;
    }

    public int getNumRating() {
        return numRating;
    }
    public void addNumRating() {
        this.numRating++;
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
                ", director='" + directores + '\'' +
                ", ingreso=" + ingreso +
                '}';
    }
}
