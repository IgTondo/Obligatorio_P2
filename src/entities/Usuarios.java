package entities;

public class Usuarios {
    private int id;
    private int[] calificaciones;

    public Usuarios(int id, int[] calificaciones) {
        this.id = id;
        this.calificaciones = calificaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(int[] calificaciones) {
        this.calificaciones = calificaciones;
    }
}
