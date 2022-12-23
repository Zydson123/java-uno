import java.util.ArrayList;

public class Card {
    private String type;
    private String color;
    private ArrayList<Card> Deck = new ArrayList<Card>();
    private int id;

    public Card(String type, String color) {
        this.type = type;
        this.color = color;
        this.id++;
    }

    public void fillDeck(ArrayList<Card> Deck){
        Deck.add(new Card(String.valueOf(0),"Red"));
        Deck.add(new Card(String.valueOf(0),"Green"));
        Deck.add(new Card(String.valueOf(0),"Blue"));
        Deck.add(new Card(String.valueOf(0),"Yellow"));
        for(int i=1; i<9; i++){
                Deck.add(new Card(String.valueOf(i),"Red"));
                Deck.add(new Card(String.valueOf(i),"Red"));
                Deck.add(new Card(String.valueOf(i),"Green"));
                Deck.add(new Card(String.valueOf(i),"Green"));
                Deck.add(new Card(String.valueOf(i),"Blue"));
                Deck.add(new Card(String.valueOf(i),"Blue"));
                Deck.add(new Card(String.valueOf(i),"Yellow"));
                Deck.add(new Card(String.valueOf(i),"Yellow"));
        }
        for(int i=0; i<2;i++){
            Deck.add(new Card("+2","Red"));
            Deck.add(new Card("+2","Green"));
            Deck.add(new Card("+2","Blue"));
            Deck.add(new Card("+2","Yellow"));
            Deck.add(new Card("Block","Red"));
            Deck.add(new Card("Block","Green"));
            Deck.add(new Card("Block","Blue"));
            Deck.add(new Card("Block","Yellow"));
            Deck.add(new Card("Switch","Red"));
            Deck.add(new Card("Switch","Green"));
            Deck.add(new Card("Switch","Blue"));
            Deck.add(new Card("Switch","Yellow"));
        }
        for(int i=0;i<4;i++){
            //Ch - change color
            Deck.add(new Card("+4","None"));
            Deck.add(new Card("Ch","None"));
        }
    }

    public ArrayList<Card> getDeck() {
        return Deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        Deck = deck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if(this.getColor()!="None"){
            return this.getColor() + " " + this.getType();
        }
        else if(this.getType()!="Ch"){
            return this.getType();
        }
        else{
            return "Color Change";
        }
    }
}
