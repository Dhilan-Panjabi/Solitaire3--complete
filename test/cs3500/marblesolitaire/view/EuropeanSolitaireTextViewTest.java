package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * test the european solitaire text view method.
 */
public class EuropeanSolitaireTextViewTest extends MarbleSolitaireTextViewTest {
  EuropeanSolitaireModel m1;

  MarbleSolitaireTextView b3;

  /**
   * represent the initial boards to test.
   */
  @Before
  public void init() {
    m1 = new EuropeanSolitaireModel();

    this.b3 = new MarbleSolitaireTextView(m1);
  }

  @Test
  public void testBoard() {
    init();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", b3.toString());
  }

  @Test
  public void testMove() {
    init();
    m1.move(5, 3, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O", b3.toString());
    m1.move(5, 1, 5, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  _ _ O O O\n" +
            "    O O O", b3.toString());
  }
}
