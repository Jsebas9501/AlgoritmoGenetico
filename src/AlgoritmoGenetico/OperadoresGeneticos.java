package AlgoritmoGenetico;

public class OperadoresGeneticos {

    private Individuo[] pareja;
    private Individuo[] hijos;
    private Individuo[] hijosMutados;
    int puntoCruce;   

    
    public OperadoresGeneticos() {
        pareja = new Individuo[2];
        hijos = new Individuo[2];
        hijosMutados = new Individuo[2];
    }

    public void crearNuevaGeneracion(Individuo[] individuos) {
        emparejar(individuos);
        mostrarIndividuos("Pareja seleccionada", pareja);
             
        Cruce cruce = new Cruce(pareja);
        hijos = cruce.cruzar();
        calcularPromedio(hijos);
        mostrarIndividuos("Hijos cruzados", hijos, cruce.getPuntoCruce());
        
        Mutacion mutacion = new Mutacion(hijos);
        hijosMutados = mutacion.mutar();
        calcularPromedio(hijosMutados);
        mostrarIndividuos("Hijos Mutados", hijosMutados, mutacion.getPuntoMutacion0(), mutacion.getPuntoMutacion1());
        
        insertarNuevosIndividuos(individuos);
    }

    public void insertarNuevosIndividuos(Individuo[] individuos) {
        int buscadorParejaAtras = 0;
        float valorPromedioBuscadorAtras = individuos[0].getPromHabilidades();

        int buscadorParejaActual = 1;
        float valorPromedioBuscadorActual = individuos[1].getPromHabilidades();

        for (int i = 2; i < individuos.length; i++) {
            if (valorPromedioBuscadorActual > individuos[i].getPromHabilidades()) {
                buscadorParejaActual = individuos[i].getId();
                valorPromedioBuscadorActual = individuos[i].getPromHabilidades();
            }

        }

        for (int i = 0; i < individuos.length; i++) {
            if (valorPromedioBuscadorAtras > individuos[i].getPromHabilidades()
                    && i != buscadorParejaActual) {

                buscadorParejaAtras = individuos[i].getId();
                valorPromedioBuscadorAtras = individuos[i].getPromHabilidades();
            }
        }
        
        
        hijosMutados[0].setId(buscadorParejaActual);
        hijosMutados[1].setId(buscadorParejaAtras);
        
        individuos[buscadorParejaActual] = hijosMutados[0];
        individuos[buscadorParejaAtras] = hijosMutados[1];
    }
    
    public void calcularPromedio(Individuo[] futbolistas){
        for (int i = 0; i < 2; i++) {
            futbolistas[i].setSumaHabilidades();
            futbolistas[i].setPromedioHabilidades();
        }
    }
    
    public void mostrarIndividuos(String tipoIndividuos, Individuo[] futbolistas,int ...puntoCorte) {
        
        System.out.println("-------------------------------- " + tipoIndividuos + " --------------------------------");
        System.out.println();
        
        this.mostrarInformacionOperacion(tipoIndividuos, puntoCorte);

        for (int i = 0; i < 2; i++) {
             
            System.out.print("Individuo " + (i + 1) + ":\t");

            for (int j = 0; j < AlgoritmoGenetico.nCromosomas; j++) {
                
                System.out.print(futbolistas[i].getCromo(j) + "\t");
            }

            System.out.println();
            System.out.print("Habilidades: \t");

            for (int j = 0; j < AlgoritmoGenetico.nCromosomas; j++) {
                System.out.print(futbolistas[i].getPunHabilidades()[j] + "\t");
            }

            System.out.println("Valor promedio de habilidades: " + futbolistas[i].getPromHabilidades());
            System.out.println("\n");
        }
    }
    
    public void mostrarInformacionOperacion(String tipoIndividuos, int[] puntoCorte){

       switch(tipoIndividuos){
            case "Pareja seleccionada":
                break;
            case "Hijos cruzados":
                System.out.println("*** Informacion operacion: ***\n");
                System.out.println("Punto cruce: " +  (puntoCorte[0] + 1));
                System.out.println();
                break;
            case "Hijos Mutados":
                System.out.println("*** Informacion operacion: ***\n"); 
                System.out.println("Punto mutacion 1: " +  (puntoCorte[0] + 1));
                System.out.println("Punto mutacion 2: " +  (puntoCorte[1] + 1));
                System.out.println();
                break;
        }
    }
    
    public void emparejar(Individuo[] individuos) {
         
        int buscadorParejaAtras = 0;
        float valorPromedioBuscadorAtras = individuos[0].getPromHabilidades();

        int buscadorParejaActual = 1;
        float valorPromedioBuscadorActual = individuos[1].getPromHabilidades();

        for (int i = 2; i < individuos.length; i++) {
            if (valorPromedioBuscadorActual < individuos[i].getPromHabilidades()) {
                buscadorParejaActual = individuos[i].getId();
                valorPromedioBuscadorActual = individuos[i].getPromHabilidades();
            }

        }

        for (int i = 0; i < individuos.length; i++) {
            if (valorPromedioBuscadorAtras < individuos[i].getPromHabilidades()
                    && i != buscadorParejaActual) {

                buscadorParejaAtras = individuos[i].getId();
                valorPromedioBuscadorAtras = individuos[i].getPromHabilidades();
            }
        }

        pareja[0] = new Individuo(individuos[buscadorParejaActual].getId(),
                individuos[buscadorParejaActual].getCromo().clone(),
                individuos[buscadorParejaActual].getPunHabilidades().clone());

        pareja[1] = new Individuo(individuos[buscadorParejaAtras].getId(),
                individuos[buscadorParejaAtras].getCromo().clone(),
                individuos[buscadorParejaAtras].getPunHabilidades().clone());

    }

}