package entities;

public class PeliculaPromedio implements Comparable<PeliculaPromedio>{
    Pelicula pelicula;
    float promedio;

    public PeliculaPromedio(Pelicula pelicula, float promedio) {
        this.pelicula = pelicula;
        this.promedio = promedio;
    }

    @Override
    public int compareTo(PeliculaPromedio peliculaPromedio) {
        return Float.compare(peliculaPromedio.promedio, this.promedio);
    }
}
