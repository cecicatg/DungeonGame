/**
 *
 * @author nfavela
 */
public abstract class Personaje {
    private Celda celdaActual;
    private int celdasVisitadas;

    public Celda getCeldaActual() {
        return celdaActual;
    }

    public void setCeldaActual(Celda actual) {
        this.celdaActual = actual;
        celdasVisitadas++;
    }

    public int getCeldasVisitadas() {
        return celdasVisitadas;
    }

    public void setCeldasVisitadas(int celdasVisitadas) {
        this.celdasVisitadas = celdasVisitadas;
    }
    
    public abstract String getEstadisticas();
}
