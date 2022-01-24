
import java.util.Random;

/**
 *
 * @author nfavela
 */
public class RandomUtils {
    
    private static Random random = new Random(System.currentTimeMillis());
    
    /**
     * Regresa un valor entero aleatorio entre 0 (inclusivo) y max (exclusivo)
     * @param max Valor máximo menos uno a generar. 
     * @return Un valor aleatorio entre 0 y max-1
     */
    public static int valorRandom( int max ) {
        return random.nextInt(max);
    }
    
    /**
     * Regresa un valor entero aleatorio entre min y max. 
     * @param min El valor minimo a generar
     * @param max El valor máximo a generar
     * @return un valor aleatorio entre min y max-1
     */
    public static int valorRandom(int min, int max) {
        int valor = random.nextInt(max-min);
        return valor + min;
    }
    
}
