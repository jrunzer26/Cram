
public class Cram_env {
	
	public static void main(String[] args) {
		int [][] board = {{0,0,0},{1,1,0},{0,0,0}};
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
		
		while(moves[count] != null)
		{
			System.out.println(moves[count]);
			count++;
		}


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
	
	public int calculateWins(int [][] newBoard){
		return 0;
	}
	
	

}
