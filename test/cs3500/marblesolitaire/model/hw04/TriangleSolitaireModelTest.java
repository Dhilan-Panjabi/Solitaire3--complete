package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;


/**
 * test the triangle solitaire methods.
 */
public class TriangleSolitaireModelTest {

  TriangleSolitaireModel board1;
  TriangleSolitaireModel board2;
  TriangleSolitaireModel board3;

  TriangleSolitaireModel board4;
  TriangleSolitaireModel board5;

  @Before
  public void init() {
    board1 = new TriangleSolitaireModel();
    board2 = new TriangleSolitaireModel(6);
    board3 = new TriangleSolitaireModel(3, 3);
    board4 = new TriangleSolitaireModel(3, 2, 0);
    board5 = new TriangleSolitaireModel(4, 4);
  }

  @Test
  public void testConstructor() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", new TriangleSolitaireTextView(board1).toString());
    assertEquals("     _\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O", new TriangleSolitaireTextView(board2).toString());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O _\n" +
            "O O O O O", new TriangleSolitaireTextView(board3).toString());
  }

  @Test
  public void testGetScore() {
    init();
    assertEquals(14, board1.getScore());
    assertEquals(20, board2.getScore());
    assertEquals(14, board3.getScore());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testFailConstructor() {
    init();
    new TriangleSolitaireModel(2, 6);
    new TriangleSolitaireModel(3, 6, 5);
    new TriangleSolitaireModel(5, 0, 5);
  }

  @Test
  public void testCorrectArgs() {
    assertEquals("  O\n" +
            " O O\n" +
            "_ O O", new TriangleSolitaireTextView(board4).toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMove() {
    init();
    board1.move(-1, 1, 0, 0);
    board1.move(0, 0, 2, 0);
    board2.move(2, 2, 4, 4);
    board2.move(4, 4, 2, 2);

  }

  @Test
  public void testIsGameOver() {
    init();
    board1.move(2, 0, 0, 0);
    assertEquals(false, board1.isGameOver());
    board1.move(2, 2, 2, 0);
    board1.move(0, 0, 2, 2);
    board1.move(3, 0, 1, 0);
    board1.move(3, 3, 1, 1);
    board1.move(4, 1, 2, 1);
    board1.move(4, 3, 4, 1);
    board1.move(4, 0, 4, 2);
    board1.move(4, 2, 2, 2);
    board1.move(1, 1, 3, 3);
    board1.move(4, 4, 2, 2);
    board1.move(2, 2, 2, 0);
    board1.move(2, 0, 0, 0);
    assertEquals(true, board1.isGameOver());
    assertEquals("    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _", new TriangleSolitaireTextView(board1).toString());
  }

  @Test
  public void testGetBoardSize() {
    init();
    assertEquals(5, this.board1.getBoardSize());
    assertEquals(6, this.board2.getBoardSize());
    assertEquals(3, this.board4.getBoardSize());
    assertEquals(5, this.board5.getBoardSize());

  }


}