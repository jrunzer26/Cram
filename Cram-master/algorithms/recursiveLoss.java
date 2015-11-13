package algorithms;
import org.gamelink.game.Cram;
import org.gamelink.game.Algo;
import java.util.Scanner;

public class recursiveLoss extends Algo{ // Replace TeamName
    private static String teamName = "recursiveLoss"; // Replace TeamName

    public static String getTeamName(){
    	return teamName;
    }

    public static void main(String[] args){
    	Cram game = new Cram(false);
        game.startGame(recursiveLoss.class); // Replace TeamName
    }

    public static String algorithm(Cram game){

   /************************************************
    ************  PLACE ALGORITHM HERE  ************
    ************************************************/

	//We store the current board each time it is our turn
   int[][] board = game.getBoard();
   //Create an array to store all the move that are possible in the board
   String [] moves;

   //Initalize variables
   int count = 0;
   int k = 0;

   //--------------------------------------------------------------------------//
   for(int i=0; i < (board.length); i++)
   {

   	for(int j =0; j< (board[0].length); j++)
   	{
   		System.out.print(" "+ board[i][j]);
   	}
   	System.out.println("");
   }
   //--------------------------------------------------------------------------//
		
   //Calls the function findMoves with the parameter of the current board and sends the results to the moves array
   moves = findMoves(board);

   //Creates an array to store the number of losses each move will get
   int loss[] = new int[42];

   //Checks to see if there are anymore moves left in the array
   while(moves[k]!= null)
   {
   	System.out.println(moves[k]);
   	k++;

   }
   System.out.println(k);

   /*We will only run our algorithm if there are less then 24 possible moves on the board,
   the reason for this is becasue of the computer speed we would not be able to calculate where to play
   if the number of moves is greater then 24*/
   if(k > 24)
   {
   	//Plays the last move in the moves array
   	return moves[k-1];
   }

   //While there are still moves to check this while loop will keep on executing
   while(moves[count] != null)
   {
   	System.out.println(moves[count]);


   	System.out.println("Move: " + moves[count]);

   	//Sets one of the possible moves in the moves array equal to 1 (meaning the spot can no longer be played in)
   	board[(int)moves[count].charAt(0) -48][(int)moves[count].charAt(1)-48] = 1;
   	board[moves[count].charAt(2) - 48][moves[count].charAt(3) - 48] = 1;


   	//Enters the calculateLoss function and passes in the board after setting the moves made above to 1 and we pass in true, which repersents it is our turn
   	loss[count] = calculateLoss(board,true);

   	//The move you previously set to 1 is not set back to 0 (meaning the spot can be played on)
   	board[(int)moves[count].charAt(0) -48][(int)moves[count].charAt(1)-48] = 0;
   	board[moves[count].charAt(2) - 48][moves[count].charAt(3) - 48] = 0;

			//System.out.println("loss for move: " + count + " = " + loss[count]);
   	System.out.println("Move: " + moves[count]);

   	//Increases the counter so next time in the while loop we can check the next possible move in the array and pass the new board into calculateLoss
   	count++;
   }


   int min = loss[0];
   int indexMin = 0;
   for(int i = 0; i < count; i++){
   	System.out.println(loss[i]);
   	if (loss[i] <= min){
   		min = loss[i];
   		indexMin = i;
				//System.out.println("max index " + indexMax);
   	}

   }

   //Plays the move with the fewest losses
   return moves[indexMin];


}

/* calculateLoss - Parameters: newBoard, and boolean value
 * Returns an integer value 
 * The purpose of this function is it plays though every permutation of the move we passed into the board, it does this by checking to see how many losses the move we played
 * will have and then we return number of losses into the loss array at the index that corresponds to the move we just played from the moves array
 */
public static int calculateLoss(int [][] newBoard,boolean turn){

	//The variable for the number of losses
	int loss = 0;

	//Stores all the possible moves into the movoes array
	String [] moves = findMoves(newBoard);
		
		int i = 0;

		//If there are still moves to be played increase the value of i by one
		while(moves[i] != null){
			i++;
		}

		//If there are no moves in the array
		if(i == 0){
			if(!turn){
				return 1;
			}
			else{
				return 0;
			}
		}
		
		
		if(turn == false){
			turn = true;
		}
		else
			turn = false;
		

		
		int index = 0;

		while(moves[index] != null){
			//System.out.println("got in the while loop of calculate moves");
			newBoard[moves[index].charAt(0) - 48][moves[index].charAt(1) - 48] = 1;
			newBoard[moves[index].charAt(2) -48][moves[index].charAt(3) - 48] = 1;
			loss += calculateLoss (newBoard,turn);
			newBoard[moves[index].charAt(0) - 48][moves[index].charAt(1) - 48] = 0;
			newBoard[moves[index].charAt(2) -48][moves[index].charAt(3) - 48] = 0;
			index++;
		}
		//System.out.println("Retunring loss:  " + loss);

		return loss;	
	}
	
	public static String [] findMoves(int [][] newBoard){
		String previous = null;
		String current = null;
		int previousJV = 99;
		int previousJH = 99;
		int counterIndex = 0;

		
		String []totalMoves = new String[42];

		for(int i = 0; i < (newBoard.length); i++){
			for(int j = 0; j< (newBoard[0].length); j++){
				if((newBoard[j][i] != 1) && (newBoard[j][i] != 2) && (newBoard[j][i] != 99)){
					current = Integer.toString(j) + Integer.toString(i);
					if((previous != null) && (j-previousJV == 1) ){
						totalMoves[counterIndex] = previous + current;
						counterIndex++;
					}
				}
				else{
					current = null;
				}
				previous = current;
				previousJV = j;
			}
		}
		
		for(int i = 0; i < (newBoard.length); i++){
			for(int j = 0; j< (newBoard[0].length); j++){
				if((newBoard[i][j] != 1) && (newBoard[i][j] != 2) && (newBoard[i][j] != 99)){
					current = Integer.toString(i) + Integer.toString(j);
					if((previous != null) && (j-previousJH == 1) ){
						totalMoves[counterIndex] = previous + current;
						counterIndex++;
					}
				}
				else{
					current = null;
				}
				previous = current;
				previousJH = j;
			}
		}
		//System.out.println("Total moves: " + counterIndex);
		return totalMoves;
	}
	
}
