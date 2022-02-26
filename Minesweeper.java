import java.util.Scanner;

public class Minesweeper{
  public static void main(String[] args)
	{
		Run m = new Run();
	}
}

class Run
{
	Run()
	{
		Grid g = new Grid();
		g.printGrid();
		Scanner reader = new Scanner(System.in);
		while(!g.gameOver())
		{
			System.out.print("Check (1) or Flag(2)? ");
			int cf = reader.nextInt();
			System.out.print("Row: ");
			int r = reader.nextInt();
			System.out.print("Col: ");
			int c = reader.nextInt();
			if (cf == 2)
				g.flagSquare(r, c);
			else{
				g.checkSquare(r, c);
        if(g.gameOver())
        break;
      }
			g.printGrid();
		}
	}
}

class Grid
{
  private Square[][] grid;
  private boolean gameOver;

  Grid(){
    grid = new Square[9][9];
    for(int i = 0; i<9; i++){
      for(int j = 0; j<9; j++){
        grid[i][j] = new Square(i, j, false);
      }
    }

    int n = 0;
    do{
      int r = (int)(Math.random()*9);
      int rr = (int)(Math.random()*9);

      if(!(grid[r][rr].hasMine())){
        grid[r][rr].setMine(true);
        n++;
      }
    }while(n < 10);

    for(int r = 0; r<9; r++){
      for(int c = 0; c<9; c++){
        int mines = 0;
        if(r+1!=9 && grid[r+1][c].hasMine())
          mines++;

        if(r+1!=9 && c+1!=9 && grid[r+1][c+1].hasMine())
          mines++;

        if(r-1 != -1 && c+1!=9 && grid[r-1][c+1].hasMine())
          mines++;

        if(r+1 != 9 && c-1!=-1 && grid[r+1][c-1].hasMine())
          mines++;

        if(r-1!= -1 && c-1!=-1 && grid[r-1][c-1].hasMine())
          mines++;

        if(r-1!=-1 && grid[r-1][c].hasMine())
          mines++;

        if (c+1!=9 &&  grid[r][c+1].hasMine())
          mines++;

        if(c-1!=-1 && grid[r][c-1].hasMine())
          mines++;

        grid[r][c].setAdjMines(mines);
      }
    }
  }

  public void flagSquare(int r, int c){
    grid[r][c].setFlagged();
    int n = 0;
    if(grid[r][c].hasMine() && grid[r][c].isFlagged())
      n++;
    if(c == 10){
      gameOver = true;
      System.out.println("You flagged every mine.");
    }
  }

  public void checkSquare(int r, int c){
    if(grid[r][c].hasMine()){
      gameOver = true;
      System.out.println("You hit a bomb.");
      for(Square[] s: grid){
       for(Square ss: s){
         if(ss.hasMine()){
            ss.isChecked();
            System.out.print(s);
          }
        }
       System.out.println();
      }
    }
    grid[r][c].setChecked();
  }

  public void printGrid(){
    for(Square [] s: grid){
      for(Square ss: s)
        System.out.print(ss + " ");
      System.out.println();
    }
  }
  public void setGameOver(boolean a){
    gameOver = a;
  }
  public boolean gameOver()
  {
    return gameOver;
  }
}

class Square{
  private int r, c, m;
  private boolean checked, flagged, hasMine;

  Square(int r, int c, boolean m){
    this.r = r;
    this.c = c;
    this.hasMine = hasMine;
  }

  public boolean isChecked(){
    return checked;
  }

  public void setChecked(){
    checked = true;
  }

  public boolean isFlagged(){
    return flagged;
  }

  public void setFlagged(){
    flagged = !flagged;
  }

  public boolean hasMine(){
    return hasMine;
  }

  public void setMine(boolean x){
    hasMine = x;
  }

  public int getRow(){
    return r;
  }

  public int getCol(){
    return c;
  }

  public int getAdjMines()
  {
    return m;
  }

  public void setAdjMines(int n)
  {
    m = n;
  }

  public String toString(){
    if(flagged == true)
      return "F";

    if(hasMine == true && checked == true)
      return "*";

    if(hasMine == true)
      return "~";

    if(checked && m > 0)
      return "" + m;

    else if(m == 0 && checked == true)
      return " ";

    return "~";
  }
}