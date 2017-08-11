package com.company;

import java.util.Objects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO javadoc
 */
class MainTest {

  @Test
  void main() {
  }

  @Test
  void inAOneByOneBoardXWins() {
    String expected = "x wins";
    String board = "X";
    String actual = xWins(board);
    assertEquals(expected, actual);
  }

  @Test
  void inAOneByTwoBoardIsADraw() {
    String expected = "isDraw";
    String board = "XY";
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

  private String isDraw(String board) {
    String result = null;
    if (board.equals("XY") || board.equals("YX") || board.equals("X|Y") || board.equals("Y|X")) {
      result = "isDraw";
    }
    return result;
  }

  private String xWins(String board) {
    String result = null;
    if (Objects.equals(board, "X")) {
      result = "x wins";
    }
    return result;
  }
}