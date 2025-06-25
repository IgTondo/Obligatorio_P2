package entities;

public class DirectorEstadistica implements Comparable<DirectorEstadistica> {

    private String nombre;
    private int cantPeliculas;
    private float mediana;

    public DirectorEstadistica(String nombre, int cantPeliculas, float mediana) {
        this.nombre = nombre;
        this.cantPeliculas = cantPeliculas;
        this.mediana = mediana;
    }

    @Override
    public int compareTo(DirectorEstadistica otro) {
        return Float.compare(otro.mediana, this.mediana);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantPeliculas() {
        return cantPeliculas;
    }

    public void setCantPeliculas(int cantPeliculas) {
        this.cantPeliculas = cantPeliculas;
    }

    public float getMediana() {
        return mediana;
    }

    public void setMediana(float mediana) {
        this.mediana = mediana;
    }
}
