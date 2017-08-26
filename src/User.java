import java.util.ArrayList;
import java.util.List;

public class User {

	private List<Dice> dices;
	private double points;
	private String result;

	public User() {
		rollAll();
	}

	public List<Dice> getDices() {
		return this.dices;
	}

	public double getPoints() {
		return this.points;
	}
	
	public String getResult(){
		return this.result;
	}
	
	public void rollAll(){
		this.dices = new ArrayList<>();
		for(int i = 0 ; i < 5 ; i++){
			this.dices.add(new Dice());
		}
	}

	public void rollSingle(int index){
			if(index>0){
				this.getDices().get(--index).rollDice();
			}
	}
	
	private static boolean containsStr(String str, String search) {
		for (int i = 0; i < str.length() - search.length() + 1; i++) {
			if (str.substring(i, search.length() + i).equals(search))
				return true;
		}
		return false;
	}

	public void calculatePoints() {
		int[] order = new int[6];
		for (int i = 0; i < 5; i++) {
			order[dices.get(i).getValue() - 1]++;
		}
		String orderStr = "";
		for (int temp : order) {
			orderStr += Integer.toString(temp);
		}
		String[] orders = { "5", "4", "011111", "111110", "3" };
		int orderNumber = -1;
		for (int i = 0; i < orders.length; i++) {
			if (containsStr(orderStr, orders[i])) {
				orderNumber = i;
				break;
			}
		}
		switch (orderNumber) {
		case 0:
			this.result = "Poker!";
			this.points = 9.0 + orderStr.indexOf('5') / 10.0;
			break;
		case 1:
			this.result = "Kareta!";
			this.points = 8.0 + (orderStr.indexOf('4') + 1) / 10.0 + (orderStr.indexOf('1') + 1) / 100.0;
			break;

		case 2:
			this.result = "Duży Street!";
			this.points = 6.0;
			break;
		case 3:
			this.result = "Mały Street!";
			this.points = 5.0;
			break;
		case 4:
			if (containsStr(orderStr, "2")) {
				this.result = "Full!";
				this.points = 7.0 + (orderStr.indexOf('3') + 1) / 10.0 + (orderStr.indexOf('2') + 1) / 100.0;
			} else {
				this.result = "Trójka!";
				this.points = 4.0 + (orderStr.indexOf('3') + 1) / 10.0;
			}
			break;
		default:
			int amount2 = 0;
			for (int i = 0; i < 6; i++) {
				if (orderStr.charAt(i) == '2') {
					amount2++;
				}
			}
			if (amount2 == 2) {
				this.result = "Dwie pary!";
				this.points = 3.0 + (orderStr.lastIndexOf('2') + 1) / 10.0 + (orderStr.indexOf('2') + 1) / 100.0;
			} else if (amount2 == 1) {
				this.result = "Para!";
				this.points = 2.0 + (orderStr.indexOf('2') + 1) / 10.0;
			} else {
				this.result = "Najwyższa kość!";
				this.points = 1.0 + (orderStr.lastIndexOf('1') + 1) / 10.0;
			}
		}
	}

}
