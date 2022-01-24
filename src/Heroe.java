/**
 *
 * @author nfavela
 */
public class Heroe extends Personaje {
    private int monedas = 5;
    private int numEnemigosVencidos;

    public int getNumEnemigosVencidos() {
        return numEnemigosVencidos;
    }

    public void setNumEnemigosVencidos(int numEnemigosVencidos) {
        this.numEnemigosVencidos = numEnemigosVencidos;
    }
    
    public void incrementarEnemigosVencidos() {
        this.setNumEnemigosVencidos( this.getNumEnemigosVencidos() + 1);
    }

    public Heroe() {
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    @Override
    public void setCeldaActual(Celda nueva) {
        
        if ( nueva == super.getCeldaActual() ) 
        {
            return;
        }
        super.setCeldaActual(nueva);
        monedas += nueva.getMonedas();
    }
    
    public String getEstadisticas() {

        return "Monedas: " + monedas + 
                ", Enemigos Vencidos: " + numEnemigosVencidos + 
                ", Celdas visitadas: " + super.getCeldasVisitadas();
    }
    
}
