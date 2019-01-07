import java.util.Scanner;

class Test{

    public int[] cards = new int[14];
    
    public static void main ( String[] args ) {
            
        //Board b = new Board();
        //Player p = new Player( b );
        //Evaluate e = new Evaluate();
        Test t = new Test();
        Scanner s = new Scanner( System.in );

        for ( int i=0; i<14; i++ ){
            t.cards[i] = s.nextInt();
        }

        boolean test = t.canCh( s.nextInt() );
        System.out.println( test );
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
}
