package cs3500.marblesolitaire.model.hw02;


import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw04.AbstractTModel;

/**
 * Represents English Solitaire Model which has four constructors to build a board given the
 * parameters. It creates a solitaire board where the player needs to move the marble to complete
 * the game.
 */
public class EnglishSolitaireModel extends AbstractTModel {
  /**
   * Constructor with no Parameters.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * rows and column of empty slot.
   * set armThickness to 3.
   * Row and Col start from (0, 0).
   *
   * @param row empty slot
   * @param col empty slot
   * @throw IllegalArgumentException if invalid cell pos
   */
  public EnglishSolitaireModel(int row, int col) {
    super(3, row, col);
  }

  /**
   * Takes in the armThickness.
   * Make an empty slot in the center given the armThickness.
   *
   * @param armThickness num of marbles in top row
   * @throws IllegalArgumentException if the armThickness is not a positive num
   */
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness, (armThickness * 3 - 3) / 2, (armThickness * 3 - 3) / 2);
  }

  /**
   * Creates a board given each parameter thickness, row and col.
   *
   * @param thickness the arm size of each side of the board
   * @param row       row of initial empty space
   * @param col       col of init empty space
   * @throws IllegalArgumentException if given thickness is negative or not even and the given
   *                                  row and col are invalid
   */
  public EnglishSolitaireModel(int thickness, int row, int col) throws IllegalArgumentException {
    super(thickness, row, col);
  }

  @Override
  protected List<List<SlotState>> fullBoard(int row, int col, int thickness)
          throws IllegalArgumentException {

    List<List<SlotState>> board = new ArrayList<List<SlotState>>();
    if (thickness <= 2 || thickness % 2 == 0) {
      throw new IllegalArgumentException("Given Thickness is Invalid");
    }
    int length = thickness + ((2 * thickness) - 2);
    int lim = thickness - 1;
    int enRow = 0;
    while (enRow < length) {
      List<SlotState> b = new ArrayList<SlotState>();
      int enCol = 0;
      while (enCol < length) {
        if ((enCol <= lim - 1 && enRow <= lim - 1)
                || (enCol >= length - lim && enRow <= lim - 1)
                || (enCol >= length - lim && enRow >= length - lim)
                || (enCol <= lim - 1 && enRow >= length - lim)) {
          b.add(SlotState.Invalid);
        } else {
          b.add(SlotState.Marble);
        }
        enCol++;
      }
      board.add(b);
      enRow++;
    }
    return this.checkIfEmpty(board, row, col, length);
  }
}


