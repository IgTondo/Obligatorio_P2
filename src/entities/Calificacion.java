package entities;

import java.util.Date;

public class Calificacion implements Comparable<Calificacion>{
    private int id;
    private int idUsuario;
    private int idPelicula;
    private int puntaje; // del 0 al 5
    private Date fechaCalificacion;

    //Constructor de la clase Calificación
    public Calificacion(int id, int idUsuario, int idPelicula, int puntaje, Date fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
        this.puntaje = puntaje;
        this.fechaCalificacion = fecha;

    }

    //Getter del id de la calificación
    public int getId() {
        return id;
    }

    //Setter del id de la calificación
    public void setId(int id) {
        this.id = id;
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
    public int getPuntaje() {
        return puntaje;
    }

    //Setter del puntaje de la calificación
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    //Getter de la fecha de publicación de la calificación
    public Date getFechaCalificacion() {
        return fechaCalificacion;
    }

    //Setter de la fecha de publicación de la calificación
    public void setFechaCalificacion(Date fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    @Override
    public int compareTo(Calificacion calificacion) {
        return 0;
    }
}