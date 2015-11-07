package algorithms;
import org.gamelink.game.Cram;
import org.gamelink.game.Algo;
import java.util.Scanner;

public class RNG extends Algo{ // Replace TeamName
    private static String teamName = "RNG"; // Replace TeamName

    public static String getTeamName(){
        return teamName;
    }

    public static void main(String[] args){
        Cram game = new Cram(false);
        game.startGame(RNG.class); // Replace TeamName
    }

    public static String algorithm(Cram game){
 
   /************************************************
    ************  PLACE ALGORITHM HERE  ************
    ************************************************/
	
	int[][] board = game.getBoard();

	String previous = null;
	String current = null;
	int previousJV = 99;
	int previousJH = 99;

	String []verticalMoves = new String[21];
	String []horizontalMoves = new String[21];
	int countVertical = 0;
	int countHorizontal = 0;
	for(int i = 0; i < 5; i++){
		for(int j = 0; j< 5; j++){
			if((board[j][i] != 1) && (board[j][i] != 2) && (board[j][i] != 99)){
				current = Integer.toString(j) + Integer.toString(i);
				if((previous != null) && (j-previousJV == 1) ){
					verticalMoves[countVertical] = previous + current;
					countVertical++;
				}
			}
			else{
				current = null;
			}
			previous = current;
			previousJV = j;
		}
	}
	
	for(int i = 0; i < 5; i++){
		for(int j = 0; j< 5; j++){
			if((board[i][j] != 1) && (board[i][j] != 2) && (board[i][j] != 99)){
				current = Integer.toString(i) + Integer.toString(j);
				if((previous != null) && (j-previousJH == 1) ){
					horizontalMoves[countHorizontal] = previous + current;
					countHorizontal++;
				}
			}
			else{
				current = null;
			}
			previous = current;
			previousJH = j;
		}
	}
	int totalMoves = countVertical + countHorizontal;
	System.out.println("Vertical: " + countVertical + " Horizontal: " + countHorizontal + "Total moves: " + totalMoves);
	
	
	double verticalOrHorizontal = 0;
	int randomMove = 0;
	if(countVertical == 0){
		verticalOrHorizontal = 1;
	}
	else if(countHorizontal == 0){
		verticalOrHorizontal = 0;
	}
	else{
		verticalOrHorizontal =  Math.random();
		if(verticalOrHorizontal < 0.5){
			randomMove = (int)(Math.random() * (countVertical - 1));
		}
		else{
			randomMove = (int)(Math.random() * (countHorizontal - 1));
		}
	}
	
	if(verticalOrHorizontal < 0.5)
		return verticalMoves[randomMove];
	else 
		
		return horizontalMoves[randomMove];
	
	
	/*
		Scanner in = new Scanner(System.in);
		String value = in.next();
		//System.out.println(value);
		return value;
		*/
		
    }
}
