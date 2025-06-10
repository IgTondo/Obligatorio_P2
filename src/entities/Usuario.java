package entities;

import tads.list.ArrayList;

public class Usuario implements Comparable<Usuario>{
    private int id;
    private ArrayList<Integer> calificaciones;

    //Constructor de la clase Usuario
    public Usuario(int id, ArrayList<Integer> calificaciones) {
        this.id = id;
        this.calificaciones = calificaciones;
    }

    public Usuario(int id){
        this.id = id;
        this.calificaciones = new ArrayList<>(10000);
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
    public ArrayList<Integer> getCalificaciones() {
        return calificaciones;
    }

    //Setter de las calificaciones que hizo el usuario
    public void setCalificaciones(ArrayList<Integer> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public void addCalificacion(int id){
        this.calificaciones.add(id);
    }

    @Override
    public int compareTo(Usuario usuario) {
        return 0;
    }
}
