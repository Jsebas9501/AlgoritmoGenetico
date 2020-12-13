package AlgoritmoGenetico;

public class Mutacion {

    private Individuo[] hijosCruzados;
    private Individuo[] hijosMutados;
    float[] proMutacion0;
    float[] proMutacion1;
    int puntoMutacion0;
    int puntoMutacion1;

    public Mutacion(Individuo[] hijos) {
        this.hijosCruzados = hijos;
        this.hijosMutados = new Individuo[2];
        proMutacion0 = new float[AlgoritmoGenetico.nCromosomas];
        proMutacion1 = new float[AlgoritmoGenetico.nCromosomas];
    }

    public Individuo[] mutar() {
        hijosMutados[0] = new Individuo(hijosCruzados[0].getId(),
                hijosCruzados[0].getCromo().clone(),
                hijosCruzados[0].getPunHabilidades().clone());

        hijosMutados[1] = new Individuo(hijosCruzados[1].getId(),
                hijosCruzados[1].getCromo().clone(),
                hijosCruzados[1].getPunHabilidades().clone());

        generarProbabilidadMutacion();
        int[] puntoMutacion = buscarPuntosMutacion();

        boolean posicionEncontrada0 = false;
        boolean posicionEncontrada1 = false;

        for (int i = 0; i < AlgoritmoGenetico.nCromosomas; i++) {

            if (puntoMutacion[0] == i && !posicionEncontrada0) {
                if (hijosCruzados[0].getCromo(i) == 0) {
                    hijosMutados[0].setCromo(i, 1);
                    hijosMutados[0].setPunHabilidad(i, AlgoritmoGenetico.valoresAleatoriosEnteros("valorHabillidades"));
                } else {
                    hijosMutados[0].setPunHabilidad(i, AlgoritmoGenetico.valoresAleatoriosEnteros("valorHabillidades"));
                }

                posicionEncontrada0 = true;
            }

            if (puntoMutacion[1] == i && !posicionEncontrada1) {
                if (hijosCruzados[1].getCromo(i) == 0) {
                    hijosMutados[1].setCromo(i, 1);
                    hijosMutados[1].setPunHabilidad(i, AlgoritmoGenetico.valoresAleatoriosEnteros("valorHabillidades"));
                } else {
                    hijosMutados[1].setPunHabilidad(i, AlgoritmoGenetico.valoresAleatoriosEnteros("valorHabillidades"));
                }

                posicionEncontrada1 = true;
            }

            if (posicionEncontrada0 && posicionEncontrada1) {
                break;
            }

        }
        this.puntoMutacion0 = puntoMutacion[0];
        this.puntoMutacion1 = puntoMutacion[1];

        return hijosMutados;
    }

    public void generarProbabilidadMutacion() {
        for (int i = 0; i < AlgoritmoGenetico.nCromosomas; i++) {
            proMutacion0[i] = AlgoritmoGenetico.valoresAleatoriosReales();
            proMutacion1[i] = AlgoritmoGenetico.valoresAleatoriosReales();
        }

        hijosCruzados[0].setValorProbMutacion(proMutacion0);
        hijosCruzados[1].setValorProbMutacion(proMutacion1);
    }

    public int[] buscarPuntosMutacion() {
        int[] posicionesPuntoMutacion = new int[2];

        float distanciaMasCortaPuntoMutacion0 = Math.abs(AlgoritmoGenetico.probMutacion 
                - hijosCruzados[0].getValorProbMutacion()[0]);

        float distanciaMasCortaPuntoMutacion1 = Math.abs(AlgoritmoGenetico.probMutacion
                - hijosCruzados[1].getValorProbMutacion()[0]);

        for (int i = 0; i < AlgoritmoGenetico.nCromosomas; i++) {

            float comparador0 = Math.abs(AlgoritmoGenetico.probMutacion
                    - hijosCruzados[0].getValorProbMutacion()[i]);

            float comparador1 = Math.abs(AlgoritmoGenetico.probMutacion
                    - hijosCruzados[1].getValorProbMutacion()[i]);

            if (comparador0 < distanciaMasCortaPuntoMutacion0) {
                posicionesPuntoMutacion[0] = i;
                distanciaMasCortaPuntoMutacion0 = comparador0;
            }

            if (comparador1 < distanciaMasCortaPuntoMutacion1) {
                posicionesPuntoMutacion[1] = i;
                distanciaMasCortaPuntoMutacion1 = comparador1;
            }
        }
        return posicionesPuntoMutacion;
    }

    public int getPuntoMutacion0() {
        return this.puntoMutacion0;
    }

    public int getPuntoMutacion1() {
        return this.puntoMutacion1;
    }

}
