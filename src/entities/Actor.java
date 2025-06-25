package entities;
import tads.list.ArrayList;

public class Actor implements Comparable<Actor>{
    private int id;
    private String nombre;

    //Constructor de la clase actor
    public Actor(int id, String nombre, ArrayList<Integer> peliculas) {
        this.id = id;
        this.nombre = nombre;
    }

    public Actor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //Getter del id del actor
    public int getId() {
        return id;
    }

    //Setter del id del actor
    public void setId(int id) {
        this.id = id;
    }

    //Getter del nombre del actor
    public String getNombre() {
        return nombre;
    }

    //Setter del nombre del actor
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Actor actor) {
        return 0;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
