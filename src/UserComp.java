
public class UserComp extends User {

	private void rollComp(boolean exept, char toRoll) {
		int[] order = new int[6];
		for (int i = 0; i < 5; i++) {
			order[this.getDices().get(i).getValue() - 1]++;
		}
		String orderStr = "";
		for (int temp : order) {
			orderStr += Integer.toString(temp);
		}
		int roll;
		if (exept) {
			roll = orderStr.lastIndexOf(toRoll) + 1;
		} else {
			roll = orderStr.indexOf(toRoll) + 1;
		}
		for (Dice dice : this.getDices()) {
			if (dice.getValue() == roll) {
				dice.rollDice();
			}
		}
	}

	public void compRoll(double playerPoints) {
		if (playerPoints >= this.getPoints()) {
			switch ((int) this.getPoints()) {
			case 9:
				if (playerPoints > this.getPoints()) {
					this.rollAll();
				}
				break;
			case 8:
			case 4:
			case 3:
			case 2:
				rollComp(false, '1');
				break;
			case 7:
				rollComp(false, '2');
				break;
			case 6:
			case 5:
			case 1:
				rollComp(true, '1');
				break;

			}

		}
	}

}
