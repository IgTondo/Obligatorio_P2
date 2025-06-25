package entities;

class GeneroCount implements Comparable<GeneroCount> {
    String genero;
    int count;

    public GeneroCount(String genero, int count) {
        this.genero = genero;
        this.count = count;
    }

    @Override
    public int compareTo(GeneroCount other) {
        return Integer.compare(other.count, this.count); // ascending by default
    }

    @Override
    public String toString() {
        return genero + ": " + count;
    }
}

