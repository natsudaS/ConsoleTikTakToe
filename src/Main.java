import java.util.Scanner;

public class Main {
  static int[][] field = new int[3][3];
  static int moves = 0;
  static int countWin;
  static int check;
  static boolean winnerFound = false;

  public static void main(String[] args) {
    Scanner startScan = new Scanner(System.in);
    System.out.println("Press Enter to start the game...");
    startScan.nextLine(); // Wait for the user to press Enter
    startGame();
    startScan.close();
  }

  private static void startGame(){
    initializeField();
    while (winnerFound == false) {
      setMark();
    }
    System.out.println("Player " + printWinner() + " won!");
  }

  // field related methods
  private static void initializeField() {
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field.length; j++) {
        field[i][j] = 0;
      }
    }
    printField();
  }

  private static void printField() {
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field.length; j++) {
        System.out.print(field[i][j]);
      }
      System.out.println();
    }
  }

  // setting marks
  private static int getCurrentPlayer() {
    int player = 0;

    if (moves % 2 == 0) {
      player = 1;
    } else {
      player = 2;
    }

    return player;
  }

  private static void setMark() { // int column, int row, int player
    int p = getCurrentPlayer();
    System.out.println("Player " + p + " set your mark.");
    System.out.print("row(0-2): ");
    Scanner scanner = new Scanner(System.in);
    int r = scanner.nextInt();
    System.out.print("col(0-2): ");
    int c = scanner.nextInt();

    if (c >= 0 && c <= 2 && r >= 0 && r <= 2) { // if that's not the case: IndexOutOfBound Exception
      if (field[r][c] == 0) {
        field[r][c] = p;
        moves++;
        System.out.println("Moves: " + moves);
        printField();
      } else {
        System.out.println("You can't place your marker here.");
        setMark();
      }
    }
    checkWin(p);
    if(winnerFound == true){
      scanner.close();
    }
  }

  // check for win
  private static boolean checkWin( int p) {
    if (checkHorizontal(p) == true || checkVertical(p) == true || checkDiagonal(p) == true)
    {
      winnerFound = true;
    } else {
      winnerFound = false;
    }
    return winnerFound;
  }

  private static boolean checkHorizontal(int p) {
    boolean win = false;
    for (int i = 0; i < field.length; i++) {
      if (field[i][0] == p && field[i][1] == p && field[i][2] == p) {
        win = true;
      }
    }
    return win;
  }

  private static boolean checkVertical(int p) {
    boolean win = false;
    for (int j = 0; j < field.length; j++){
      if (field[0][j] == p && field[1][j] == p && field[2][j] == p)
      {
        win = true;
      }
    }
    return win;
  }

  private static boolean checkDiagonal(int p) {
    boolean win = false;
    if (field[0][0] == p && field[1][1] == p && field[2][2] == p) {
      win = true;
    } else if (field[0][2] == p && field[1][1] == p && field[2][0] == p) {
      win = true;
    }
    return win;
  }

  private static int printWinner() {
    int winner = 0;

    if (getCurrentPlayer() == 1) {
      winner = 2;
    }
    if (getCurrentPlayer() == 2) {
      winner = 1;
    }

    return winner;
  }
}