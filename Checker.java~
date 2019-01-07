public class Checker {

    // checks whether a hand has won
    
    public boolean check( int[] cards ) {
        // take out 2 of the same cards
        boolean[] used = new boolean[14];
        for ( int x = 0; x < 14; x++ ) {
            for ( int y = 0; y < 14; y++ ) {
                if ( x != y && cards[x] == cards[y] ) {
                    used[x] = true;
                    used[y] = true;
                    if ( checking( cards, used, 0) ) return true;
                    used[x] = false;
                    used[y] = false;
                }
            }
        }
        return false;
    }

    // recursive method
    // remember to i+1
    // remember to change back used value
    public boolean checking( int[] cards, boolean[] used,  int i ) {
        
        // when all four sets are found
        if ( i == 4 ) return true;

        // else take 2 random cards
        for ( int p = 0; p < 14; p++ ) {
            if ( used[p] ) continue;
            for ( int q = 0; q < 14; q++ ) {
                if ( used[q] ) continue;
                
                // 2 cards are the same, check if there is a third
                if ( p != q && cards[p] == cards[q] ) {
                    for ( int r = 0; r < 14; r++ ) {
                        if ( used[r] ) continue;
                        if ( r != p && r != q && cards[r] == cards[p] ) {
                            used[p] = true;
                            used[q] = true;
                            used[r] = true;
                            if ( checking( cards, used, i+1 ) ) return true;
                            used[p] = false;
                            used[q] = false;
                            used[r] = false;
                        }
                    }
                }

                // 2 cards are adjacent, check if there is a third
                if ( (cards[p] - cards[q]) == 1 ) {
                    for ( int s = 0; s < 14; s++ ) {
                        if ( used[s] ) continue;
                        if ( cards[s] == ( cards[q] - 1 ) ) {
                            used[p] = true;
                            used[q] = true;
                            used[s] = true;
                            if ( checking( cards, used, i+1 ) ) return true;
                            used[p] = false;
                            used[q] = false;
                            used[s] = false;
                        }
                    }
                }
            }
        }
        return false;
    }
}
