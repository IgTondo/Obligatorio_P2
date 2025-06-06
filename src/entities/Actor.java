package entities;

public class Actor {
    private int id;
    private String nombre;
    private int[] peliculas;

    //Constructor de la clase actor
    public Actor(int id, String nombre, int[] peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.peliculas = peliculas;
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
    public int[] getPeliculas() {
        return peliculas;
    }

    //Setter de las películas en las que actuó el actor
    public void setPeliculas(int[] peliculas) {
        this.peliculas = peliculas;
    }
}
