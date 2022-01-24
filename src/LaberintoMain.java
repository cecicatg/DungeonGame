
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author nfavela
 */
public class LaberintoMain {
    
    public static void main(String[] args) {
        Tablero t = new Tablero();
        
        Scanner scan = new Scanner(System.in);
        // Preguntar al usuario si desea cargar el tablero desde un archivo o 
        // si la aplicación creará uno de manera aleatorio. 
        System.out.println("Deseas cargar un tablero o creamos uno aleatorio?");
        System.out.println("1 - Cargar desde archivo\n2 - Aleatorio");
        int opcion = scan.nextInt();
        
        // Una vez el usuario decide si cargará o no un tablero entonces crearlo 
        // utilizando el método de creación adecuado: 
        
        // t.crear(); // Si el usuario decidió que la aplicación lo cree
        // t.crear(matriz); // Si el usuario decide cargarlo desde un archivo.
        if ( opcion == 1 ) {
            File archivo = FileUtils.selectFile();
            int matriz[][] = cargarTableroMatriz(archivo);
            t.crear(matriz);
        } else if (opcion == 2) {
            t.crear();
        }
        
        // Una vez el tablero es creado entonces hacer un ciclo infinito para 
        // preguntar una y otra vez qué dirección tomará el heroe en el laberinto
        // las direcciones validas son 0,1,2,3
        // Una vez capturada la dirección entonces 
        // t.moverPersonaje( direccion );
        // El ciclo se termina si el personaje encontro la salida o murió por caer
        // en un pozo o enfrentando a un enemigo.
        
        // mostrar las estadisticas:  
        // Personaje p = t.getPersonaje();
        // String estadisticas = p.getEstadisticas();
        // System.out.println(estadisticas);
        int fin = 0;
        do{
        System.out.println("Que direccion quieres tomar?\n0 = Norte\n1 = Sur\n2 = Este\n3 = Oeste ");
        int direccion = scan.nextInt();
        fin = t.moverPersonaje(direccion);
        }while (fin == 0);
        
        if (fin==1){
            System.out.println("Haz caido en un pozo y muerto :(");
        }
        else if (fin ==2){
            System.out.println("Encontraste la salida!! Eres libre!!");
        }
        else if (fin == 3){
            System.out.println("Te ha matado el enemigo y haz muerto en sus garras T-T");
        }
        int enemigos;
        String estadisticas;
        estadisticas = t.getHeroe().getEstadisticas();
        System.out.println(estadisticas);
    }

    /**
     * Método que carga la distribución de enemigos, pozos y salidas 
     * desde un archivo. Los valores 
     * 
     * @param archivo El archivo que contiene la información del tablero. 
     * @return Una matriz con los valores enteros definidos en el archivo. 
     */
    private static int[][] cargarTableroMatriz(File archivo) {
        String contenido = FileUtils.readFileContentInString(archivo);
        String lineas[] = contenido.split("\n");
        
        if(lineas.length != 10) {
            System.out.println("Error: Se esperan 10 lineas");
            System.exit(0);
        }
        
        int matriz[][] = new int[10][10];
        int x = 0;
        int y = 0;
        String[] celdas;
        for( String linea : lineas) {
            celdas = linea.split(",");
            y = 0;
         
            if(celdas.length != 10 ) {
                System.out.println("Error: El contenido del archivo no es correcto, se esperan 10 celdas por lineas");
                System.exit(0);
            }
            
            for (String v : celdas) {
                matriz[x][y++] = Integer.parseInt(v);
            }
            x++;
        }
        
        return matriz;
    }
    
}
