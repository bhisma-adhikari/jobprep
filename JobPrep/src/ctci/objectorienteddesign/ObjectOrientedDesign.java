package ctci.objectorienteddesign;

public class ObjectOrientedDesign {
	public static void main(String[] args) {
		Suit s = Suit.Club;
		System.out.println(Suit.getSuitFromValue(2));
		
	}
}

class Card {
	private int num;
	private CardType type;

	public Card(int num, CardType type) {
		this.num = num;
		this.type = type;
	}

	// getters & setters 
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}
	
	// methods 
	
	public CardColor getColor() {
		if (this.type == CardType.HEART || this.type == CardType.DIAMOND) {
			return CardColor.RED;
		}
		return CardColor.BLACK;
	}
	
	public boolean isFace() {
		return this.num >= 11; 
	}
	
}

enum CardType {
	HEART, DIAMOND, SPADE, CLUB;
}

enum CardColor {
	RED, BLACK;
}

enum Suit {
	Club (0, "tree"), Diamond(1), Heart(2), Spade(3);
	
	private int value; 
	public String desc;
	private Suit(int value) {
		this.value = value; 
	}
	private Suit(int value, String desc) {
		this.value = value; 
		this.desc = desc; 
		System.out.println("created");
	}
	
	public int getValue() {
		return this.value; 
	}
	public static Suit getSuitFromValue(int value) {
		for (Suit s : Suit.values()) {
			if (s.value == value) {
				return s; 
			}
		}
		return null;  
	}
	public static void foo() {
		System.out.println("foo");
	}

}
