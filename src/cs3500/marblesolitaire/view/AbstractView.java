package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract class with the methods used for tostring, renderboard, rendermessage.
 */
public abstract class AbstractView implements MarbleSolitaireView {
  protected Appendable appendable;
  protected MarbleSolitaireModelState arg;

  /**
   * Takes in the state.
   *
   * @param arg state
   */
  public AbstractView(MarbleSolitaireModelState arg) throws IllegalArgumentException {
    if (arg == null) {
      throw new IllegalArgumentException("cannot be null");
    }
    this.arg = arg;
    this.appendable = System.out;
  }

  /**
   * MarbleSolitaireTextView of a model and an appendable.
   *
   * @param arg        given marble solitaire model state
   * @param appendable the given appendable
   * @throws IllegalArgumentException if any of the paramters are null
   */
  public AbstractView(MarbleSolitaireModelState arg, Appendable appendable) throws
          IllegalArgumentException {
    if (arg == null || appendable == null) {
      throw new IllegalArgumentException("null paramters");
    }
    this.arg = arg;
    this.appendable = appendable;
  }

  /**
   * loops through the positions on the board and checks the state to append a different position.
   * depending on the slotstate.
   *
   * @return toString of string builder
   */
  @Override
  public String toString() {
    String base = "";
    int bSize = arg.getBoardSize();
    int row = 0;
    while (row <= bSize - 1) {
      int column = 0;
      while (column <= bSize - 1) {
        MarbleSolitaireModelState.SlotState s = arg.getSlotAt(row, column);
        if (s.equals(MarbleSolitaireModelState.SlotState.Empty)) {
          if (column == bSize - 1 || arg.getSlotAt(row, column + 1).equals(
                  MarbleSolitaireModelState.SlotState.Invalid)) {
            base = base + "_";
            column = bSize;
          } else {
            base = base + "_ ";
          }
        } else if (s.equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          base = base + "  ";
        } else if (s.equals(MarbleSolitaireModelState.SlotState.Marble)) {
          if (column == bSize - 1) {
            base = base + "O";
            column = bSize;
          } else if (arg.getSlotAt(row, column + 1).equals(
                  MarbleSolitaireModelState.SlotState.Invalid)) {
            base = base + "O";
            column = bSize;
          } else {
            base = base + "O ";
          }
        }
        column = column + 1;
      }
      if (row < bSize - 1) {
        base = base + "\n";
      } else {
        base = base + "";
      }
      row = row + 1;
    }
    return base;
  }

  @Override
  public void renderBoard() throws IOException {
    this.renderMessage(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}

