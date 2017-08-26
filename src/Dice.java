import java.util.Random;

public class Dice {

	private int value;

	public Dice() {
		rollDice();
	}

	public void rollDice() {
		Random rand = new Random();
		this.value = rand.nextInt(6)+1;
	}

	public int getValue() {
		return this.value;
	}

}
