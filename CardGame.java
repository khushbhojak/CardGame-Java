import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Shivang Shingala
    You miss 100% of the shots you don't take.
 */
public class CardGame {
    static String[] player = new String[]{"p1","p2","p3","p4"}; //Create four players
         static int NumberAmount[] = new int[4]; //Store the sum of each player’s cards
         static boolean[] playerFlag = new boolean[]{true,true,true,true}; //Record whether the player is dead
    static boolean[] repeatFlag = new boolean[]{false,false,false,false};
         static boolean[] CardNeed = new boolean[]{true,true,true,true}; //Record whether the player wants a card
 
    public static void main(String[] args) {
                 RequireDealCard(); //Call the card issuing method
                 Result(); //Call the method of judging winning or losing
 
        Scanner sc = new Scanner(System.in);
        sc.next();
    }
 
    public static void RequireDealCard() {
 
                 PlayingCard pc = new PlayingCard(); //Create a card object
        Scanner sc = new Scanner(System.in);
 
                 int[][] card = new int[player.length][44]; //Record the player’s hand
 
                 for (int i = 0;i <player.length;i++) {//The first round of cards
            try{
            card[i][0] = pc.DealCard();
            card[i][1] = pc.DealCard();
            NumberAmount[i] = card[i][0] + card[i][1];
            System.out.printf("The person %s gets his cards,which are %d and %d.\n and the point-number of he is %d.\n\n",player[i],card[i][0],card[i][1],NumberAmount[i]);
            }
            catch (Exception e){
                System.out.printf(e.toString());
            }
        }
 
        boolean DontNeedFlag[] = new boolean[player.length];
        int DontNeed = 0;
        while (DontNeed != 4) {
            for (int i = 0; i < player.length; i++) {
                                 if (playerFlag[i] && CardNeed[i]) {//If the player is still alive
                    System.out.printf("\ndoes %s needs any cards?\n Type 1 for yes and 0 for no",player[i]);           //
                                         int cardAmount = sc.nextInt(); //Enter the number of cards dealt, whether the player requires a deal
 
                    if (!(cardAmount == 0)) {
                        int CardLength = FindNotZero(card[i]);
                        System.out.printf("%s gets %d card(s),\nand there are ",player[i],cardAmount);
                        for (int j = CardLength; j < (cardAmount + CardLength); j++) {
                                                         card[i][j] = pc.DealCard(); //Call the random card issuing method
                                                         System.out.printf("%-4d",card[i][j]); //Output the card the player just owned
                                                         NumberAmount[i] += card[i][j]; //Calculate the sum of all hand cards of the player
                        }
 
                        CardLength = FindNotZero(card[i]);
                        System.out.printf("\n%s has ",player[i]);
                        for (int j = 0; j < (CardLength); j++) {
                                                         System.out.printf("%-4d",card[i][j]); //Output all the player’s hands
                        }
                    }
                                         else {//The player doesn't want the card
                        int CardLength = FindNotZero(card[i]);
                        System.out.printf("\n%s has ",player[i]);
                        for (int j = 0; j < CardLength; j++) {
                            System.out.printf("%-4d",card[i][j]);
                        }
                        CardNeed[i] = false;
                    }
 
                    System.out.printf("\nsum is %d.",NumberAmount[i]);
                                         if (NumberAmount[i]> 21) {//If the player's card sum exceeds 21, die
                                                 playerFlag[i] = false; //player death flag
                        System.out.printf("\n\t%s is killed.",player[i]);
                        CardNeed[i] = false;
                        NumberAmount[i] = 0; //Set the player's hand card sum to empty for subsequent statistical output
                    }
                    System.out.println();
                }
 
                else if (playerFlag[i] && !CardNeed[i]) {
                    if (i == 4) {
                        break;
                    }
                    else
                        continue;
                }
            }
 
                         for (int i = 0; i <player.length; i++) {//Count how many people don’t
                if (!DontNeedFlag[i]) {
                    if (!CardNeed[i]) {
                        DontNeedFlag[i] = true;
                        DontNeed++;
                    }
                }
            }
        }
    }
 
    public static void Result() {
                 //Everyone is dead, a draw
 
                 //Only one or two people died, who is closer than the size 21
 
                 //There is only one person alive, he is the winner
                 int count = 0; //Record how many players have not died
        for (int i = 0; i < playerFlag.length; i++) {
            if(playerFlag[i] == true) {
                count++;
            }
        }
 
                 if(count> 1) {//If there are more than two players alive
                         //The main reason is to be afraid that two people have the same points
            if(CheckRepeat(NumberAmount)) {
                System.out.printf("\n\n\t\tThe winners are ");
                for (int i = 0; i < player.length; i++) {
                    if (repeatFlag[i]) {
                        System.out.printf("%3s",player[i]);
                    }
                }
            }
            else {
                System.out.printf("\n\n\t\tThe winner is %s.",player[Arrays.binarySearch(NumberAmount,getMax(NumberAmount))]);
            }
        }
                 else if(count == 1) {//If only one person is alive
            for (int i = 0; i < playerFlag.length; i++) {
                         if (playerFlag[i]) {//Look for the living player
                    System.out.printf("\n\n\t\tThe winner is %s.",player[i]);
                }
            }
        }
                 else if (count == 0) {//All dead
            System.out.printf("\n\n\t\tNo winner!");
        }
    }
 
         public static int getMax(int[] arr){ //Get the card of the biggest person
        int max = arr[0];
        for(int i = 1;i < arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
 
         public static boolean CheckRepeat(int[] arr) {//Check if there are multiple winners
        int m = Arrays.binarySearch(NumberAmount,getMax(NumberAmount));
        boolean flag = false;
                 //Two layers of loops to determine whether there are duplicate elements
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == arr[m]) {
                flag = true;
                repeatFlag[i] = true;
            }
        }
        return flag;
    }
 
         public static int FindNotZero(int[] arr) {//Returns the element that starts at 0 in the current array
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                return i;
            }
        }
        return arr.length;
    }
}
