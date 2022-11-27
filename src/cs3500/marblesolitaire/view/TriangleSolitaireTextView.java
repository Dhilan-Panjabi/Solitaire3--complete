package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the board for the triangle view.
 */
public class TriangleSolitaireTextView extends AbstractView {

  public TriangleSolitaireTextView(MarbleSolitaireModelState arg) throws IllegalArgumentException {
    super(arg);
  }

  public TriangleSolitaireTextView(MarbleSolitaireModelState arg, Appendable appendable)
          throws IllegalArgumentException {
    super(arg, appendable);
  }

  @Override
  public String toString() {
    String base = "";
    int bSize = arg.getBoardSize();
    int row = 0;
    while (row <= bSize - 1) {
      int column = 0;
      int space = 0;
      while (space < bSize - (row + 1)) {
        base = base + " ";
        space = space + 1;
      }
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
}




