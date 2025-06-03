package entities;

public class Coleccion {
    private int id;
    private String nombre;
    private int[] idsPeliculas;

    public Coleccion(int id, String nombre, int[] idsPeliculas) {
        this.id = id;
        this.nombre = nombre;
        this.idsPeliculas = idsPeliculas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getIdsPeliculas() {
        return idsPeliculas;
    }

    public void setIdsPeliculas(int[] idsPeliculas) {
        this.idsPeliculas = idsPeliculas;
    }
}
