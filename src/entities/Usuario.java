package entities;

import tads.list.ArrayList;

public class Usuario implements Comparable<Usuario>{
    private int id;
    private ArrayList<Calificacion> calificaciones;

    //Constructor de la clase Usuario
    public Usuario(int id, ArrayList<Calificacion> calificaciones) {
        this.id = id;
        this.calificaciones = calificaciones;
    }

    public Usuario(int id){
        this.id = id;
        this.calificaciones = new ArrayList<>(10);
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
    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    //Setter de las calificaciones que hizo el usuario
    public void setCalificaciones(ArrayList<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public void addCalificacion(Calificacion cal){
        this.calificaciones.add(cal);
    }

    @Override
    public int compareTo(Usuario usuario) {
        return 0;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", calificaciones=" + calificaciones +
                '}';
    }
}
