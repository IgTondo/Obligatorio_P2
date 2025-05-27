package tads.exceptions;

public class ListOutOfIndexException extends RuntimeException {

  private int index;

  public ListOutOfIndexException(int index) {
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  @Override
  public String toString(){
    return "La lista solo tiene " + index + " elementos";
  }
}

