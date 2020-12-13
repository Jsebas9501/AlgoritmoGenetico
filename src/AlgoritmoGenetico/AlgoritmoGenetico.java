
package AlgoritmoGenetico;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Jhon Sebastian Molina Arias
 * @author Mateo Gallego Chaverra 
 */
public class AlgoritmoGenetico {
    
    public static float probCruce = 0.5f;
    public static float probMutacion = 0.7f;
    public static int nIndividuos = 5;
    public static int nCromosomas = 8;
    public static int nHijosCruce = 2;
    public static int nGeneracion = 0;
    
    public static Individuo [] Individuos;
    public static Individuo [] Hijos;
    
    
    public static void main(String[] args) {
        
        Individuos = new Individuo[nIndividuos];  
        OperadoresGeneticos operadores = new OperadoresGeneticos();
        
        int conBasquetProfesionales = 0;
        
        
        Individuos = generarPoblacionInicial();
        
        imprimirDatosIniciales();    
        
        do { 
            conBasquetProfesionales = 0;
           
            imprimirIndividuos();
            operadores.crearNuevaGeneracion(Individuos);
            
            nGeneracion++;
            
            for (int j = 0; j < nIndividuos; j++) {
                if(75 < Individuos[j].getPromHabilidades()){
                    conBasquetProfesionales++;
                }
            }
     
        } while(conBasquetProfesionales != nIndividuos);
        
        imprimirIndividuos();
        
        System.out.println();
        System.out.println("\n/================ GENERACION " + (nGeneracion+1)+ " ES LA FINAL ================/\n");
    }
    
    
    public static void imprimirDatosIniciales(){
        System.out.println("Población: Basquetbolistas con habilidades para pertenecer al equipo");
        System.out.println("Número de individuos: " + nIndividuos);
        System.out.println("Función fitness: Σ Habilidad");
        System.out.println("Selección: Heurística");
        System.out.println("Cruce: 1 Punto");
        System.out.println("Mutación: Heurística");
        System.out.println("Probabilidad de cruce: " + (probCruce*100) + "%");
        System.out.println("probabilidad de mutación: " + (probMutacion*100) + "%");
        System.out.println("Criterio de parada: Todos los individuos tienen que tener un valor promedio mayor a 75");
        System.out.println("\nHABILIDADES: \n");
        
        //Habilidades evaluadas
        System.out.println("at: Ataque");
        System.out.println("df: Defensa");
        System.out.println("vl: Velocidad");
        System.out.println("tr: Tiro de 3 puntos");
        System.out.println("md: Tiro de Media Mistancia");
        System.out.println("tl: Tiro libre");
        System.out.println("ps: Pase");
        System.out.println("fs: Fisico");
        System.out.println("");
        
    }
    
    public static void imprimirIndividuos(){
        System.out.println("\n/================ GENERACIÓN "+(nGeneracion+1)+" ================/\n");
        System.out.println("\n/********************** Individuos de la generación " + (nGeneracion+1) + " **********************/\n");
        System.out.println("");
        System.out.println("\t\tat\t df\t vl\t tr\t md\t tl\t ps\t fs\t");
        
        for (int i = 0; i < nIndividuos; i++) {
            
            System.out.print("Individuo " + (i+1) + ":\t");
            
            for (int j = 0; j < nCromosomas; j++) {
                
               System.out.print(Individuos[i].getCromo(j) + "\t");   
            }
            
            System.out.println();
            System.out.print("Habilidades: \t");
            
            for (int j = 0; j < nCromosomas; j++) {    
                System.out.print(Individuos[i].getPunHabilidades()[j] + "\t"); 
            }
            
            System.out.println("Valor promedio de habilidades: " + Individuos[i].getPromHabilidades());
            System.out.println("\n");
        }
    }
    
    public static Individuo[] generarPoblacionInicial() {
        Individuo[] individuosGenerados = new Individuo[nIndividuos];
        int idIndividuo = 0;
        
        for (int i = 0; i < nIndividuos; i++) {
            individuosGenerados[i] = generarIndividuo(idIndividuo);
            idIndividuo++;
        }
        
        return individuosGenerados;
    }
    
    public static Individuo generarIndividuo(int id){ 
        int [] cromosomas = new int [nCromosomas];
        int [] habilidades = new int [nCromosomas];
        
        for (int i = 0; i < nCromosomas; i++) {    
            int valorCromosoma = valoresAleatoriosEnteros("cromosoma"); 
            cromosomas[i] = valorCromosoma;
            
            if (valorCromosoma == 1) {
                habilidades[i] = valoresAleatoriosEnteros("valorHabillidades");//
            } else {
                habilidades[i] = 0;
            }  
            
        } 
        
        Individuo futbolista = new Individuo(id,cromosomas,habilidades);
        
        return futbolista;
    }
    
    public static int valoresAleatoriosEnteros(String tipoValor){
        switch(tipoValor){
            case "cromosoma":
                return (int) (Math.random() * 2);
            case "valorHabillidades":
                return (int) (Math.random() * 100) + 1;
        }       
        return 0;
    } 
    
    public static float valoresAleatoriosReales() {
        BigDecimal bd = new BigDecimal((Math.random() * 1)).setScale(2, RoundingMode.HALF_UP);
        return (float) bd.doubleValue();
    }
}
