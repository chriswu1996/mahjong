// checks how many cards a hand needs to win
public class Evaluate{

    private int value;
    private int remaining;

    private int[] left;
    
    public int test( int[] cards, int[] l ){

        left = l;
        int hands = 0;
        
        // 1 - 70 // -1 = made hands( peng, chi )
        value = 14;
        int[] cardsCount = new int[73];
        for ( int i = 0; i < 13; i++ ) {
            if ( cards[i] == -1 ) {
                hands++;
            } else {
                cardsCount[ cards[i] ] = cardsCount[ cards[i] ] + 1;
            }
        }
        
        testing( cardsCount, 14 - hands, false, hands/3, 0 );
        return value;
    }

    public int remaining() {
        return remaining;
    }

    // how many remaining cards can win
    public void testing( int[] cards, int need, boolean hasPair, int hands, int r ){
        
        for ( int x = 0; x<71; x++ ){
            if ( hands != 4 ){
                // 1) more than one same card
                if ( cards[x] > 1 ) {
                    if ( ! hasPair ) {
                        cards[x] = cards[x] - 2;
                        testing( cards, need - 2, true, hands, r );
                        cards[x] = cards[x] + 2;
                    }
                    //3 - cards
                    // assuming that max = 3
                    if ( cards[x] == 2 ) {
                        cards[x] = 0;                        
                        testing( cards, need - 2, hasPair, hands+1, r + left[x] );
                        cards[x] = 2;
                    } else if ( cards[x] == 3 ){
                        cards[x] = 0;
                        testing( cards, need - 3, hasPair, hands+1, r );
                        cards[x] = 3;
                    }                
                }
                if ( cards[x] > 0 && cards[x+1] > 0 && cards[x+2] > 0 ) {
                    cards[x] = cards[x] - 1;
                    cards[x+1] = cards[x+1] - 1;
                    cards[x+2] = cards[x+2] - 1;
                    testing( cards, need - 3, hasPair, hands+1, r );
                    cards[x] = cards[x] + 1;
                    cards[x+1] = cards[x+1] + 1;
                    cards[x+2] = cards[x+2] + 1;
                }
                if ( cards[x] > 0 && cards[x+1] > 0 &&  cards[x+2] == 0 ){
                    cards[x] = cards[x] - 1;
                    cards[x+1] = cards[x+1] - 1;                                       
                    testing( cards, need - 2, hasPair, hands+1, r + left[x+2] + left[x-1] );
                    cards[x] = cards[x] + 1;
                    cards[x+1] = cards[x+1] + 1;
                }
                if ( cards[x] > 0 && cards[x+1] == 0 && cards[x+2] > 1 ) {
                    cards[x] = cards[x] - 1;
                    cards[x+2] = cards[x+2] - 1;
                    testing( cards, need - 2, hasPair, hands+1, r + left[x+1] );
                    cards[x] = cards[x] + 1;
                    cards[x+2] = cards[x+2] + 1;
                }
            }
        }
        if ( hasPair ) {
            need = need - ( 4 - hands );
        } else {
            need = need - ( 4 - hands ) - 1;
        }

        if ( need < value || ( need == value && r > remaining ) ){
            remaining = r;
            value = need;
        }
    }
}
