package br.edu.ifsp.boliche;

public class Jogo {

    private final int[] jogadas = new int[21];
    private int quantidade = 0;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        jogadas[quantidade++] = pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        int pontos = 0;
        int i = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (jogadas[i] + jogadas[i + 1] == 10) {
                if(i == 18)
                    pontos = pontos + 10 + jogadas[i + 1];
                else
                    pontos = pontos + 10 + jogadas[i + 2];
                i = i + 2;
            } else if (jogadas[i] == 10) {
                pontos = pontos + 10 + jogadas[i + 1] + jogadas[i + 2];
                i = i + 1;
            } else {
                pontos = pontos + jogadas[i] + jogadas[i + 1];
                i = i + 2;
            }
        }
        return pontos;
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() { return false; }
}