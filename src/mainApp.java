import java.util.Scanner;

public class mainApp {

	public static void main(String[] args) {
		// Zmienne i obiekty
		Scanner scan = new Scanner(System.in);
		boolean howLongPlay = true;
		User player = new User();
		UserComp comp = new UserComp();
		int[] dices = new int[5];
		String line;
		//
		GameService.readFile("Start.txt");
		scan.nextLine();
		int howManyRounds = 2;
		// Początek właściwej gry
		while (howLongPlay) {
			GameService.gameShowDices(player, comp);
			// Początek rundy
			while (howManyRounds > 0) {
				System.out.println("Które kości chcesz przerzucić?");
				line = scan.nextLine();
				try {
					GameService.gameCheck(line, dices);
				} catch (ArrayIndexOutOfBoundsException e) {
					GameService.readFile("ErrorArray.txt");
					GameService.gameShowDices(player, comp);
					continue;
				} catch (Exception e) {
					GameService.readFile("ErrorData.txt");
					GameService.gameShowDices(player, comp);
					continue;
				}
				for (int i = 0; i < dices.length; i++) {
					player.rollSingle(dices[i]);
				}
				comp.compRoll(player.getPoints());
				GameService.gameShowDices(player, comp);
				--howManyRounds;
			}
			GameService.gameResult(player, comp);
			System.out.println("Chcesz zagrać jeszcze raz? T/N");
			if (scan.nextLine().compareToIgnoreCase("t") == 0) {
				howManyRounds = 2;
				System.out.println("Nacisnij enter aby rzucić kośćmi!");
				scan.nextLine();
				player.rollAll();
				comp.rollAll();

			} else {
				howLongPlay = false;
				scan.close();
			}
		}
	}
}