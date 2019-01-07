// record the card after peng and chi
// simplify and make faster
// make sure user can only play/ chi/ peng the right card
// for mum to debug, take away all the display of opponents' cards
import java.util.Scanner;
class Game{

    private static Board board = new Board();
    private static Player[] player = new Player[4];        
    private static Scanner s = new Scanner( System.in );
    
    public static void main( String[] args ) {


        // create 4 players, player 0 is the human player
        for ( int i = 0; i < 4; i++ ) {
            player[i] = new Player( board );
        }

        // player 1 draws a card
        // player arranges a card
        // player 1 plays a card
        // check hu player 2,3,4
        // check peng player 2,3,4
        // check chi player 2
        // player 2's turn;
        
        int pos = -1;
        int card;
        
        outer: while ( true ){

            pos = (pos+1) % 4;
            
            player[pos].next();

            // add code for self winning
            if ( player[pos].hasWon() ) break;
            
            // remember that when the player has 13 cards, the last card is not used, so can only arrange when there are 14 cards.
            player[pos].arrange();

            System.out.println( player[pos].display( 14 ));

            if ( pos == 0 ) {
                System.out.println( "Play: ");
                card = player[pos].play( s.nextInt() );
            } else {
                card = player[pos].play();
                System.out.println( "Player " + pos + " played " + card );
            }

            // take the card away from left[]
            for ( int x = 1; x < 4; x++ ){
                player[ (pos+x)%4 ].record( card );
            }

            // CHECK WIN
            for ( int x = 1; x<4; x++ ){
                if ( player[ (pos+x)%4 ].canWin( card ) ){
                    pos = (pos+x)%4;
                    // end the while loop
                    break outer;
                }
            }

            // CHECK PENG
            for ( int x = 1; x<4; x++ ){
                if ( player[ (pos+x)%4 ].canPeng( card ) ){
                    System.out.println( "CAN PENG: " + (pos+x)%4 );
                    if ( (pos+x)%4 == 0 ){
                        System.out.println( player[0].display( 13 ) );
                        System.out.println( "PENG? (Y/N): " );
                        if ( s.next().equals("Y") ){
                            player[0].peng( card );
                            System.out.println( "PLAY " );
                            player[0].play( s.nextInt() );
                            pos = 0;
                        }
                    } else {
                        int wouldPeng = player[ (pos+x)%4 ].wouldPeng( card );
                        if ( wouldPeng != -1 ){
                        // remember to record the cards
                        pos = (pos+x)%4;
                        System.out.println( "Player " + pos + " PENG!!!" );
                        player[pos].arrange();
                        System.out.println( player[pos].display(14) );
                        player[pos].play( wouldPeng );
                        System.out.println( "Player " + pos + " played " + wouldPeng );
                        continue outer;
                        }
                    }
                }
            }
            
            // CHECK CHI
            if ( player[ (pos+1)%4 ].canCh( card ) ){
                System.out.println( "CAN CHI: " + (pos+1)%4 );
                if ( (pos+1)%4 == 0 ){
                    System.out.println( player[0].display( 13 ) );
                    System.out.println( "CHI? (Y/N): " );
                    if ( s.next().equals("Y") ){
                        System.out.println( "Lowest? " );
                        player[0].chi( card, s.nextInt() );
                        System.out.println( "PLAY " );
                        player[0].play( s.nextInt() );
                        pos = 0;
                    }
                } else {
                    int wouldCh = player[ (pos+1)%4 ].wouldCh( card )[0];
                    int lowest = player[ (pos+1)%4 ].wouldCh( card )[1];
                    if ( wouldCh != -1 ){
                        // remember to record the cards
                        pos = (pos+1)%4;
                        player[pos].chi( card, lowest );
                        System.out.println( "Player " + pos + " CHI!!!" );
                        player[pos].arrange();
                        System.out.println( player[pos].display(14) );
                        player[pos].play( wouldCh );
                        System.out.println( "Player " + pos + " played " + wouldCh );
                        continue outer;
                    }
                }
            }
            // wouldCh;
            // continue;
                
        }
        
        // let the player win the game
        System.out.println( "Player " + pos + " has won!" );
        System.out.println( player[pos].display(14) );
    }
}
