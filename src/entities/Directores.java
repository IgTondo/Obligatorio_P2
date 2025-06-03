package entities;

public class Directores {
    private int idDirector;
    private String nombre;
    private int[] peliculasDirigidas;

    public Directores(int idDirector, String nombre, int[] peliculasDirigidas) {
        this.idDirector = idDirector;
        this.nombre = nombre;
        this.peliculasDirigidas = peliculasDirigidas;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getPeliculasDirigidas() {
        return peliculasDirigidas;
    }

    public void setPeliculasDirigidas(int[] peliculasDirigidas) {
        this.peliculasDirigidas = peliculasDirigidas;
    }
}
