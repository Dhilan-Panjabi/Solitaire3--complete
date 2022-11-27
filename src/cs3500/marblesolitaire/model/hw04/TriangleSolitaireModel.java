package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a triangle solitaire game.
 */
public class TriangleSolitaireModel extends AbstractTModel {

  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  public TriangleSolitaireModel(int thickness) {
    super(thickness, 0, 0);
  }

  public TriangleSolitaireModel(int row, int col) {
    super(5, row, col);
  }

  public TriangleSolitaireModel(int thickness, int row, int col) {
    super(thickness, row, col);
  }

  @Override
  public int getBoardSize() {
    return thickness;
  }

  protected List<List<SlotState>> fullBoard(int row, int col, int thickness)
          throws IllegalArgumentException {
    if (thickness < 0) {
      throw new IllegalArgumentException("cannot have negative thickness");
    }
    int tRow = 1;
    List<List<SlotState>> b = new ArrayList<>();
    while (tRow <= thickness) {
      List<SlotState> s = new ArrayList<>();
      int tCol = 0;
      while (tCol <= thickness) {
        if (tCol < tRow) {
          s.add(SlotState.Marble);
        } else {
          s.add(SlotState.Invalid);
        }
        tCol = tCol + 1;
      }
      b.add(s);
      tRow = tRow + 1;
    }
    if (row < 0 || row > thickness - 1 || col < 0 || col > thickness - 1) {
      throw new IllegalArgumentException("the empty space is not on the board");
    } else {
      if (b.get(row).get(col).equals(SlotState.Invalid)) {
        throw new IllegalArgumentException("the given space is an invalid slot for the empty slot");
      }
      b.get(row).remove(col);
      b.get(row).add(col, SlotState.Empty);
    }
    return b;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (fromRow < 0 || fromCol < 0) {
      throw new IllegalArgumentException("given row and or col you want to move from is invalid");
    }
    if (toRow < 0 || toCol < 0) {
      throw new IllegalArgumentException("given row and or col you want to move to is invalid");
    }
    if (fromRow > this.getBoardSize() - 1 || fromCol > this.board.get(fromCol).size() - 1) {
      throw new IllegalArgumentException("given row or col you want to move from is too large");
    }
    if (toRow > this.getBoardSize() - 1 || toCol > this.board.get(toRow).size() - 1) {
      throw new IllegalArgumentException("given row or col you want to move to is too large");
    }
    if (Math.abs(fromRow - toRow) > 2 || Math.abs(fromCol - toCol) > 2 || Math.abs(fromRow - toRow)
            == 2 && Math.abs(fromCol - toCol) == 1) {
      throw new IllegalArgumentException("trying to move more or less than 2 spaces with a move");
    }
    int nextRow = this.findCoOrd(fromRow, toRow);
    int nextCol = this.findCoOrd(fromCol, toCol);

    if (this.getSlotAt(fromRow, fromCol).equals(SlotState.Marble) && this.canMoveUpDown(nextRow,
            nextCol, toRow, toCol)) {
      this.editState(fromRow, fromCol, SlotState.Empty);
      this.editState(nextRow, nextCol, SlotState.Empty);
      this.editState(toRow, toCol, SlotState.Marble);
    } else {
      throw new IllegalArgumentException("Move cannot be made");
    }
  }

  @Override
  protected boolean isValidMove(int row, int col) {
    if (!(this.getSlotAt(row, col).equals(SlotState.Marble))) {
      return false;
    }
    return this.canMoveUpDown(row + 1, col + 1, row + 2, col + 2)
            || this.canMoveUpDown(row + 1, col - 1, row + 2,
            col - 2)
            || this.canMoveUpDown(row - 1, col - 1, row - 2,
            col - 2)
            || this.canMoveUpDown(row - 1, col + 1, row - 2,
            col + 2)
            || this.canMoveUpDown(row, col + 1, row, col + 2)
            || this.canMoveUpDown(row, col - 1, row, col - 2)
            || this.canMoveUpDown(row + 1, col, row + 2, col)
            || this.canMoveUpDown(row - 1, col, row - 2, col);

  }
}
