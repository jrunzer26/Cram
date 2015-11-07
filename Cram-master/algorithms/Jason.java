package algorithms;
import org.gamelink.game.Cram;
import org.gamelink.game.Algo;
import java.util.Scanner;

public class Jason extends Algo{ // Replace TeamName
    private static String teamName = "Jason"; // Replace TeamName

    public static String getTeamName(){
        return teamName;
    }

    public static void main(String[] args){
        Cram game = new Cram(false);
        game.startGame(Jason.class); // Replace TeamName
    }

    public static String algorithm(Cram game){
 
   /************************************************
    ************  PLACE ALGORITHM HERE  ************
    ************************************************/
	
	
	
	
	
		Scanner in = new Scanner(System.in);
		String value = in.next();
		//System.out.println(value);
		return value;
		
    }
}
