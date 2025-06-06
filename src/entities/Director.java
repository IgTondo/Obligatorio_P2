package entities;

public class Director {
    private int idDirector;
    private String nombre;
    private int[] peliculasDirigidas;

    //Constructor de la clase Director
    public Director(int idDirector, String nombre, int[] peliculasDirigidas) {
        this.idDirector = idDirector;
        this.nombre = nombre;
        this.peliculasDirigidas = peliculasDirigidas;
    }

    //Getter de la id del director
    public int getIdDirector() {
        return idDirector;
    }

    //Setter de la id del director
    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    //Getter del nombre del director
    public String getNombre() {
        return nombre;
    }

    //Setter del nombre del director
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter de la lista de las películas que dirigió el director
    public int[] getPeliculasDirigidas() {
        return peliculasDirigidas;
    }

    //Setter de la lista de las películas que dirigió el director
    public void setPeliculasDirigidas(int[] peliculasDirigidas) {
        this.peliculasDirigidas = peliculasDirigidas;
    }
}
