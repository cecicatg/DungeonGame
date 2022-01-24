/**
 *
 * @author nfavela
 */
import java.io.File;
import java.util.Scanner;

public class Tablero {
    public static final int NORTE = 0;
    public static final int SUR = 1;
    public static final int ESTE = 2;
    public static final int OESTE = 3;
    
    
    private int numEnemigos = 5;
    private int numSalidas = 3;
    private int numPozos = 3; 
    
    private final static int DIMENSION = 10;
    
    private Celda[][] celdas;
    
    private Personaje heroe = new Heroe();
    
    public Personaje getHeroe(){
        return heroe;
    }

    public int getNumEnemigos() {
        return numEnemigos;
    }

    public void setNumEnemigos(int numEnemigos) {
        this.numEnemigos = numEnemigos;
    }

    public int getNumSalidas() {
        return numSalidas;
    }

    public void setNumSalidas(int numSalidas) {
        this.numSalidas = numSalidas;
    }

    public int getNumPozos() {
        return numPozos;
    }

    public void setNumPozos(int numPozos) {
        this.numPozos = numPozos;
    }
    
    // PorHacer: Añadir el código para lograr el objetivo del método. 
    /**
     * Deberá crear el laberinto utilizando colocación aleatoria de 
     * enemigos, pozos, salidas y posicionamiento del heroe. 
     * Para generar los valores aleatorios puede apoyarse de la clase RandomUtils.
     */
    public void crear( ) {
        celdas = new Celda [10][10];
        
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                celdas[i][j] = new Celda();
                celdas[i][j].setX(i);
                celdas[i][j].setY(j);
            }
        }
        for(int k=0; k<5; k++){
            int i=RandomUtils.valorRandom(10);
            int j=RandomUtils.valorRandom(10);
            if (!celdas[i][j].isEnemigo()){
                celdas[i][j].setEnemigo(true);
            }
            else 
                k--;
        }
        for(int k=0; k<3; k++){
            int i=RandomUtils.valorRandom(10);
            int j=RandomUtils.valorRandom(10);
            if (!celdas[i][j].isEnemigo() && !celdas[i][j].isPozo()){
                celdas[i][j].setPozo(true);
            }
            else 
                k--;
        }
        
        for(int k=0; k<3; k++){
            int i=RandomUtils.valorRandom(10);
            int j=RandomUtils.valorRandom(10);
            if (!celdas[i][j].isEnemigo() && !celdas[i][j].isPozo() && !celdas[i][j].isSalida()){
                celdas[i][j].setSalida(true);
            }
            else 
                k--;
        }
        for(int k=0; k<1; k++){
            int i=RandomUtils.valorRandom(10);
            int j=RandomUtils.valorRandom(10);
            if (!celdas[i][j].isEnemigo() && !celdas[i][j].isPozo() && !celdas[i][j].isSalida()){
                heroe.setCeldaActual(celdas[i][j]);
            }
            else 
                k--;
        }
        imprimir();
    }
    
    // PorHacer: Añadir el código para lograr el objetivo del método. 
    /**
     * Deberá crear el laberinto en base a la información recibida en 
     * la matriz de valores enteros. 
     * Los valores enteros válidos que puede tener la matriz va en el rango de 0 - 3. 
     * El significado de cada valor es el siguiente: 
     *   0 - Celda normal, 
     *   1 - Celda Pozo
     *   2 - Celda con Enemigo
     *   3 - Celda Salida
     * 
     * Si viene un valor que no esté en este rango entonces poner cero por definición. 
     * @param celdasEnteros 
     */
    public void crear ( int  celdasEnteros[][] ) {
        celdas = new Celda [10][10];
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                    celdas[i][j] = new Celda();
                    celdas[i][j].setX(i);
                    celdas[i][j].setY(j);
                if (celdasEnteros[i][j]==1){
                    celdas[i][j].setPozo(true);
                }
                else if (celdasEnteros[i][j]==2){
                    celdas[i][j].setEnemigo(true);
                }
                else if (celdasEnteros[i][j]==3){
                    celdas[i][j].setSalida(true);
                }
                else System.out.println("Error, hay numeros fuera de los permitidos (del 0 al 3), estos seran convertidos a 0");
            }
        }
        for(int k=0; k<1; k++){
            int i=RandomUtils.valorRandom(10);
            int j=RandomUtils.valorRandom(10);
            if (!celdas[i][j].isEnemigo() && !celdas[i][j].isPozo() && !celdas[i][j].isSalida()){
                heroe.setCeldaActual(celdas[i][j]);
            }
            else 
                k--;
        }
        imprimir();
    }
   
    // PorHacer: Añadir código para el caso donde el personaje llega a una celda
    // pozo, salida o con enemigo.
    /**
     * Este método intentará mover al personaje a una siguiente celda basado 
     * en la dirección elegida. La dirección puede ser NORTE(0), SUR(1), ESTE(2) u OESTE(3). 
     * En caso de que la dirección no sea valida el personaje se queda en la misma celda. 
     * @param direccion La dirección a tomar por el personaje
     * @return Un valor entero para indicar que ya se terminó porque el personaje murió (1),
     * o porque el personaje encontró la salida (2) o porque decidió huir (3), en otro caso 
     * se regresa 0.
     */
    public int moverPersonaje( int direccion ) {
        int fin = 0;
        Celda nueva = siguienteCelda( heroe.getCeldaActual(), direccion );
        heroe.setCeldaActual(nueva);
        int monedas= ((Heroe)heroe).getMonedas();
        ((Heroe)heroe).setMonedas(monedas);
        int enemigo = ((Heroe)heroe).getNumEnemigosVencidos();
        enemigo=0;
        // Imprimir el tablero:
        this.imprimir();
        
        Scanner scan = new Scanner(System.in);
        
               
        if ( nueva.isPozo() ) {
            fin = 1;
            monedas--;
        } else if ( nueva.isSalida() ) {
            fin = 2;
        } else if ( nueva.isEnemigo() ) {
            int victoria=0;
            int derrota=0;
            System.out.println("ALERTA DE ENEMIGO\nQue quieres hacer?\n1 = Pelear! >:)\n2 = Huir >.<");
            int eleccion = scan.nextInt();
            if (eleccion == 1){
                for (int i =0; i<3; i++){
                    int pelea = RandomUtils.valorRandom(2);
                    if (pelea == 1){
                    System.out.println("Pelea "+i+": ganaste");
                    }
                    else System.out.println("Pelea "+i+": perdiste");
                    if (pelea ==0){
                        derrota++;
                    }
                    else victoria++;
                }
                if (derrota>victoria){
                    fin = 3; //si pierde se va al fin 3 que es mandar un mensaje de derrota y terminar el programa
                    monedas--;
                }
                else{
                    System.out.println("Haz derrotado al enemigo con tus habilidades y tu astucia!");
                    imprimir();
                    enemigo++;
                    ((Heroe)heroe).setNumEnemigosVencidos(enemigo);
                    monedas+=5;
                    fin = 0;
                }
            }
            else{
                System.out.println("Haz decidido huir");//la huida no es un fin ya que quiero que vuelva a entrar al ciclo do-while en que 
                                                        //pregunta a donde se quiere mover despues de haberse movido a otro lugar aleatoriamente
                int huida = RandomUtils.valorRandom(4);
                moverPersonaje(huida);
            }
            // Aquí deberá preguntar al usuario si desea enfrentar al enemigo o huir?
            
            // En caso de que se decida enfrentar al enemigo entonces el enfrentamiento 
            // se decide en tres rounds donde el ganador debe ganar al menos dos rounds. 
            // La victoria por round se decide de manera aleatoria en el rango de 0 y 1.
            // 0 es victoria para el enemigo, 1 para el heroe. 
            // Debe imprimir el resultado de cada round. 
            // Si el enemigo es ganador o victorioso entonces el heroe muere y se debe 
            // actualizar la variable fin para que indique si se acabó o no. 
            
            // Si el heroe desea huir entonces se debe elegir una dirección o 
            // puerta al azar que el heroe tomará. 
        }
        return fin;
    } 
    
    private Celda siguienteCelda ( Celda actual, int direccion ) {
        Celda next = actual;
        
        switch ( direccion ) {
            case NORTE:
                if ( actual.getX() > 0) {
                    next = celdas[ actual.getX() - 1][ actual.getY() ];
                } else if (actual.getX() == 0)
                    System.out.println("Ya no puedes moverte mas al Norte");
                break;
            case SUR:
                if ( actual.getX() < 9 ) {
                    next = celdas[ actual.getX() + 1][ actual.getY() ];
                } else if (actual.getX() == 9)
                    System.out.println("Ya no puedes moverte mas al Sur");
                break;
            case ESTE:
                if ( actual.getY() > 0 ) {
                    next = celdas[ actual.getX()][ actual.getY() + 1 ];
                } else if (actual.getY() == 0)
                    System.out.println("Ya no puedes moverte mas al Este");
                break;
            case OESTE:
                if ( actual.getY() < 9 ) {
                    next = celdas[ actual.getX()][ actual.getY() - 1 ];
                } else if (actual.getY() == 9)
                    System.out.println("Ya no puedes moverte mas al Oeste");
                break;
        } 
        
        return next;
    }
    
    // PorHacer: El siguiente código no imprime las celdas que no tienen enemigo, 
    // pozo, salida o heroe. Una celda normal imprime solamente 4 espacios en blanco
    /**
     * Imprime el contenido del tablero. Se imprimen valores enteros con el siguiente 
     * significado: 
     * H - Celda con Heroe
     * HE - Celda con Heroe y Enemigo
     * HP - Celda con Heroe y Pozo
     * HS - Celda con Hereo y Salida
     * E - Celda con Enemigo
     * P - Celda Pozo
     * S - Celda Salida
     */
    private void imprimir() {
        System.out.println("--------------------------------------------------");
        Celda c = null;
        Celda p = heroe.getCeldaActual();
        
        for (int x = 0; x < celdas.length; x++) {
            for (int y = 0; y < celdas.length; y++) {
                c = celdas[x][y];
                // c == p significa Estoy por imprimir una celda que el heroe está visitando? 
                if( c == p && c.isEnemigo() ) {
                    System.out.print(" HE ");
                } else if( c == p && c.isPozo() ) {
                    System.out.print(" HP ");
                } else if( c == p && c.isSalida() ) {
                    System.out.print(" HS ");
                } else if( c == p ) {
                    System.out.print("  H ");
                } else if( c.isEnemigo() ) {
                    System.out.print("  E ");
                } else if( c.isPozo() ) {
                    System.out.print("  P ");
                } else if( c.isSalida() ) {
                    System.out.print("  S ");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("");
        }
        System.out.println("--------------------------------------------------");
    }  
}
