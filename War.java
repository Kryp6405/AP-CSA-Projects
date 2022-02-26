import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class War{
	public static void main(String[]args){
		Run w = new Run();
	}
}

class Run{
	Run(){
		Scanner player = new Scanner(System.in);

		Deck d = new Deck(); //Create a deck of crads
		d.shuffle(); //Shuffle the deck
		d.shuffle(); //Shuffle the deck a second time (just to make sure)

		Hand p1 = new Hand(52); //Player 1's hand
		Hand p2 = new Hand(52); //Player 2's Hand
		Hand s1 = new Hand(52); //Player 1's Winning Hand/Pile
		Hand s2 = new Hand(52); //Player 2's Winning Hand/Pile

		for(int i = 0; i < 26; i++){ //Deal half the deck to each player
			p1.addCard(d.deal());
			p2.addCard(d.deal());
		}

		int i = 0;
		boolean g = true;

		do{ //War game

			System.out.println("Round "+(i+1)); //Displays round number
			System.out.println("Player 1: "+p1.getCards().get(0)); //Displays player 1's card
			System.out.println("Player 2: "+p2.getCards().get(0)); //Displays player s's card

			if(p1.getCards().get(0).getValue() > p2.getCards().get(0).getValue()){ //Compare player 1's top card to player 2's top card
				System.out.println("Result: Player 1 Wins!");
				s1.addCard(p1.getCards().get(0)); //Add player 1's top card to player 1's wining pile
				s1.addCard(p2.getCards().get(0)); //Add player 2's top card to player 1's wining pile
				p1.getCards().remove(0); //Remove player 1's top
				p2.getCards().remove(0); //Remove player 2's top card
			}
			else if(p1.getCards().get(0).getValue() < p2.getCards().get(0).getValue()){ //Compare player 1's top card to player 2's top card
				System.out.println("Result: Player 2 Wins!");
				s2.addCard(p1.getCards().get(0)); //Add player 1's top card to player 2's wining pile
				s2.addCard(p2.getCards().get(0)); //Add player 2's top card to player 2's wining pile
				p1.getCards().remove(0); //Remove player 1's top card
				p2.getCards().remove(0); //Remove player 2's top card
			}
			else if(p1.getCards().get(0).getValue() == p2.getCards().get(0).getValue()){ //Compare player 1's top card to player 2's top card0
				if((p1.getCards().size()+s1.getCards().size()) >= 5 && (p2.getCards().size()+s2.getCards().size()) >= 5)
					war(s1.getCards(), s2.getCards(), p1.getCards(), p2.getCards(), 0, 4); //Go to war (war method)
				else{
					if((p1.getCards().size()+s1.getCards().size()) < 5){
						System.out.println("Result: War!\nPlayer 2 Wins!");
						g = false;
					}
					else if((p2.getCards().size()+s2.getCards().size()) < 5){
						System.out.println("Result: War!\nPlayer 1 Wins!");
						g = false;
					}
				}
			}
			if(g == true){
				System.out.println("P1 - "+(p1.getCards().size()+s1.getCards().size())+" card(s) : P2 - "+(p2.getCards().size()+s2.getCards().size())+" card(s)\n"); //Print how many cards each player has
				System.out.print("Press \"ENTER\" for next round: "); //Asks user to press enter to go to next round
			}

			String user = player.nextLine();
			if(user.equalsIgnoreCase("") && g != false) //If true, continue game to next round
				g = true;
			else //Else, end game (gets out of while loop)
				g = false;

			System.out.println();

			if(p1.getCards().size() == 0 && s1.getCards().size() > 0){ //If player 1's hand is empty
				s1.handShuffle(); //Shuffle player 1's winning pile
				for(int j = s1.getCards().size()-1; j >= 0; j--){ //Deal shuffled cards from winning pile back to player 1
					p1.addCard(s1.getCards().get(0));
					s1.getCards().remove(0);
				}
			}
			if(p2.getCards().size() == 0 && s2.getCards().size() > 0){ //If player 2's hand is empty
				s2.handShuffle(); //Shuffle player 2's winning pile
				for(int j = s2.getCards().size()-1; j >= 0; j--){ //Deal shuffled cards from winning pile back to player 2
					p2.addCard(s2.getCards().get(0));
					s2.getCards().remove(0);
				}
			}
			i++;
		}while(((p1.getCards().size()+s1.getCards().size() != 0) && (p2.getCards().size()+s2.getCards().size() != 0)) && g == true);

		if(g == false)
			System.out.println("Thank you for playing War!");
		else if((s1.getCards().size()+p1.getCards().size()) == 52)
			System.out.println("\nPlayer 1 Wins!\n\nThank you for playing War!");
		else if((s2.getCards().size()+p2.getCards().size()) == 52)
			System.out.println("\nPlayer 2 Wins!\n\nThank you for playing War!");
	}

