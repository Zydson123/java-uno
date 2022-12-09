import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        int cardsForOpponent=0;
        Player player1 = new Player("Kuba");
        Player player2 = new Player("Grzesiu");
        Card card = new Card("pies", "pies");
        card.fillDeck(card.getDeck());
        ArrayList<Card> Deck = card.getDeck();
        System.out.println(Deck);
        Player turn = player1;
        player1.giveCards(Deck, player1);
        player2.giveCards(Deck, player2);
        int cardId = (int)(Math.random() * Deck.size());
        boolean validCard=false;
        Card CardInCenter = Deck.get(cardId);
        while(!validCard){
            if(CardInCenter.getType()!="Block" && CardInCenter.getType()!="+4" && CardInCenter.getType()!="Ch" && CardInCenter.getType()!="Switch"){
                CardInCenter = Deck.get(cardId);
                validCard=true;
            }
            else{
                cardId = (int)(Math.random() * Deck.size());
            }
        }
        player1.getCards().add(new Card("+4","None"));
        player2.getCards().add(new Card("+4","None"));
        player1.getCards().add(new Card("Ch","None"));
        player2.getCards().add(new Card("Ch","None"));
        player1.getCards().add(new Card("+2","Red"));
        player2.getCards().add(new Card("+2","Red"));
        while (true) {
            System.out.println("The card in the center is: " + CardInCenter);
            if(player1.getCards().size()==0){
                System.out.println(player1.getName() + " Wins!");
            }
            else if(player2.getCards().size()==0){
                System.out.println(player2.getName() + " Wins!");
            }
            else if (turn == player1) {
                if(cardsForOpponent!=0){
                    System.out.println("It's the turn of: " + player1.getName() + " he has " + cardsForOpponent + " cards to draw, if he doesnt use a + card he will draw the amount");
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 if you dont have/dont want to use your + cards)");
                    System.out.println(player1.getCards());
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if(whichCard==2136){
                        while (cardsForOpponent!=0){
                            int cardId2 = (int)(Math.random() * Deck.size());
                            player1.getCards().add(Deck.get(cardId2));
                            Deck.remove(Deck.get(cardId2));
                            cardsForOpponent--;
                        }
                        turn = player2;
                    }
                    else{
                        Card chosenCard = player1.getCards().get(whichCard);
                        if(chosenCard.getType()=="+2"){
                            cardsForOpponent+=2;
                            turn = player2;
                        }
                        else{
                            Scanner color = new Scanner(System.in);
                            System.out.println("Choose the color (Red,Green,Blue,Yellow");
                            String CardColor = scan.nextLine();
                            CardInCenter = new Card("0",CardColor);
                            cardsForOpponent+=4;
                            player1.getCards().remove(chosenCard);
                            turn = player2;
                        }
                    }
                }
                else {
                    System.out.println("It's the turn of: " + player1.getName());
                    System.out.println("Your hand: ");
                    System.out.println(player1.getCards());
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 to skip your turn)");
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if (whichCard == 2136) {
                        player1.getCards().add(Deck.get(0));
                        Deck.remove(Deck.get(0));
                        turn = player2;
                    }
                    else {
                        Card chosenCard = player1.getCards().get(whichCard);
                        System.out.println(chosenCard.getType());
                        System.out.println(chosenCard.getColor());
                        if (chosenCard.getType() == CardInCenter.getType() || chosenCard.getColor() == CardInCenter.getColor() || chosenCard.getColor() == "None") {
                            if (chosenCard.getColor() == "None") {
                                if (chosenCard.getType() == "Ch") {
                                    Scanner color = new Scanner(System.in);
                                    System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                    String CardColor = color.nextLine();
                                    CardInCenter = new Card("0", CardColor);
                                    player1.getCards().remove(chosenCard);
                                    turn = player2;
                                }
                                else if (chosenCard.getType() == "+4") {
                                    Scanner color = new Scanner(System.in);
                                    System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                    String CardColor = color.nextLine();
                                    CardInCenter = new Card("0", CardColor);
                                    cardsForOpponent = 4;
                                    player1.getCards().remove(chosenCard);
                                    turn = player2;
                                }
                            }
                            else {
                                CardInCenter = chosenCard;
                                player1.getCards().remove(chosenCard);
                                turn = player2;
                            }
                        }
                        else {
                            System.out.println("You can't use this card!" + "(" + chosenCard + ")");
                        }
                    }
                }
            }
            else if(turn==player2){
                if(cardsForOpponent!=0){
                    System.out.println("It's the turn of: " + player2.getName() + " he has " + cardsForOpponent + " cards to draw, if he doesnt use a + card he will draw the amount");
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 if you dont have/dont want to use your + cards)");
                    System.out.println(player2.getCards());
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if(whichCard==2136){
                        while (cardsForOpponent!=0){
                            int cardId2 = (int)(Math.random() * Deck.size());
                            player2.getCards().add(Deck.get(cardId2));
                            Deck.remove(Deck.get(cardId2));
                            cardsForOpponent--;
                        }
                        turn = player1;
                    }
                    else{
                        Card chosenCard = player2.getCards().get(whichCard);
                        if(chosenCard.getType()=="+2"){
                            cardsForOpponent+=2;
                            turn = player1;
                        }
                        else{
                            Scanner color = new Scanner(System.in);
                            System.out.println("Choose the color (Red,Green,Blue,Yellow");
                            String CardColor = color.nextLine();
                            CardInCenter = new Card("0",CardColor);
                            cardsForOpponent+=4;
                            player2.getCards().remove(chosenCard);
                            turn = player1;
                        }
                    }
                }
                else{
                    System.out.println("It's the turn of: " + player2.getName());
                    System.out.println("Your hand: ");
                    System.out.println(player2.getCards());
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 to end turn)");
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if(whichCard==2136){
                        player2.getCards().add(Deck.get(0));
                        Deck.remove(Deck.get(0));
                        turn = player1;
                    }
                    else{
                        Card chosenCard = player2.getCards().get(whichCard);
                        if (chosenCard.getType() == CardInCenter.getType() || chosenCard.getColor() == CardInCenter.getColor() || chosenCard.getColor() == "None") {
                            if (chosenCard.getColor() == "None") {
                                if (chosenCard.getType() == "Ch") {
                                    Scanner color = new Scanner(System.in);
                                    System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                    String CardColor = color.nextLine();
                                    CardInCenter = new Card("0", CardColor);
                                    player2.getCards().remove(chosenCard);
                                    turn = player1;
                                }
                                else if (chosenCard.getType() == "+4") {
                                    Scanner color = new Scanner(System.in);
                                    System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                    String CardColor = color.nextLine();
                                    CardInCenter = new Card("0", CardColor);
                                    cardsForOpponent = 4;
                                    player2.getCards().remove(chosenCard);
                                    turn = player1;
                                }
                            }
                            else {
                                CardInCenter = chosenCard;
                                player2.getCards().remove(chosenCard);
                                turn = player1;
                            }
                        }
                        else{
                            System.out.println("You can't use this card!" + "(" + chosenCard + ")");
                        }
                    }
                }

            }
        }
    }
}