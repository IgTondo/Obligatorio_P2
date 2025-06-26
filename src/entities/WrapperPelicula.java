package entities;

 class WrapperPelicula implements Comparable<WrapperPelicula> {
    private Integer idPelicula;
    private int numRating;

    public WrapperPelicula(Integer idPelicula, int numRating) {
        this.idPelicula = idPelicula;
        this.numRating = numRating;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public int getNumRating() {
        return numRating;
    }

     @Override
     public int compareTo(WrapperPelicula other) {
         return Integer.compare(this.numRating, other.numRating);
     }
}