	public void war(ArrayList<Card> w1, ArrayList<Card> w2, ArrayList<Card> c1, ArrayList<Card> c2, int a, int n){
		if(c1.size() <= n){
			for(int i = 0; i < 520; i++){
				int t = (int)(Math.random()*w1.size());
				w1.add(w1.remove(t));
			}
			for(int j = w1.size()-1; j >= 0; j--){
				c1.add(w1.get(0));
				w1.remove(0);
			}
		}
		if(c2.size() <= n){
			for(int i = 0; i < 520; i++){
				int t = (int)(Math.random()*w2.size());
				w2.add(w2.remove(t));
			}
			for(int j = w2.size()-1; j >= 0; j--){
				c2.add(w2.get(0));
				w2.remove(0);
			}
		}
		System.out.println("Result: War!");
		System.out.println("Player 1: "+c1.get(n));
		System.out.println("Player 2: "+c2.get(n));
		if(c1.get(n).getValue() > c2.get(n).getValue()){
			System.out.println("Result: Player 1 Wins!");
			for(int i = n; i >= a; i--){
				w1.add(c1.get(i));
				w1.add(c2.get(i));
				c1.remove(i);
				c2.remove(i);
			}
		}
		else if(c1.get(n).getValue() < c2.get(n).getValue()){
			System.out.println("Result: Player 2 Wins!");
			for(int i = n; i >= a; i--){
				w2.add(c1.get(i));
				w2.add(c2.get(i));
				c1.remove(i);
				c2.remove(i);
			}
		}
		else{
			war(w1, w2, c1, c2, a, n+4);
		}
	}
}

class Card{
	private int value;
	private String suit;
	private String color;

	Card(int v, String s){
		value = v+1;
		suit = s;
		if(s.equalsIgnoreCase("Diamond") || s.equalsIgnoreCase("Heart") || s.equalsIgnoreCase("Diamonds") || s.equalsIgnoreCase("Hearts")){
			color = "Red";
		}
		else{
			color = "Black";
		}
	}

	public int getValue(){
		return value;
	}

	public String getSuit(){
		return suit;
	}

	public String getColor(){
		return color;
	}

	public String toString(){
		switch(value){
			case 1 : value+=13; return "Ace of "+suit;
			case 11 : return "Jack of "+suit;
			case 12 : return "Queen of "+suit;
			case 13 : return "King of "+suit;
		}
		return value+" of "+suit;
	}
}

class Deck{
	private ArrayList<Card> c;

	Deck(){
		c = new ArrayList<Card>();
		for(int i = 0; i < 52; i++){
			if(i/13 == 0){
				c.add(new Card(i%13, "Hearts"));
			}
			else if(i/13 == 1){
				c.add(new Card(i%13, "Diamonds"));
			}
			else if(i/13 == 2){
				c.add(new Card(i%13, "Clubs"));
			}
			else{
				c.add(new Card(i%13, "Spades"));
			}
		}
	}

	public void shuffle(){
		for(int i = 0; i < 520; i++){
			int t = (int)(Math.random()*c.size());
			c.add(c.remove(t));
		}
	}

	public Card deal(){
        return c.remove(0);
	}

	public ArrayList<Card> getCards(){
		return c;
	}

	public String toString(){
		return c.toString();
	}
}

class Hand{
	private int maxCards;
	private ArrayList<Card> cards;

	Hand(int n){
		cards = new ArrayList<Card>();
		maxCards = n;

	}

	public int getMaxCards(){
		return maxCards;
	}

	public ArrayList<Card> getCards(){
		return cards;
	}

	public void addCard(Card card){
		cards.add(card);
	}

	public void handShuffle(){
		for(int i = 0; i < 520; i++){
			int t = (int)(Math.random()*cards.size());
			cards.add(cards.remove(t));
		}
	}

	public String toString(){
		String temp = "";
		for(int i = 0; i < cards.size(); i++){
			temp += cards.get(i).toString()+"\t";
		}
		return temp;
	}
}