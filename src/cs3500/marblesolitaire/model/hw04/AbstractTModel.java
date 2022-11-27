package cs3500.marblesolitaire.model.hw04;

import java.util.List;

/**
 * calls method that is based on the shape depending on the chosen one.
 */
public abstract class AbstractTModel extends AbstractMarbleSolitaireModel {

  protected AbstractTModel(int thickness, int row, int col) throws IllegalArgumentException {
    super(thickness, row, col);
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    int size = this.getBoardSize();
    int nextRow = this.findCoOrd(fromRow, toRow);
    int nextCol = this.findCoOrd(fromCol, toCol);

    if (fromCol < 0 || fromCol > size - 1 || fromRow < 0 || fromRow > size - 1) {
      throw new IllegalArgumentException("board space you want to move from is not possible");
    }
    if (toCol < 0 || toCol > size - 1 || toRow < 0 || toRow > size - 1) {
      throw new IllegalArgumentException("board space you want to move to is not possible");
    }
    if (this.getSlotAt(fromRow, fromCol).equals(SlotState.Empty) || this.getSlotAt(fromRow, fromCol)
            .equals(SlotState.Invalid)) {
      throw new IllegalArgumentException("space you are attempting to move from is empty or " +
              "is invalid");
    }
    if (this.getSlotAt(toRow, toCol).equals(SlotState.Marble) || this.getSlotAt(toRow, toCol)
            .equals(SlotState.Invalid)) {
      throw new IllegalArgumentException("space you are attempting to move to has a marble or " +
              "is invalid");
    }
    if (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2) {
      throw new IllegalArgumentException("diagonal is invalid");
    }

    if (Math.abs(fromRow - toRow) == 1 || Math.abs(fromCol - toCol) == 1) {
      throw new IllegalArgumentException("cannot move only 1 space");
    }
    if (fromRow == toRow && fromCol == toCol) {
      throw new IllegalArgumentException("you are selecting the same space");
    }

    if (Math.abs(fromRow - toRow) >= 3 || Math.abs(fromCol - toCol) >= 3) {
      throw new IllegalArgumentException("cannot move more than two spaces");
    }

    if (!((Math.abs(fromRow - toRow) == 2 && fromCol == toCol)
            || Math.abs(fromCol - toCol) == 2 && fromRow == toRow)) {
      throw new IllegalArgumentException("invalid");
    }

    if (this.getSlotAt(nextRow, nextCol).equals(SlotState.Invalid) || this.getSlotAt(nextRow,
            nextCol).equals(SlotState.Empty)) {
      throw new IllegalArgumentException("must move over another marble");
    }

    this.editState(fromRow, fromCol, SlotState.Empty);

    this.editState(nextRow, nextCol, SlotState.Empty);

    this.editState(toRow, toCol, SlotState.Marble);

  }

  protected List<List<SlotState>> checkIfEmpty(List<List<SlotState>> board, int row, int col,
                                               int length)
          throws IllegalArgumentException {
    if (board.get(row).get(col).equals(SlotState.Invalid) || row < 0 || col < 0 || row > length - 1
            || col > length - 1) {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + col + ")");
    } else {
      board.get(row).remove(col);
      board.get(row).add(col, SlotState.Empty);
    }
    return board;
  }


  protected boolean isValidMove(int row, int column) {
    if (!(this.getSlotAt(row, column).equals(SlotState.Marble))) {
      return false;
    }
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    boolean t;

    down = this.canMoveUpDown(row + 1, column, row + 2, column);
    up = this.canMoveUpDown(row - 1, column, row - 2, column);
    left = this.canMoveUpDown(row, column - 1, row, column - 2);
    right = this.canMoveUpDown(row, column + 1, row, column + 2);

    t = left || right || up || down;
    return t;
  }
}

