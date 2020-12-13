
package AlgoritmoGenetico;

import static java.lang.Float.parseFloat;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Individuo {
    private int id;
    private int [] cromo;
    private int [] punHabilidades;
    private float [] valorProbaCruce;
    private float [] valorProbMutacion;
    private int sumaHabilidades;
    private float promHabilidades;

    public Individuo(int id, int[] cromosomas,int[] puntuacionHabilidades) {
        this.id = id;
        this.cromo = cromosomas;
        this.punHabilidades = puntuacionHabilidades;
        this.setSumaHabilidades();
        this.setPromedioHabilidades();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int[] getCromo() {
        return cromo;
    }

    public void setCromo(int[] cromosomas) {
        this.cromo = cromosomas;
    }
    
    public int getCromo(int indice) {
        return cromo[indice];
    }

    public void setCromo(int indice, int valor) {
        this.cromo[indice] = valor;
    }
    
    public int[] getPunHabilidades() {
        return punHabilidades;
    }

    public void setPunHabilidades(int[] puntuacionHabilidades) {
        this.punHabilidades = puntuacionHabilidades;
    }
    
    public int getPunHabilidad(int indicie) {
        return punHabilidades[indicie];
    }
    
    public void setPunHabilidad(int indice, int valor){
         this.punHabilidades[indice] = valor;
    }

    public float[] getValorProbaCruce() {
        return valorProbaCruce;
    }

    public void setValorProbaCruce(float[] valorProbaCruce) {
        this.valorProbaCruce = valorProbaCruce;
    }

    public float[] getValorProbMutacion() {
        return valorProbMutacion;
    }
    
    public void setValorProbMutacion(float[] valorProbMutacion) {
        this.valorProbMutacion = valorProbMutacion;
    }

    public int getSumaHabilidades() {
        return sumaHabilidades;
    }

    public void setSumaHabilidades() { 
        
        this.sumaHabilidades = 0;
        
        for (int i = 0; i < punHabilidades.length ; i++) {
            this.sumaHabilidades = sumaHabilidades + punHabilidades[i];
        }
    }

    public float getPromHabilidades() {
        return promHabilidades;
    }

    public void setPromedioHabilidades() {
        
        float promedio = (float)this.getSumaHabilidades() / 7;

        BigDecimal bd = new BigDecimal(promedio).setScale(2, RoundingMode.HALF_UP);
        promedio = (float)bd.doubleValue();
       
        this.promHabilidades = promedio;
       
    }
    
}
