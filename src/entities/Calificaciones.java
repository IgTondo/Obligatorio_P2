package entities;

import java.util.Date;

public class Calificaciones {
    private int id;
    private int idUsuario;
    private int idPelicula;
    private int puntaje; // del 0 al 5
    private Date fechaCalificacion;

    public Calificaciones(int id, int idUsuario, int idPelicula, int puntaje, Date fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
        this.puntaje = puntaje;
        this.fechaCalificacion = fecha;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdPelicula() {
        return idPelicula;
    }
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    public Date getFechaCalificacion() {
        return fechaCalificacion;
    }
    public void setFechaCalificacion(Date fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }
}
