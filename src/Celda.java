/**
 *
 * @author nfavela
 */
class Celda {
    private int x;
    private int y;
    private int monedas = 5;
    public boolean pozo;
    public boolean enemigo;
    public boolean salida;

    public boolean isPozo() {
        return pozo;
    }

    public void setPozo(boolean pozo) {
        this.pozo = pozo;
    }

    public boolean isEnemigo() {
        return enemigo;
    }

    public void setEnemigo(boolean enemigo) {
        this.enemigo = enemigo;
    }

    public boolean isSalida() {
        return salida;
    }

    public void setSalida(boolean salida) {
        this.salida = salida;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
