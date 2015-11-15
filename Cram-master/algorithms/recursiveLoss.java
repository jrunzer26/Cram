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
   moves = findMoves(board, 38);

   //Creates an array to store the number of losses each move will get
   int loss[] = new int[38];
   int lowestMoves[] = new int[38];
   

   //Checks to see if there are anymore moves left in the array
  	System.out.println("All Moves:");
   while(moves[k]!= null)
   {
   	System.out.println(moves[k]);
   	k++;

   }

   System.out.println("Total number of moves: " +k);

   /*We will only run our algorithm if there are less then 24 possible moves on the board,
   the reason for this is becasue of the computer speed we would not be able to calculate where to play
   if the number of moves is greater then 24*/
   if(k > 24)
   {
   	String bestMove = moves[0];
   	int bestMoveCount = k+1;
   	for(int i =0; i < k; i++)
   	{
   		int j=0;
   		
   		board[(int)moves[i].charAt(0) -48][(int)moves[i].charAt(1)-48] = 1;
   		board[moves[i].charAt(2) - 48][moves[i].charAt(3) - 48] = 1;
   		String [] holder = findMoves(board, k);
   		board[(int)moves[i].charAt(0) -48][(int)moves[i].charAt(1)-48] = 0;
   		board[moves[i].charAt(2) - 48][moves[i].charAt(3) - 48] = 0;
   		

   		while(holder[j] != null)
   		{
   			//System.out.print(" "+j);
   			j++;
   		}
   		if(j < bestMoveCount)
   		{
   			bestMoveCount = j;
   			bestMove = holder[i];   		
   			System.out.println("The number of moves in this board are: "+bestMove);
   		}


   	}
   	return bestMove;
   	//Plays the last move in the moves array
   	//return moves[k-1];
   }

   //While there are still moves to check this while loop will keep on executing
   while(moves[count] != null)
   {
   	System.out.println("Analysis Move: " + moves[count]);

   	//Sets one of the possible moves in the moves array equal to 1 (meaning the spot can no longer be played in)
   	board[(int)moves[count].charAt(0) -48][(int)moves[count].charAt(1)-48] = 1;
   	board[moves[count].charAt(2) - 48][moves[count].charAt(3) - 48] = 1;


   	//Enters the calculateLoss function and passes in the board after setting the moves made above to 1 and we pass in true, which repersents it is our turn
   	loss[count] = calculateLoss(board,true,k);

   	//The move you previously set to 1 is not set back to 0 (meaning the spot can be played on)
   	board[(int)moves[count].charAt(0) -48][(int)moves[count].charAt(1)-48] = 0;
   	board[moves[count].charAt(2) - 48][moves[count].charAt(3) - 48] = 0;

			//System.out.println("loss for move: " + count + " = " + loss[count]);
   	System.out.println("Number of Losses for this Move: " + loss[count]+"\n");

   	//Increases the counter so next time in the while loop we can check the next possible move in the array and pass the new board into calculateLoss
   	count++;
   }


   //Intializes min to the first value in the loss array
   int min = loss[0];
   //Sets the index to the first value in the loss array
   int indexMin = 0;

   //Runs though this for loop for the total number of moves in the move array
   for(int i = 0; i < count; i++){

   	if(loss[i] == 0)
   	{
   		return moves[i];
   	}

   	//Checks to see if the loss value in the array is less then the min loss value
   	if (loss[i] <= min){


   			//If it is i/t sets the new minimum loss value to the min value
   			min = loss[i];
   			//Stores the index of the loss array so we can later call it to play the move from the moves array
   			indexMin = i;
   	

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
public static int calculateLoss(int [][] newBoard,boolean turn, int size){

	//The variable for the number of losses
	int loss = 0;

	//Stores all the possible moves into the movoes array
	String [] moves = findMoves(newBoard, size);
		
		//int i = 0;

		//If there are still moves to be played increase the value of i by one
		/*while(moves[i] != null){
			i++;
		}*/

		//If there are no moves in the array enter the if statement
		if(moves[0] == null){
			//If we are not the last people to play then return 1 (which in this case repersents a loss)
			if(!turn){
				return 1;
			}
			//If we were the last one to play then return 0
			else{
				return 0;
			}
		}
		
		//Switching turns
		if(turn == false){
			turn = true;
		}
		else
			turn = false;
		

		
		int index = 0;

		//Same while loop as before, we start recursion, by passing in the next moved to be played on the board we passed into the calculateLoss function
		while(moves[index] != null){
			//System.out.println("got in the while loop of calculate moves");
			newBoard[moves[index].charAt(0) - 48][moves[index].charAt(1) - 48] = 1;
			newBoard[moves[index].charAt(2) -48][moves[index].charAt(3) - 48] = 1;
			loss += calculateLoss (newBoard,turn,size-1);
			newBoard[moves[index].charAt(0) - 48][moves[index].charAt(1) - 48] = 0;
			newBoard[moves[index].charAt(2) -48][moves[index].charAt(3) - 48] = 0;
			index++;
		}
		//After you play through the whole game with the first move you sent in, it will return the amount of times you could lose with the move
		return loss;	
	}
	
	/* 
	 *
	 *
	 */
	public static String [] findMoves(int [][] newBoard, int size){
		String previous = null;
		String current = null;
		int previousJV = 99;
		int previousJH = 99;
		int counterIndex = 0;

		
		String []totalMoves = new String[size];


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
