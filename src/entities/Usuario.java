package entities;

public class Usuario {
    private int id;
    private int[] calificaciones;

    //Constructor de la clase Usuario
    public Usuario(int id, int[] calificaciones) {
        this.id = id;
        this.calificaciones = calificaciones;
    }

    //Getter del id del usuario
    public int getId() {
        return id;
    }

    //Setter del id del usuario
    public void setId(int id) {
        this.id = id;
    }

    //Getter de las calificaciones que hizo el usuario
    public int[] getCalificaciones() {
        return calificaciones;
    }

    //Setter de las calificaciones que hizo el usuario
    public void setCalificaciones(int[] calificaciones) {
        this.calificaciones = calificaciones;
    }
}
