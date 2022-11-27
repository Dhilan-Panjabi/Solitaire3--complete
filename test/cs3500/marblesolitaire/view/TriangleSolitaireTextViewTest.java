package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * test triangle view tests.
 */
public class TriangleSolitaireTextViewTest {

  TriangleSolitaireModel board1;

  TriangleSolitaireTextView t2;

  TriangleSolitaireModel board2;

  TriangleSolitaireTextView t1;

  /**
   * inital conditions to test with.
   */
  @Before
  public void init() {
    board1 = new TriangleSolitaireModel();

    board2 = new TriangleSolitaireModel(4, 4);
    this.t2 = new TriangleSolitaireTextView(board1);
    this.t1 = new TriangleSolitaireTextView(board2);
  }

  @Test
  public void testView() {
    init();
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", t2.toString());

  }

  @Test
  public void testMoveWithBoard() {
    init();
    board1.move(2, 0, 0, 0);
    // into the corner of triangle
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", t2.toString());
    board1.move(4, 0, 2, 0);
    //out of corner of triangle
    assertEquals("    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "_ O O O O", t2.toString());
    board1.move(3, 2, 3, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "_ O O O O", t2.toString());
    board1.move(2, 0, 4, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " _ _ _ O\n" +
            "O O O O O", t2.toString());
  }


  @Test
  public void testMoveRight() {
    init();
    board1.move(2, 2, 0, 0);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O", t2.toString());
    board1.move(2, 0, 2, 2);
    assertEquals("    O\n" +
            "   O _\n" +
            "  _ _ O\n" +
            " O O O O\n" +
            "O O O O O", t2.toString());
    board2.move(2, 2, 4, 4);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O _\n" +
            "O O O O O", t1.toString());
  }
}



