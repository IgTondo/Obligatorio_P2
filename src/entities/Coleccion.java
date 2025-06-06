package entities;

public class Coleccion {
    private int id;
    private String nombre;
    private int[] idsPeliculas;

    //Constructor de la clase Colección
    public Coleccion(int id, String nombre, int[] idsPeliculas) {
        this.id = id;
        this.nombre = nombre;
        this.idsPeliculas = idsPeliculas;
    }

    //Getter del id de la colección
    public int getId() {
        return id;
    }

    //Setter del id de la colección
    public void setId(int id) {
        this.id = id;
    }

    //Getter del nombre de la colección
    public String getNombre() {
        return nombre;
    }

    //Setter del nombre de la colección
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter de la lista de las ids de las películas de la colección
    public int[] getIdsPeliculas() {
        return idsPeliculas;
    }

    //Setter de la lista de las ids de las películas de la colección
    public void setIdsPeliculas(int[] idsPeliculas) {
        this.idsPeliculas = idsPeliculas;
    }
}
