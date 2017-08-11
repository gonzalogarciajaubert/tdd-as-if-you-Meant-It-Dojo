package com.company;

import java.util.Objects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
  Board board;
  GameEngine gameEngine;

  @Test
  void inAOneByOneBoardXWins() {
    board = new Board(2, 2);
    gameEngine = new GameEngine(board);
    board.oneOne = Board.X_TOKEN;
    String expected = GameEngine.X_WINS;
    String actual = gameEngine.resolver.xWins(board);

    assertEquals(expected, actual);
  }

  @Test
  void inAOneByTwoBoardIsADraw() {
    board = new Board(2, 2);
    gameEngine = new GameEngine(board);
    String expected = "isDraw";
    String board = "X"+"Y";
    String actual = isDraw(board);
    assertEquals(expected, actual);
  }


  @Test
  void inATwoByOneBoardIsADraw() {
    String expected = "isDraw";
    String board = "X|Y";
    String actual = isDraw(board);
    assertEquals(expected, actual);
  }

  @Test
  void inATwoByOneBoardOp2IsADraw() {
    String expected = "isDraw";
    String board = "Y|X";
    String actual = isDraw(board);
    assertEquals(expected, actual);
  }

  @Test
  void inATwoByTwoBoardXWins() {
    String expected = "x wins";
    String board = "XY|YX";
    String actual = xWins(board);
    assertEquals(expected, actual);
  }

  private String isDraw(String board) {
    String result = null;
    if (board.equals("XY") || board.equals("YX") || board.equals("X|Y") || board.equals("Y|X")) {
      result = "isDraw";
    }
    return result;
  }

  private String xWins(Board board) {
    String result = null;
    if (board.oneOne.equals(Board.X_TOKEN) || board.equals("XY|YX") || board.equals("YX|XY") || board.equals("XY|XY") || board.equals("YX|YX")) {
      result = "x wins";
    }
    return result;
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
  }

  class OneByOneResolver implements GameResolver {
    @Override public String xWins(Board board) {
      return board.oneOne.equals(Board.X_TOKEN) ? GameEngine.X_WINS : null;
    }
  }

  class TwoByTwoResolver implements GameResolver {
    @Override public String xWins(Board board) {
      boolean op1 = Objects.equals(board.oneOne, "X") && Objects.equals(board.oneTwo, "Y")
          && Objects.equals(board.twoOne, "Y") && Objects.equals(board.twoTwo, "X");

      boolean op2 = Objects.equals(board.oneOne, "Y") && Objects.equals(board.oneTwo, "X")
          && Objects.equals(board.twoOne, "X") && Objects.equals(board.twoTwo, "Y");

      boolean op3 = Objects.equals(board.oneOne, "X") && Objects.equals(board.oneTwo, "Y")
          && Objects.equals(board.twoOne, "X") && Objects.equals(board.twoTwo, "Y");

      boolean op4 = Objects.equals(board.oneOne, "Y") && Objects.equals(board.oneTwo, "X")
          && Objects.equals(board.twoOne, "Y") && Objects.equals(board.twoTwo, "X");

      return (op1 || op2 || op3 || op4) ? GameEngine.X_WINS : null;
    }
  }

  class GameEngine {
    public static final String IS_DRAW = "isDraw";
    public static final String X_WINS = "x wins";
    public static final String Y_WINS = "y wins";
    public  GameResolver resolver;
    public Board board;

    public GameEngine(Board board) {
      this.board = board;
      if (this.board.rows == 1 && this.board.columns == 1) {
        resolver = new OneByOneResolver();
      }
      if (this.board.rows == 2 && this.board.columns == 2) {
        resolver = new TwoByTwoResolver();
      }
    }
  }
}