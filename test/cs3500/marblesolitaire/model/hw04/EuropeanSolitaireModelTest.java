package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import static org.junit.Assert.assertEquals;

/**
 * Test the european model methods.
 */
public class EuropeanSolitaireModelTest {

  EuropeanSolitaireModel board1;

  EuropeanSolitaireModel board2;

  EuropeanSolitaireModel board3;

  MarbleSolitaireTextView e1;
  MarbleSolitaireTextView e2;
  MarbleSolitaireTextView e3;

  @Before
  public void init() {
    board1 = new EuropeanSolitaireModel();

    board2 = new EuropeanSolitaireModel(5);

    board3 = new EuropeanSolitaireModel(2, 3);

    e1 = new MarbleSolitaireTextView(board1);

    e2 = new MarbleSolitaireTextView(board2);

    e3 = new MarbleSolitaireTextView(board3);
  }

  @Test
  public void diffConstructor() {
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", e1.toString());
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", e2.toString());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", e3.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    new EuropeanSolitaireModel(1);
    new EuropeanSolitaireModel(2, 3);
    new EuropeanSolitaireModel(-2, 3);
    new EuropeanSolitaireModel(7);
    new EuropeanSolitaireModel(-3, 2, 2);
  }

  @Test
  public void testGetScore() {
    init();
    assertEquals(36, this.board1.getScore());
    assertEquals(128, this.board2.getScore());
    assertEquals(36, this.board3.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToEmpty() {
    board1.move(3, 3, 1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameSpace() {
    board1.move(3, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDiagonal() {
    board1.move(3, 3, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveMoreThan2() {
    board2.move(2, 3, 5, 3);
    board2.move(2, 3, 2, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOnly1() {
    board1.move(2, 3, 3, 3);
    board2.move(2, 3, 2, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOutBounds() {
    board1.move(-1, 4, 1, 4);
    board1.move(1, 7, 1, 3);
    board1.move(1, 3, -1, 3);
    board1.move(-1, 3, 1, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameSpot() {
    board1.move(2, 3, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    board1.move(0, 0, 2, 3);
  }

  @Test
  public void testGetSlot() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, board1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, board1.getSlotAt(0, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, board1.getSlotAt(6, 5));
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, board1.getBoardSize());
    assertEquals(13, board2.getBoardSize());
    assertEquals(7, board3.getBoardSize());
  }


}