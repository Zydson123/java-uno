import java.util.ArrayList;

public class Player {
    private ArrayList<Card> Cards = new ArrayList<Card>();
    private String Name;

    public Player(String name) {
        this.Name = name;
    }
    public void giveCards(ArrayList<Card> Deck, Player player){
        int cardId = (int)(Math.random() * Deck.size());
        while(player.getCards().size()!=5){
            cardId = (int)(Math.random() * Deck.size());
            Card card = Deck.get(cardId);
            player.getCards().add(card);
            Deck.remove(card);
        }

    }
    public ArrayList<Card> getCards() {
        return Cards;
    }

    public void setCards(ArrayList<Card> cards) {
        Cards = cards;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
