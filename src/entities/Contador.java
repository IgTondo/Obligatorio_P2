package entities;

public class Contador implements Comparable<Contador> {
    private int count;

    public Contador() {
        this.count = 0;
    }

    public void incrementar() {
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(Contador o) {
        return Integer.compare(this.count, o.count);
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}

