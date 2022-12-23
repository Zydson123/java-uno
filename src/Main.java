import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.*;

public class Main {
    public static void print_hand(ArrayList<Card> Cards){
        for (int i = 0; i < Cards.size(); i++)
        {
            System.out.println(i+1 + ": " + Cards.get(i));
        }
    }
    public static void main(String[] args) {
        ArrayList<String> GoodColors = new ArrayList<String>();
        GoodColors.add("Green");
        GoodColors.add("Yellow");
        GoodColors.add("Red");
        GoodColors.add("Blue");
        int cardsForOpponent=0;
        int blocksForP1=0;
        int blocksForP2=0;
        Player player1 = new Player("Kuba");
        Player player2 = new Player("Grzesiu");
        Card card = new Card("pies", "pies");
        card.fillDeck(card.getDeck());
        ArrayList<Card> Deck = card.getDeck();
        Collections.shuffle(Deck);
        Player turn = player1;
        player1.giveCards(Deck, player1);
        player2.giveCards(Deck, player2);
        int cardId = (int)(Math.random() * Deck.size());
        boolean validCard=false;
        Card CardInCenter = Deck.get(cardId);
        while(!validCard){
            if(CardInCenter.getType()!="Block" && CardInCenter.getType()!="+4" && CardInCenter.getType()!="+2" && CardInCenter.getType()!="Ch" && CardInCenter.getType()!="Switch"){
                CardInCenter = Deck.get(cardId);
                validCard=true;
            }
            else{
                cardId = (int)(Math.random() * Deck.size());
            }
        }
        while (true) {
            if(Deck.size()==0){
                card.fillDeck(Deck);
                Collections.shuffle(Deck);
            }
            if(player1.getCards().size()==0){
                System.out.println(player1.getName() + " Wins!");
                break;
            }
            else if(player2.getCards().size()==0){
                System.out.println(player2.getName() + " Wins!");
                break;
            }
            else if (turn == player1) {
                System.out.println("The card in the center is: " + CardInCenter);
                if(blocksForP1!=0){
                    System.out.println("It's the turn of: " + player1.getName() + " you have been blocked for: " + blocksForP1 + " turns");
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137  if you dont have or dont want to use your block type in: 2137)");
                    print_hand(player1.getCards());
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if(whichCard==2136) {
                        turn = player2;
                        blocksForP1--;
                    }
                    else if(whichCard+1>player1.getCards().size()){
                        System.out.println("You only have: " + player1.getCards().size() + " cards!");
                    }
                    else {
                            Card chosenCard = player1.getCards().get(whichCard);
                            if (chosenCard.getType() == "Block") {
                                CardInCenter = chosenCard;
                                blocksForP2=blocksForP1;
                                blocksForP2++;
                                blocksForP1=0;
                                turn = player2;
                            }
                            else if (player1.getCards().get(whichCard).getType() != "Block") {
                                System.out.println("This is not a card you can use!");
                            }
                        }
                }
                else if(cardsForOpponent!=0){
                    System.out.println("It's the turn of: " + player1.getName() + " he has " + cardsForOpponent + " cards to draw, if he doesnt use a + card he will draw the amount");
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 if you dont have/dont want to use your + cards)");
                    print_hand(player1.getCards());
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if(whichCard==2136){
                        Card save = Deck.get(0);
                        player1.getCards().add(Deck.get(0));
                        Deck.remove(Deck.get(0));
                        cardsForOpponent--;
                        if(Deck.get(0).getType()=="+2"){
                            System.out.println("You got a +2 card! All of the cards for you will be transferred to the opponent with the additional 2 you just drew");
                            cardsForOpponent+=2;
                            CardInCenter = new Card("+2",save.getColor());
                            turn=player2;
                        }
                        else if(Deck.get(0).getType()=="+4") {
                            while(true){
                                System.out.println("You got a +4 card! All of the cards for you will be transferred to the opponent with the additional 2 you just drew");
                                System.out.println("Choose your color");
                                Scanner scanner = new Scanner(System.in);
                                String color = scanner.nextLine();
                                if(GoodColors.contains(color)){
                                    CardInCenter = new Card("0",color);
                                    turn = player2;
                                    cardsForOpponent+=4;
                                    break;
                                }
                                else{
                                    System.out.println("This is not a valid color");
                                }
                            }
                        }
                        else{
                            while (cardsForOpponent!=0){
                                player1.getCards().add(Deck.get(0));
                                Deck.remove(Deck.get(0));
                                cardsForOpponent--;
                            }
                            turn=player2;
                        }
                    }
                    else if(whichCard+1>player1.getCards().size()){
                        System.out.println("You only have: " + player1.getCards().size() + " cards!");
                    }
                    else{
                        Card chosenCard = player1.getCards().get(whichCard);
                            if(chosenCard.getType()=="+2"){
                                cardsForOpponent+=2;
                                CardInCenter = chosenCard;
                                player1.getCards().remove(chosenCard);
                                turn = player2;
                            }
                            else if(chosenCard.getType()=="+4"){
                                Scanner color = new Scanner(System.in);
                                System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                String CardColor = color.nextLine();
                                if(CardColor=="Red" || CardColor=="Green" || CardColor=="Blue" || CardColor=="Yellow"){
                                    CardInCenter = new Card("0", CardColor);
                                    player2.getCards().remove(chosenCard);
                                    cardsForOpponent+=4;
                                    turn = player2;
                                }
                                else{
                                    System.out.println("This is not a valid color");
                                }
                            }
                            else{
                                System.out.println("You can't use this card to counter the + card!");
                            }
                    }

                }
                else {
                    System.out.println("It's the turn of: " + player1.getName());
                    System.out.println("Your hand: ");
                    print_hand(player1.getCards());
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 to skip your turn)");
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if (whichCard == 2136) {
                        player1.getCards().add(Deck.get(0));
                        Deck.remove(Deck.get(0));
                        turn = player2;
                    }
                    else if(whichCard+1>player1.getCards().size()){
                        System.out.println("You only have: " + player1.getCards().size() + " cards!");
                    }
                    else {
                        Card chosenCard = player1.getCards().get(whichCard);
                        if (chosenCard.getType() == CardInCenter.getType() || chosenCard.getColor() == CardInCenter.getColor() || chosenCard.getColor() == "None") {
                            if (chosenCard.getColor() == "None") {
                                if (chosenCard.getType() == "Ch") {
                                    Scanner color = new Scanner(System.in);
                                    System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                    String CardColor = color.nextLine();
                                    if(CardColor=="Red" || CardColor=="Green" || CardColor=="Blue" || CardColor=="Yellow"){
                                        CardInCenter = new Card("0", CardColor);
                                        player2.getCards().remove(chosenCard);
                                        turn = player2;
                                    }
                                    else{
                                        System.out.println("This is not a valid color");
                                    }
                                }
                                else if (chosenCard.getType() == "+4") {
                                    Scanner color = new Scanner(System.in);
                                    System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                    String CardColor = color.nextLine();
                                    if(CardColor=="Red" || CardColor=="Green" || CardColor=="Blue" || CardColor=="Yellow"){
                                        CardInCenter = new Card("0", CardColor);
                                        player2.getCards().remove(chosenCard);
                                        cardsForOpponent+=4;
                                        turn = player2;
                                    }
                                    else{
                                        System.out.println("This is not a valid color");
                                    }
                                }
                            }
                            else if(chosenCard.getType()=="+2"){
                                cardsForOpponent+=2;
                                CardInCenter = chosenCard;
                                player1.getCards().remove(chosenCard);
                                turn = player2;
                            }
                            else if (chosenCard.getType()=="Block") {
                                blocksForP2++;
                                CardInCenter = chosenCard;
                                player1.getCards().remove(chosenCard);
                                turn = player2;
                            }
                            else if(chosenCard.getType()=="Switch"){
                                CardInCenter = chosenCard;
                                player2.getCards().remove(chosenCard);
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
                System.out.println("The card in the center is: " + CardInCenter);
                if(blocksForP2!=0){
                    System.out.println("It's the turn of: " + player2.getName() + " you have been blocked for: " + blocksForP2 + " turns, if you dont have or dont want to use your block type in: 2137");
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 if you dont have/dont want to use your + cards)");
                    print_hand(player2.getCards());
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if(whichCard==2136){
                        blocksForP2--;
                        turn=player1;
                    }
                    else if(whichCard+1>player2.getCards().size()){
                        System.out.println("You only have: " + player2.getCards().size() + " cards!");
                    }
                    else {
                        Card chosenCard = player2.getCards().get(whichCard);
                        if (chosenCard.getType() == "Block") {
                            CardInCenter = chosenCard;
                            blocksForP1=blocksForP2;
                            blocksForP1++;
                            blocksForP2=0;
                            turn = player1;
                        } else if (player2.getCards().get(whichCard).getType() != "Block") {
                            System.out.println("This is not a card you can use!");
                        }
                    }
                }
                else if(cardsForOpponent!=0){
                    System.out.println("It's the turn of: " + player2.getName() + " he has " + cardsForOpponent + " cards to draw, if he doesnt use a + card he will draw the amount");
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 if you dont have/dont want to use your + cards)");
                    print_hand(player2.getCards());
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if(whichCard==2136) {
                        Card save = Deck.get(0);
                        player2.getCards().add(Deck.get(0));
                        Deck.remove(Deck.get(0));
                        cardsForOpponent--;
                        if (save.getType() == "+2") {
                            System.out.println("You got a +2 card! All of the cards for you will be transferred to the opponent with the additional 2 you just drew");
                            cardsForOpponent += 2;
                            CardInCenter = new Card("+2", save.getColor());
                            turn = player1;
                        } else if (save.getType() == "+4") {
                            while (true) {
                                System.out.println("You got a +4 card! All of the cards for you will be transferred to the opponent with the additional 2 you just drew");
                                System.out.println("Choose your color");
                                Scanner scanner = new Scanner(System.in);
                                String color = scanner.nextLine();
                                if (GoodColors.contains(color)) {
                                    CardInCenter = new Card("0", color);
                                    turn = player1;
                                    cardsForOpponent += 4;
                                    break;
                                } else {
                                    System.out.println("This is not a valid color");
                                }
                            }
                        }
                        else {
                            while (cardsForOpponent != 0) {
                                player1.getCards().add(Deck.get(0));
                                Deck.remove(Deck.get(0));
                                cardsForOpponent--;
                            }
                            turn = player2;
                        }
                    }
                    else if(whichCard+1>player2.getCards().size()){
                        System.out.println("You only have: " + player2.getCards().size() + " cards!");
                    }
                    else{
                        Card chosenCard = player2.getCards().get(whichCard);
                            if(chosenCard.getType()=="+2"){
                                CardInCenter = chosenCard;
                                cardsForOpponent+=2;
                                player2.getCards().remove(chosenCard);
                                turn = player1;
                            }
                            else if(chosenCard.getType()=="+4"){
                                Scanner color = new Scanner(System.in);
                                System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                String CardColor = color.nextLine();
                                if(CardColor=="Red" || CardColor=="Green" || CardColor=="Blue" || CardColor=="Yellow"){
                                    CardInCenter = new Card("0", CardColor);
                                    player2.getCards().remove(chosenCard);
                                    cardsForOpponent+=4;
                                    turn = player1;
                                }
                                else{
                                    System.out.println("This is not a valid color");
                                }
                            }
                            else if(chosenCard.getType()!="2" || chosenCard.getType()!="+4"){
                                System.out.println("You can't use this card to counter the + card!");
                            }
                        }
                    }
                else{
                    System.out.println("It's the turn of: " + player2.getName());
                    System.out.println("Your hand: ");
                    print_hand(player2.getCards());
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Choose your card (type in 2137 to end turn)");
                    int whichCard = scan.nextInt();
                    whichCard--;
                    if(whichCard==2136){
                        player2.getCards().add(Deck.get(0));
                        Deck.remove(Deck.get(0));
                        turn = player1;
                    }
                    else if(whichCard+1>player2.getCards().size()){
                        System.out.println("You only have: " + player2.getCards().size() + " cards!");
                    }
                    else{
                        Card chosenCard = player2.getCards().get(whichCard);
                        if (chosenCard.getType() == CardInCenter.getType() || chosenCard.getColor() == CardInCenter.getColor() || chosenCard.getColor() == "None") {
                            if (chosenCard.getColor() == "None") {
                                if (chosenCard.getType() == "Ch") {
                                    Scanner color = new Scanner(System.in);
                                    System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                    String CardColor = color.nextLine();
                                    if(CardColor=="Red" || CardColor=="Green" || CardColor=="Blue" || CardColor=="Yellow"){
                                        CardInCenter = new Card("0", CardColor);
                                        player2.getCards().remove(chosenCard);
                                        turn = player1;
                                    }
                                    else{
                                        System.out.println("This is not a valid color");
                                    }
                                }
                                else if (chosenCard.getType() == "+4") {
                                    Scanner color = new Scanner(System.in);
                                    System.out.println("Choose the color (Red,Green,Blue,Yellow");
                                    String CardColor = color.nextLine();
                                    if(CardColor=="Red" || CardColor=="Green" || CardColor=="Blue" || CardColor=="Yellow"){
                                        CardInCenter = new Card("0", CardColor);
                                        player2.getCards().remove(chosenCard);
                                        cardsForOpponent+=4;
                                        turn = player1;
                                    }
                                    else{
                                        System.out.println("This is not a valid color");
                                    }
                                }

                            }
                            else if(chosenCard.getType()=="+2"){
                                cardsForOpponent+=2;
                                CardInCenter = chosenCard;
                                player2.getCards().remove(chosenCard);
                                turn = player1;
                            }
                            else if (chosenCard.getType()=="Block") {
                                blocksForP1++;
                                CardInCenter = chosenCard;
                                player2.getCards().remove(chosenCard);
                                turn = player1;
                            }
                            else if(chosenCard.getType()=="Switch"){
                                CardInCenter = chosenCard;
                                player2.getCards().remove(chosenCard);
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