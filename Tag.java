import java.util.Scanner;

public class Tag {
  public static void main(String[] args) {
    Player p1 = new Player(1, 0, 4);
    Player p2 = new Player(2, 0, 0);
    BoardGame b = new BoardGame(p1, p2);
    Scanner reader = new Scanner(System.in);
    b.introduction();
    System.out.println("\nPlayer 1 will be the Runner & Player 2 will be the chaser.\n");
    int c = 0;
    String n = "";
    b.printBoard();
    do{
      System.out.println("\nPress ENTER to go.");
      n = reader.nextLine();
      b.takeTurn(p1);
      b.takeTurn(p2);
      System.out.println();
      b.printBoard();
      c++;
    }while(b.checkWinner() == false && c < 20 && n.equals(""));
    System.out.println();
    if(b.checkWinner() == true)
      System.out.println("Chaser caught the Runner in "+c+" turns!");
    else
      System.out.println("Runner beat the Chaser!");
  }
}

class BoardGame implements Game {
  private Space[][] board;
  private Player runner;
  private Player chaser;

  BoardGame(Player runner, Player chaser) {
    this.runner = runner;
    this.chaser = chaser;
    board = new Space[10][10];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = new Space();
        int r1 = (int) ((Math.random() * 10) + 1);
        int r2 = (int) ((Math.random() * 10) + 1);
        if (i == r1 || j == r2) {
          int r = (int) ((Math.random() * 3) + 1);
          if (r == 1)
            board[i][j] = new Space("+");
          if (r == 2)
            board[i][j] = new Space("X");
        }
      }
    }
    board[runner.getPPosR()][runner.getPPosC()] = new Space("R");
    board[chaser.getPPosR()][chaser.getPPosC()] = new Space("C");
  }

  public void introduction() {
    System.out.println(
        "Welcome to The Chase! The chaser will have to chase the runner within 20 rounds. If the chaser surpasses or ends up in the same position as the runner, the chaser wins. If the runner out runs the chaser with the special spaces in under 20 rounds, the runner wins. Runner starts with a 2 space headstart.");
  }

  public void printBoard() {
    for (Space[] a : board) {
      for (Space b : a) {
        System.out.print(b + " ");
      }
      System.out.println();
    }
  }

  public void takeTurn(Player p) {
    if(p.getPNum() != 1 || p.getPNum() != 2)
      board[p.getPPosR()][p.getPPosC()] = new Space("~");
    int dRoll = 0;
    dRoll = (int) ((Math.random() * 4) + 1);
    System.out.println("Player " + p.getPNum() + " rolls a " + dRoll);

    if(p.getPPosC() == 0){
      p.setPPosC(dRoll);
      if(board[p.getPPosR()][p.getPPosC()].toString().equals("X")){
        System.out.println("Player "+p.getPNum()+" hit a road block! Return to original position.");
        p.setPPosC(-dRoll);
      }
      else if(board[p.getPPosR()][p.getPPosC()].toString().equals("+")){
        System.out.println("Player "+p.getPNum()+" hit a booster! Skip 5 spaces.");
        p.setPPosC(5);
        if(p.getPPosC() > 9){
          p.setPPosR(1);
          p.setPPosC(-10);
        }
      }
    }

    else if(p.getPPosC() < 9 && p.getPPosC() > 0){
      if(9-p.getPPosC() < 4){
        if(dRoll > 9-p.getPPosC()){
          p.setPPosR(1);
          p.setPPosC(-10+dRoll);
          if(board[p.getPPosR()][p.getPPosC()].toString().equals("X")){
            System.out.println("Player "+p.getPNum()+" hit a road block! Return to original position.");
            p.setPPosR(-1);
            p.setPPosC(10-dRoll);
          }
          else if(board[p.getPPosR()][p.getPPosC()].toString().equals("+")){
            System.out.println("Player "+p.getPNum()+" hit a booster! Skip 5 spaces.");
            p.setPPosC(5);
          }
        }

        else
          p.setPPosC(dRoll);
          if(board[p.getPPosR()][p.getPPosC()].toString().equals("X")){
            System.out.println("Player "+p.getPNum()+" hit a road block! Return to original position.");
            p.setPPosC(-dRoll);
          }
          else if(board[p.getPPosR()][p.getPPosC()].toString().equals("+")){
            System.out.println("Player "+p.getPNum()+" hit a booster! Skip 5 spaces.");
            p.setPPosC(5);
            if(p.getPPosC() > 9){
              p.setPPosR(1);
              p.setPPosC(-10);
            }
          }
      }

      else{
        p.setPPosC(dRoll);
        if(board[p.getPPosR()][p.getPPosC()].toString().equals("X")){
          System.out.println("Player "+p.getPNum()+" hit a road block! Return to original position.");
          p.setPPosC(-dRoll);
        }
        else if(board[p.getPPosR()][p.getPPosC()].toString().equals("+")){
          System.out.println("Player "+p.getPNum()+" hit a booster! Skip 5 spaces.");
          p.setPPosC(5);
          if(p.getPPosC() > 9){
            p.setPPosR(1);
            p.setPPosC(-10);
          }
        }
      }
    }

    else if(p.getPPosC() == 9){
      p.setPPosC(-9);
      p.setPPosR(1);
      p.setPPosC(dRoll-1);
      if(board[p.getPPosR()][p.getPPosC()].toString().equals("X")){
        System.out.println("Player "+p.getPNum()+" hit a road block! Return to original position.");
        p.setPPosR(-1);
        p.setPPosC(10-dRoll);
      }
      else if(board[p.getPPosR()][p.getPPosC()].toString().equals("+")){
        System.out.println("Player "+p.getPNum()+" hit a booster! Skip 5 spaces.");
        p.setPPosC(5);
      }
    }

    if (p.getPNum() == 1)
      board[p.getPPosR()][p.getPPosC()] = new Space("R");
    else if (p.getPNum() == 2)
      board[p.getPPosR()][p.getPPosC()] = new Space("C");
  }

  public boolean checkWinner() {
    if (runner.getPPosR() < chaser.getPPosR()) {
      return true;
    }
    else if (runner.getPPosR() == chaser.getPPosR()) {
      if (runner.getPPosC() < chaser.getPPosC() ||runner.getPPosC() == chaser.getPPosC()) {
        return true;
      }
      else {
        return false;
      }
    }
    return false;
  }
}

interface Game {
  public void introduction();

  public void printBoard();

  public void takeTurn(Player p);

  public boolean checkWinner();

}

class Player{
  private int pNum, pPosR, pPosC;

  Player(int pNum, int pPosR, int pPosC){
    this.pNum = pNum;
    this.pPosR = pPosR;
    this.pPosC = pPosC;
  }

  public int getPNum(){
    return pNum;
  }

  public int getPPosR(){
    return pPosR;
  }

  public int getPPosC(){
    return pPosC;
  }

  public void setPPosR(int n){
    pPosR += n;
  }

  public void setPPosC(int n){
    pPosC += n;
  }
}

class Space {
  private int pNum;
  private String space;

  Space() {
    space = "~";
  }

  public Space(String specialSpace) {
    space = specialSpace;
  }

  public int getPNum() {
    return pNum;
  }

  /*public boolean isSpecialSpace(String specialSpace){
    if(specialSpace.equals("<") || specialSpace.equals(">"))
      return true;
    return false;
  }*/

  public String toString() {
    return space;
  }
}
