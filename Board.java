import java.util.Random;

public class Board {

    private final int[] allCards = {1,2,3,4,5,6,7,8,9,21,22,23,24,25,26,27,28,29,41,42,43,44,45,46,47,48,49,52,55,58,61,64,67,70};

    private int[] cards;

    public Board() {

        // make all the cards
        int[] orderedCards = new int[allCards.length*4];
        for ( int i = 0; i < allCards.length; i++ ) {
            for ( int x = 0; x < 4; x++ ) {
                orderedCards[i*4+x] = allCards[i];
            }
        }

        // randomize
        Random rand = new Random();
        cards = new int[orderedCards.length];
        for ( int y = 0; y < cards.length; y++ ) {
            int random = rand.nextInt( cards.length - y );
            cards[y] = orderedCards[random];
            orderedCards[random] = orderedCards[cards.length - y - 1];
        }
    }

    private int pos = 0;
    
    public int deal(){
        // out of array bug!
        pos++;
        if ( pos > cards.length ) {
            System.out.println( "NO MORE CARDS" );
            return -1;
        }
        return cards[pos-1];
    }
}
