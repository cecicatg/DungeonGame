
/**
 *
 * @author nfavela
 */
public class TestRandomUtils {
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.valorRandom(100));
            
        }
        System.out.println("++++++++++++++");
        for (int i = 0; i < 30; i++) {
            System.out.println(RandomUtils.valorRandom(1, 50));
            
        }
        
    }
    
}
