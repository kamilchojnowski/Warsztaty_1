import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameService {
	
	public static void gameResult(User player, UserComp comp) {
		if (player.getPoints() > comp.getPoints()) {
			System.out.println("Wygrałeś!");
		} else if (player.getPoints() < comp.getPoints()) {
			System.out.println("Przegrałeś :(");
		} else {
			System.out.println("Remis");
		}
	}
	
	public static void readFile(String name) {
		File file = new File(name);
		try {
			Scanner scanFile = new Scanner(file);
			while (scanFile.hasNextLine()) {
				System.out.println(scanFile.nextLine());
			}
			scanFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static int[] gameCheck(String line, int[] dices){
		String[] rerolls = line.split(" ");
		for (int i = 0; i < rerolls.length; i++) {
			dices[i] = Integer.parseInt(rerolls[i].trim());
			if (dices[i] < 0 || dices[i] > 5) {
				throw new ArrayIndexOutOfBoundsException();
			}
		}
		return dices;
	}
	
	public static void gameShowDices(User player, UserComp comp){
		player.calculatePoints();
		comp.calculatePoints();
		System.out.println("Oto Twoje kości:");
		for(Dice dice : player.getDices()){
			System.out.print(String.format("%d ", dice.getValue()));
		}
		System.out.println(String.format("%s", player.getResult()));
		System.out.println("Oto kości przeciwnika:");
		for(Dice dice : comp.getDices()){
			System.out.print(String.format("%d ", dice.getValue()));
		}
		System.out.println(String.format("%s", comp.getResult()));
	}

}
