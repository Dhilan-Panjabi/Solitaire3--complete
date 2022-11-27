package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * to view the state of the board.
 */
public class MarbleSolitaireTextView extends AbstractView {


  public MarbleSolitaireTextView(MarbleSolitaireModelState arg) {
    super(arg);
  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState a, Appendable ap) throws
          IllegalArgumentException {
    super(a, ap);

  }

}



