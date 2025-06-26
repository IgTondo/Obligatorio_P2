package entities;
import tads.list.ArrayList;

public class ListaDeFloats implements Comparable<ListaDeFloats> {
    private ArrayList<Float> valores;

    public ListaDeFloats() {
        this.valores = new ArrayList<>();
    }

    public void add(Float f) {
        valores.add(f);
    }

    public ArrayList<Float> getValores() {
        return valores;
    }

    @Override
    public int compareTo(ListaDeFloats o) {
        return 0;
    }
}
