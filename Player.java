// add the code to keep track of left when others play cards or p or ch
// replace method
// gang
// be able to evaluate between chi and peng
import java.util.Arrays;

public class Player{

    private final int[] allCards = {1,2,3,4,5,6,7,8,9,21,22,23,24,25,26,27,28,29,41,42,43,44,45,46,47,48,49,52,55,58,61,64,67,70};

    private int[] left = new int[73];
    
    private int[] cards = new int[14];

    private Board board;

    private Evaluate e = new Evaluate();
    private Checker c = new Checker();
    
    public Player( Board b ) {
        board = b;
        
        // initiate left
        for ( int x = 0; x<33; x++ ) {
            left[allCards[x]] = 4;
        }
        
        // get 13 cards at the start of the game
        for ( int i = 0; i < 13; i++ ){
            cards[i] = board.deal();
            record( cards[i] );
        }
    }

    // for auto player
    public int play(){
        return play( decide() );
    }

    public int[] wouldCh( int card ){

        int lowest = -1;
            
        cards[13] = -1;

        // without chi
        int value = e.test( cards, left );
        int remaining = e.remaining();
        
        // check which case it can be
        boolean plustwo = false;
        boolean plusone = false;
        boolean mintwo = false;
        boolean minone = false;
        for ( int i = 0; i < 13; i++ ) {
            if ( card + 2 == cards[i] ) plustwo = true;
            if ( card + 1 == cards[i] ) plusone = true;
            if ( card - 1 == cards[i] ) minone = true;
            if ( card - 2 == cards[i] ) mintwo = true;
        }
        
        // for each case -1 and check the value before setting back

        int newValue = 14;
        int newRemaining = 0;
        
        int theCard = 0;
        int tmp;
        
        // 1
        if ( mintwo && minone ) {
            setCard( card-2, -1 );
            setCard( card-1, -1 );
            
            for ( int i = 0; i < 14; i++ ) {
                if ( cards[i] == -1 ) continue;
                tmp = cards[i];
                cards[i] = cards[13];
                if ( e.test( cards, left ) < newValue ) {
                    newValue = e.test( cards, left );
                    newRemaining = e.remaining();
                    theCard = tmp;
                    lowest = card - 2;
                } else if ( e.test( cards, left ) == newValue ) {
                    if ( e.remaining() > newRemaining ) {
                        newRemaining = e.remaining();
                        theCard = tmp;
                        lowest = card - 2;
                    } else if ( e.remaining() == newRemaining && tmp > 50 ) {
                        theCard = tmp;
                        lowest = card - 2;
                    }
                }
                cards[13] = cards[i];
                cards[i] = tmp;
            }

            // set back
            setCard( -1, card-2 );
            setCard( -1, card-1 );
        }

        // 2
        if ( plusone && minone ) {
            setCard( card+1, -1 );
            setCard( card-1, -1 );

            for ( int i = 0; i < 14; i++ ) {
                if ( cards[i] == -1 ) continue;
                tmp = cards[i];
                cards[i] = cards[13];
                if ( e.test( cards, left ) < newValue ) {
                    newValue = e.test( cards, left );
                    newRemaining = e.remaining();
                    theCard = tmp;
                    lowest = card - 1;
                } else if ( e.test( cards, left ) == newValue ) {
                    if ( e.remaining() > newRemaining ) {
                        newRemaining = e.remaining();
                        theCard = tmp;
                        lowest = card - 1;
                    } else if ( e.remaining() == newRemaining && tmp > 50 ) {
                        theCard = tmp;
                        lowest = card - 1;
                    }
                }
                cards[13] = cards[i];
                cards[i] = tmp;
            }

            // set back
            setCard( -1, card+1 );
            setCard( -1, card-1 );
        }

        // 3
        if ( plusone && plustwo ) {

            setCard( card+1, -1 );
            setCard( card+2, -1 );
            
            for ( int i = 0; i < 14; i++ ) {
                if ( cards[i] == -1 ) continue;
                tmp = cards[i];
                cards[i] = cards[13];
                if ( e.test( cards, left ) < newValue ) {
                    newValue = e.test( cards, left );
                    newRemaining = e.remaining();
                    theCard = tmp;
                    lowest = card;
                } else if ( e.test( cards, left ) == newValue ) {
                    if ( e.remaining() > newRemaining ) {
                        newRemaining = e.remaining();
                        theCard = tmp;
                        lowest = card;
                    } else if ( e.remaining() == newRemaining && tmp > 50 ) {
                        theCard = tmp;
                        lowest = card;
                    }
                }
                cards[13] = cards[i];
                cards[i] = tmp;
            }

            // set back
            setCard( -1, card+1 );
            setCard( -1, card+2 );
        }
        
        // check value
        if ( newValue < value || ( newValue == value && newRemaining > remaining ) ){
            int[] type = { theCard, lowest };
            return type;
        } else {
            int[] type = { -1, -1 };
            return type;
        }
    }

