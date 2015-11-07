
public class Cram_env {
	
	public static void main(String[] args) {
		int [][] board = {{1,1,0},{0,0,0},{0,0,0}};
		String [] moves;
		
		int count = 0;
		for(int i=0; i < 3; i++)
		{
			
			for(int j =0; j<3; j++)
			{
				System.out.print(" "+ board[i][j]);
			}
			System.out.println("");
		}
		
		
		//System.out.println(board[1][1]);
		moves = findMoves(board);
		
		int wins[] = new int[42];
		
		while(moves[count] != null)
		{
			System.out.println(moves[count]);
			
			
		
			board[(int)moves[count].charAt(0) -48][(int)moves[count].charAt(1)-48] = 1;
			board[moves[count].charAt(2) - 48][moves[count].charAt(3) - 48] = 1;
			wins[count] = calculateWins(board,true);
			board[(int)moves[count].charAt(0) -48][(int)moves[count].charAt(1)-48] = 0;
			board[moves[count].charAt(2) - 48][moves[count].charAt(3) - 48] = 0;
			
			//System.out.println("Wins for move: " + count + " = " + wins[count]);
			count++;
		}
		int max = 0;
		int indexMax = 0;
		for(int i = 0; i < count; i++){
			System.out.println(wins[i]);
			if (wins[i] > max){
				indexMax = i;
				//System.out.println("max index " + indexMax);
			}
			
		}
		
		System.out.println("move : " + moves[indexMax]);
		
		


	}
	
	public static String [] findMoves(int [][] newBoard){
		String previous = null;
		String current = null;
		int previousJV = 99;
		int previousJH = 99;
		int counterIndex = 0;

		
		String []totalMoves = new String[42];

		for(int i = 0; i < 3; i++){
			for(int j = 0; j< 3; j++){
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
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j< 3; j++){
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
	
	public static int calculateWins(int [][] newBoard,boolean turn){
		
		int wins = 0;
		String [] moves = findMoves(newBoard);
		
		for(int i=0; i < 3; i++)
		{
			for(int j =0; j<3; j++)
			{
				System.out.print(" "+ newBoard[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
		
	
		
		int i = 0;
		while(moves[i] != null){
			//System.out.println(moves[i]);
			i++;
		}
	
		if(i == 0){
			//System.out.println(turn);
	
			if(turn){
				//System.out.println("we win");
				return 1;
			}
			else{
				//System.out.println("we loose");
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
			wins += calculateWins (newBoard,turn);
			newBoard[moves[index].charAt(0) - 48][moves[index].charAt(1) - 48] = 0;
			newBoard[moves[index].charAt(2) -48][moves[index].charAt(3) - 48] = 0;
			index++;
		}
		//System.out.println("Retunring wins:  " + wins);
	
		return wins;	
	}
	
	

}
