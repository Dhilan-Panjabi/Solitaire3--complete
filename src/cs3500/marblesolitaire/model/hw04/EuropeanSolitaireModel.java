package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

/**
 *Represents the european solitaire game.
 */
public class EuropeanSolitaireModel extends AbstractTModel {


  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  public EuropeanSolitaireModel(int row, int col) throws IllegalArgumentException {
    super(3, row, col);
  }

  public EuropeanSolitaireModel(int thickness) {
    super(thickness, (thickness + (((2 * thickness) - 2))) / 2,
            (thickness + (((2 * thickness)) - 2)) / 2);
  }

  public EuropeanSolitaireModel(int thickness, int row, int col) throws IllegalArgumentException {
    super(thickness, row, col);
  }

  @Override
  protected List<List<SlotState>> fullBoard(int row, int col, int thickness)
          throws IllegalArgumentException {
    List<List<SlotState>> board = new ArrayList<List<SlotState>>();
    if (thickness < 3 || thickness % 2 == 0) {
      throw new IllegalArgumentException("Given Thickness is Invalid");
    }
    int length = thickness + ((thickness - 1) * 2);
    int lim = thickness - 1;
    int euRow = 0;

    while (euRow < length) {
      List<SlotState> b = new ArrayList<SlotState>();
      int euCol = 0;
      while (euCol < length) {
        if ((euCol < lim && euRow < thickness - 1)
                || (euCol >= length - lim && euRow < thickness - 1)
                || (euCol >= length - lim && euRow >= length - (thickness - 1))
                || (euCol < lim && euRow >= length - (thickness - 1))) {
          b.add(SlotState.Invalid);

        } else {
          b.add(SlotState.Marble);
        }
        euCol = euCol + 1;
      }
      board.add(b);
      if (euRow < thickness - 1) {
        lim = lim - 1;

      }
      euRow = euRow + 1;

      if (euRow > length - (thickness)) {
        lim = lim + 1;
      }
    }
    return this.checkIfEmpty(board, row, col, length);
  }
}