    public int wouldPeng( int card ){
        // without peng
        int value = e.test( cards, left );
        int remaining = e.remaining();
            
        // after peng
        cards[13] = -1;
        for ( int i = 0; i < 2; i++ ){
            setCard( card, -1 );
        }

        // value and remaining after peng
        int newValue = 14;
        int newRemaining = 0;
        
        int theCard = 0;
        int tmp;
        for ( int i = 0; i < 14; i++ ) {
            if ( cards[i] == -1 ) continue;
            tmp = cards[i];
            cards[i] = cards[13];
            if ( e.test( cards, left ) < newValue ) {
                newValue = e.test( cards, left );
                newRemaining = e.remaining();
                theCard = tmp;
            } else if ( e.test( cards, left ) == newValue ) {
                if ( e.remaining() > newRemaining ) {
                    newRemaining = e.remaining();
                    theCard = tmp;
                } else if ( e.remaining() == newRemaining && tmp > 50 ) {
                    theCard = tmp;
                }
            }
            cards[13] = cards[i];
            cards[i] = tmp;
        }

        if ( newValue < value || ( newValue == value && newRemaining > remaining ) ) return theCard;

        
        // set back the value
        for ( int i = 0; i < 2; i++ ){
            setCard( -1, card );
        }
        return -1;
    }

    public int wouldGang( int card ){
        // after 
        // without gang
        int value = e.test( cards, left );
        int remaining = e.remaining();
            
        // after gang
        cards[13] = -1;
        for ( int i = 0; i < 3; i++ ){
            setCard( card, -1 );
        }

        // value and remaining after peng
        int newValue = 14;
        int newRemaining = 0;
        
        int theCard = 0;
        int tmp;
        for ( int i = 0; i < 14; i++ ) {
            if ( cards[i] == -1 ) continue;
            tmp = cards[i];
            cards[i] = cards[13];
            if ( e.test( cards, left ) < newValue ) {
                newValue = e.test( cards, left );
                newRemaining = e.remaining();
                theCard = tmp;
            } else if ( e.test( cards, left ) == newValue ) {
                if ( e.remaining() > newRemaining ) {
                    newRemaining = e.remaining();
                    theCard = tmp;
                } else if ( e.remaining() == newRemaining && tmp > 50 ) {
                    theCard = tmp;
                }
            }
            cards[13] = cards[i];
            cards[i] = tmp;
        }

        if ( newValue < value || ( newValue == value && newRemaining > remaining ) ) return theCard;

        
        // set back the value
        for ( int i = 0; i < 2; i++ ){
            setCard( -1, card );
        }
        return -1;
    }

    public int decide(){
        int card = 0;
        int value = 14;
        int tmp;
        int remaining = 0;
        for ( int i = 0; i < 14; i++ ) {
            if ( cards[i] == -1 ) continue;
            tmp = cards[i];
            cards[i] = cards[13];
            if ( e.test( cards, left ) < value ) {
                value = e.test( cards, left );
                remaining = e.remaining();
                card = tmp;
            } else if ( e.test( cards, left ) == value ) {
                if ( e.remaining() > remaining ) {
                    remaining = e.remaining();
                    card = tmp;
                } else if ( e.remaining() == remaining && tmp > 50 ) {
                    card = tmp;
                }
            }
            cards[13] = cards[i];
            cards[i] = tmp;
        }
        return card;
    }
    
    public int play( int card ){
        setCard( card, cards[13] );
        return card;
    }

    public String display( int num ) {
        String d = "";
        for ( int i = 0; i < num; i++ ){
            d = d + " " + cards[i];
        }
        return d;
    }

    public boolean canWin( int card ){
        cards[13] = card;
        return c.check( cards );
    }

    public boolean hasWon(){
        return c.check( cards );
    }
    
    public void next() {
        cards[13] = board.deal();
        record( cards[13] );
    }

    public void record( int card ){
        left[ card ] = left[ card ] - 1;
    }

    public boolean canPeng( int card ) {
        if ( numberOf( card ) > 1 ) return true;
        return false;
    }

    public boolean canGang( int card ) {
        if ( numberOf( card ) > 2 ) return true;
        return false;
    }
    
    public int numberOf( int card ){
        int n = 0;
        for ( int i = 0; i < 13; i++ ){
            if (cards[i] == card ) n++;
        }
        return n;
    }

    public boolean canCh( int card ) {

        boolean plustwo = false;
        boolean plusone = false;
        boolean mintwo = false;
        boolean minone = false;
        for ( int i = 0; i < 13; i++ ) {
            if ( card + 2 == cards[i] ) plustwo = true;
            if ( card + 1 == cards[i] ) plusone = true;
            if ( card - 1 == cards[i] ) minone = true;
            if ( card - 2 == cards[i] ) mintwo = true;
        }
        if ( ( plustwo && plusone ) || ( plusone && minone ) || ( minone && mintwo ) ) return true;
        else return false;
    }

    public void arrange() {
        Arrays.sort( cards );
    }

    public void peng( int card ){
        cards[13] = -1;
        for ( int i = 0; i < 2; i++ ){
            setCard( card, -1 );
        }
    }

    public void gang( int card ){
        cards[13] = -1;
        for ( int i = 0; i < 3; i++ ){
            setCard( card, -1 );
        }
    }

    public void chi( int card, int lowest ){
        cards[13] = -1;
        for ( int i = 0; i < 13; i++ ){
            if ( cards[i] == lowest && lowest != card ){
                cards[i] = -1;
                break;
            }
        }

        for ( int i = 0; i < 13; i++ ){
            if ( cards[i] == lowest + 1 && lowest + 1 != card ){
                cards[i] = -1;
                break;
            }
        }

        for ( int i = 0; i < 13; i++ ){
            if ( cards[i] == lowest + 2 && lowest + 2 != card ){
                cards[i] = -1;
                break;
            }
        }
    }

    // only from 1 - 13
    public void setCard( int before, int after ){
        for ( int i = 0; i < 13; i++ ){
            if ( cards[i] == before ){
                cards[i] = after;
                break;
            }
        }
    }
}
