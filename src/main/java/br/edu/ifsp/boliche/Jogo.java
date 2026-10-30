package br.edu.ifsp.boliche;

public class Jogo {

    private int total = 0;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        total += pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        return total;
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() { return false; }

}