package entities;
import tads.list.ArrayList;

public class Actor implements Comparable<Actor>{
    private int id;
    private String nombre;
    private ArrayList<Integer> peliculas;

    //Constructor de la clase actor
    public Actor(int id, String nombre, ArrayList<Integer> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.peliculas = peliculas;
    }

    public Actor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.peliculas = new ArrayList<>(10000);
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

    //Getter de las películas en las que actuó el actor
    public ArrayList<Integer> getPeliculas() {
        return peliculas;
    }

    //Setter de las películas en las que actuó el actor
    public void setPeliculas(ArrayList<Integer> peliculas) {
        this.peliculas = peliculas;
    }

    public void addPelicula(int id){
        this.peliculas.add(id);
    }

    @Override
    public int compareTo(Actor actor) {
        return 0;
    }
}
