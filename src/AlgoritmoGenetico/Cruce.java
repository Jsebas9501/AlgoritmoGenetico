package AlgoritmoGenetico;

public class Cruce {

    private Individuo[] padres;
    private Individuo[] hijosCruzados;
    float[] probCruce1;
    float[] probCruce2;
    int pCruce;

    public Cruce(Individuo[] pareja) {
        this.padres = pareja;
        this.hijosCruzados = new Individuo[2];
        probCruce1 = new float[AlgoritmoGenetico.nCromosomas];
        probCruce2 = new float[AlgoritmoGenetico.nCromosomas];
    }

    public Individuo[] cruzar() {

        hijosCruzados[0] = new Individuo(this.padres[0].getId(),
                this.padres[0].getCromo().clone(),
                this.padres[0].getPunHabilidades().clone());

        hijosCruzados[1] = new Individuo(padres[1].getId(),
                padres[1].getCromo().clone(),
                padres[1].getPunHabilidades().clone());

        generarProbabiliadesCruce();
        pCruce = buscarPuntoCruce();

        for (int i = 0; i < AlgoritmoGenetico.nCromosomas; i++) {
            if (pCruce < i) {
                hijosCruzados[0].setCromo(i, padres[1].getCromo(i));
                hijosCruzados[0].setPunHabilidad(i, padres[1].getPunHabilidad(i));

                hijosCruzados[1].setCromo(i, padres[0].getCromo(i));
                hijosCruzados[1].setPunHabilidad(i, padres[0].getPunHabilidad(i));
            }
        }

        return this.hijosCruzados;
    }

    public void generarProbabiliadesCruce() {
        for (int i = 0; i < AlgoritmoGenetico.nCromosomas; i++) {
            probCruce1[i] = AlgoritmoGenetico.valoresAleatoriosReales();
            probCruce2[i] = AlgoritmoGenetico.valoresAleatoriosReales();
        }

        padres[0].setValorProbaCruce(probCruce1);
        padres[1].setValorProbaCruce(probCruce2);
    }

    public int buscarPuntoCruce() {
        int posicionPuntoCruce = 0;

        float distanciaMasCortaPuntoCruce = Math.abs(AlgoritmoGenetico.probCruce
                - padres[0].getValorProbaCruce()[0]);

        for (int i = 0; i < AlgoritmoGenetico.nCromosomas; i++) {

            float comparador0 = Math.abs(AlgoritmoGenetico.probCruce
                    - padres[0].getValorProbaCruce()[i]);

            float comparador1 = Math.abs(AlgoritmoGenetico.probCruce
                    - padres[1].getValorProbaCruce()[i]);

            if (comparador0 < comparador1) {

                if (comparador0 < distanciaMasCortaPuntoCruce) {
                    posicionPuntoCruce = i;
                    distanciaMasCortaPuntoCruce = comparador0;
                }
            } else {
                if (comparador0 < distanciaMasCortaPuntoCruce) {
                    posicionPuntoCruce = i;
                    distanciaMasCortaPuntoCruce = comparador1;
                }
            }
        }

        return posicionPuntoCruce;
    }

    public int getPuntoCruce() {
        return this.pCruce;
    }
}
