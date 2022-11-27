package cs3500.marblesolitaire.model.hw04;

import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class of methods which do not change based on the shape of the board.
 */
public abstract class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {


  protected List<List<SlotState>> board;

  protected final int thickness;

  protected AbstractMarbleSolitaireModel(int thickness, int row, int col)
          throws IllegalArgumentException {
    this.board = this.fullBoard(row, col, thickness);
    this.thickness = thickness;
  }

  @Override
  public abstract void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException;

  protected abstract boolean isValidMove(int row, int col);

  @Override
  public boolean isGameOver() {
    int row = 0;
    int size = this.getBoardSize();
    while (row <= size - 1) {
      int col = 0;
      while (col <= size - 1) {
        if (this.isValidMove(row, col)) {
          return false;
        } else {
          col = col + 1;
        }
      }
      row = row + 1;
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return thickness + (((2 * thickness) - 2));
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row > this.getBoardSize() - 1 || col > this.getBoardSize() - 1) {
      throw new IllegalArgumentException("row and col are invalid");
    } else {
      return this.board.get(row).get(col);
    }
  }

  @Override
  public int getScore() {
    int score = 0;
    int row = 0;
    while (row <= this.getBoardSize() - 1) {
      int column = 0;
      while (column <= this.getBoardSize() - 1) {
        if (this.getSlotAt(row, column).equals(SlotState.Marble)) {
          score = score + 1;
        }
        column = column + 1;
      }
      row = row + 1;
    }
    return score;
  }

  protected abstract List<List<SlotState>> fullBoard(int row, int col, int thickness)
          throws IllegalArgumentException;

  protected int findCoOrd(int fromC, int toC) {
    if (fromC == toC) {
      return fromC;
    } else {
      return Math.max(toC, fromC) - 1;
    }
  }

  protected boolean canMoveUpDown(int jumpRow, int jumpCol, int landRow, int landCol) {
    boolean ability;
    try {
      ability =
              this.getSlotAt(jumpRow, jumpCol).equals(SlotState.Marble) && this.getSlotAt(landRow,
                      landCol).equals(SlotState.Empty);
    } catch (IllegalArgumentException i) {
      ability = false;
    }
    return ability;
  }

  protected void editState(int row, int column, SlotState state) {
    List<SlotState> s = this.board.get(row);
    s.remove(column);
    s.add(column, state);
  }

}
