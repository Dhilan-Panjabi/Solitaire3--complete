package cs3500.marblesolitaire;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * plays a given game in the main class.
 */
public final class MarbleSolitaire {
  /**
   * The method that takes in a given type of games and plays it.
   * @param args array of inputs.
   */
  public static void main(String[] args) {
    List<Integer> a;
    MarbleSolitaireController controller;

    int lastArgs = 0;
    for (String str : args) {
      switch (str) {
        case "english":
          a = new ArrayList<Integer>(Arrays.asList(3, 3, 3));
          a = getP(args, lastArgs, a);
          controller = new MarbleSolitaireControllerImpl(new EnglishSolitaireModel()
                  , new MarbleSolitaireTextView(new EnglishSolitaireModel()),
                  new InputStreamReader(System.in));
          controller.playGame();
          break;

        case "triangular":
          a = new ArrayList<Integer>(Arrays.asList(3, 3, 3));
          a = getP(args, lastArgs, a);
          controller = new MarbleSolitaireControllerImpl(
                  new TriangleSolitaireModel(), new TriangleSolitaireTextView(
                          new TriangleSolitaireModel()), new InputStreamReader(System.in));
          controller.playGame();
          break;

        case "european":
          a = new ArrayList<Integer>(Arrays.asList(5, 0, 0));
          a = getP(args, lastArgs, a);
          controller = new MarbleSolitaireControllerImpl(
                  new EuropeanSolitaireModel(), new MarbleSolitaireTextView(
                          new EuropeanSolitaireModel()), new InputStreamReader(System.in));
          controller.playGame();
          break;

        default:
          System.out.println("");
      }
      lastArgs++;
    }
  }

  private static List<Integer> getP(String[] args, int lastArgs, List<Integer> a) {
    int i = lastArgs;
    for (String str : args) {
      switch (str) {
        case "-size":
          try {
            a.set(0, Integer.parseInt(args[i + 1]));

          } catch (NumberFormatException e) {
            System.out.println("enter a number");
          }
          break;

        case "-hole":
          try {
            a.set(1, Integer.parseInt(args[i + 1]));
            a.set(2, Integer.parseInt(args[i + 2]));
          } catch (NumberFormatException e) {
            System.out.println("enter a number");
          }
          break;
        default:
          System.out.println("unknown command");
      }
      i++;
    }
    return a;
  }
}