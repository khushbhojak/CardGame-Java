/**
 *
 * @author Shivang Shingala
    You miss 100% of the shots you don't take.
 */
public class PlayingCard {
    private int[] card = new int[52]; //Total card
    private boolean[] CardDeck = new boolean[52]; //Mark the cards that have been drawn
    
    public PlayingCard() {
                 this.CardInit(); //initialization
    }
 
         public int DealCard() {//Method used to generate random cards
        int card = 0;
                 while (true) {//In order to ensure that the random number is useful
            card = Deal();
            if(card != -1) {
                return card;
            }
            else
                continue;
        }
    }
 
    private int Deal() {
                 int cardNum = (int)(Math.random() * 51 + 1); //Generate random numbers
        while (true) {
            if (CardDeck[cardNum] == false) {
                                 CardDeck[cardNum] = true; //The card has been drawn
                return card[cardNum];
            }
            else return -1;
        }
    }
 
    private void CardInit() {
        for (int i = 0;i < 52;i++) {
                         int Num = (i + 4) / 4; // assign a value to the card
            if (Num >= 10){
                                 Num = 10; // 10, J, Q, K are all regarded as 10
            }
                         card[i] = Num; //assignment
                         CardDeck[i] = false; //The initial state of the card is not drawn
        }
    }
}

