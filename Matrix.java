import java.util.Random;
class Matrix{

    private static Random random = new Random();

    public static void main ( String[] args ) {
        int x;
        while ( true ){
            x = random.nextInt(2);
            System.out.print( x );
        }
    }
}
