package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * X always start
 */
class MainTest {
  Board board;
  GameEngine gameEngine;

  @Test
  void inAOneByOneBoardXWins() {
    board = new Board(1, 1);
    gameEngine = new GameEngine(board);
    board.oneOne = Board.X_TOKEN;

    String actual = gameEngine.resolve();

    String expected = GameEngine.X_WINS;
    assertEquals(expected, actual);
  }

  @Test
  void inAOneByTwoBoardIsADraw() {
    board = new Board(1, 2);
    gameEngine = new GameEngine(board);
    board.oneOne = Board.X_TOKEN;
    board.oneTwo = Board.Y_TOKEN;

    String actual = gameEngine.resolve();

    String expected = GameEngine.IS_DRAW;
    assertEquals(expected, actual);
  }

  @Test
  void inATwoByOneBoardIsADraw() {
    board = new Board(2, 1);
    gameEngine = new GameEngine(board);
    board.oneOne = Board.X_TOKEN;
    board.twoOne = Board.Y_TOKEN;

    String actual = gameEngine.resolve();

    String expected = GameEngine.IS_DRAW;
    assertEquals(expected, actual);
  }

  @Test
  void inATwoByOneBoardOp2IsADraw() {
    board = new Board(2, 1);
    gameEngine = new GameEngine(board);
    board.oneOne = Board.Y_TOKEN;
    board.twoOne = Board.X_TOKEN;

    String actual = gameEngine.resolve();

    String expected = GameEngine.IS_DRAW;
    assertEquals(expected, actual);
  }

  @Test
  void inATwoByTwoBoardXWins() {
    board = new Board(2, 2);
    gameEngine = new GameEngine(board);
    board.oneOne = Board.X_TOKEN;
    board.oneTwo = Board.Y_TOKEN;
    board.twoOne = Board.Y_TOKEN;
    board.twoTwo = Board.X_TOKEN;

    String actual = gameEngine.resolve();

    String expected = GameEngine.X_WINS;
    assertEquals(expected, actual);
  }

  class Board {
    public static final String X_TOKEN = "X";
    public static final String Y_TOKEN = "Y";
    public Integer rows;
    public Integer columns;
    public String oneOne;
    public String oneTwo;
    public String twoOne;
    public String twoTwo;

    public Board(Integer rows, Integer columns) {
      this.rows = rows;
      this.columns = columns;
    }
  }

  interface GameResolver {
    String xWins(Board board);

    String isDraw(Board board);
  }

  class OneByOneResolver implements GameResolver {
    @Override public String xWins(Board board) {
      return board.oneOne.equals(Board.X_TOKEN) ? GameEngine.X_WINS : null;
    }

    @Override public String isDraw(Board board) {
      return null;
    }
  }

  class OneByTwoResolver implements GameResolver {

    @Override public String xWins(Board board) {
      return null;
    }

    @Override public String isDraw(Board board) {
      boolean op1 = board.oneOne.equals(Board.X_TOKEN) && board.oneTwo.equals(Board.Y_TOKEN);
      boolean op2 = board.oneOne.equals(Board.Y_TOKEN) && board.oneTwo.equals(Board.X_TOKEN);
      return (op1 || op2) ? GameEngine.IS_DRAW : null;
    }
  }

  class TwoByOneResolver implements GameResolver {

    @Override public String xWins(Board board) {
      return null;
    }

    @Override public String isDraw(Board board) {
      boolean op1 = board.oneOne.equals(Board.X_TOKEN) && board.twoOne.equals(Board.Y_TOKEN);
      boolean op2 = board.oneOne.equals(Board.Y_TOKEN) && board.twoOne.equals(Board.X_TOKEN);
      return (op1 || op2) ? GameEngine.IS_DRAW : null;
    }
  }

  class TwoByTwoResolver implements GameResolver {
    @Override public String xWins(Board board) {
      boolean op1 = board.oneOne.equals(Board.X_TOKEN)
          && board.oneTwo.equals(Board.Y_TOKEN)
          && board.twoOne.equals(Board.Y_TOKEN)
          && board.twoTwo.equals(Board.X_TOKEN);
      boolean op2 = board.oneOne.equals(Board.Y_TOKEN)
          && board.oneTwo.equals(Board.X_TOKEN)
          && board.twoOne.equals(Board.X_TOKEN)
          && board.twoTwo.equals(Board.Y_TOKEN);
      boolean op3 = board.oneOne.equals(Board.X_TOKEN)
          && board.oneTwo.equals(Board.Y_TOKEN)
          && board.twoOne.equals(Board.X_TOKEN)
          && board.twoTwo.equals(Board.Y_TOKEN);
      boolean op4 = board.oneOne.equals(Board.Y_TOKEN)
          && board.oneTwo.equals(Board.X_TOKEN)
          && board.twoOne.equals(Board.Y_TOKEN)
          && board.twoTwo.equals(Board.X_TOKEN);

      return (op1 || op2 || op3 || op4) ? GameEngine.X_WINS : null;
    }

    @Override public String isDraw(Board board) {
      return null;
    }
  }

  class ResolverFactory {
    public GameResolver getResolver(Board board) {
      if (board == null) {
        return null;
      }
      if (board.rows == 1 && board.columns == 1) {
        return new OneByOneResolver();
      }
      if (board.rows == 1 && board.columns == 2) {
        return new OneByTwoResolver();
      }
      if (board.rows == 2 && board.columns == 1) {
        return new TwoByOneResolver();
      }
      if (board.rows == 2 && board.columns == 2) {
        return new TwoByTwoResolver();
      }
      return null;
    }
  }

  class GameEngine {
    public static final String IS_DRAW = "isDraw";
    public static final String X_WINS = "x wins";
    public static final String Y_WINS = "y wins";
    public GameResolver resolver;
    public Board board;

    public GameEngine(Board board) {
      this.board = board;
      this.resolver = new ResolverFactory().getResolver(board);
    }

    public String resolve() {
      String isDraw = resolver.isDraw(board);
      String xWins = resolver.xWins(board);
      if (isDraw == IS_DRAW) {
        return IS_DRAW;
      } else if (xWins == X_WINS) {
        return X_WINS;
      } else {
        return Y_WINS;
      }
    }
  }
}