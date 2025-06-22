package entities;

import java.time.LocalDate;

public class Calificacion implements Comparable<Calificacion>{
    private int idUsuario;
    private int idPelicula;
    private float puntaje; // del 0 al 5
    private LocalDate fechaCalificacion;

    //Constructor de la clase Calificación
    public Calificacion(int idUsuario, int idPelicula, float puntaje, LocalDate fecha) {
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
        this.puntaje = puntaje;
        this.fechaCalificacion = fecha;

    }

    //Getter del id de quien hizo la calificación
    public int getIdUsuario() {
        return idUsuario;
    }

    //Setter del id de quien hizo la calificación
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    //Getter del id de la película calificada
    public int getIdPelicula() {
        return idPelicula;
    }

    //Setter del id de la película calificada
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    //Getter del puntaje de la calificación
    public float getPuntaje() {
        return puntaje;
    }

    //Setter del puntaje de la calificación
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    //Getter de la fecha de publicación de la calificación
    public LocalDate getFechaCalificacion() {
        return fechaCalificacion;
    }

    //Setter de la fecha de publicación de la calificación
    public void setFechaCalificacion(LocalDate fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    @Override
    public int compareTo(Calificacion calificacion) {
        return 0;
    }

    @Override
    public String toString() {
        return "Calificacion{" +
                "idUsuario=" + idUsuario +
                ", idPelicula=" + idPelicula +
                ", puntaje=" + puntaje +
                ", fechaCalificacion=" + fechaCalificacion +
                '}';
    }
}