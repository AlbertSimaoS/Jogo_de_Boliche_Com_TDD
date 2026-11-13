package br.edu.ifsp.boliche;

public class Jogo {

    private final int[] jogadas = new int[21];
    private int quantidade = 0;
    private int numeroFrame = 0;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        if (pinos < 0) {
            throw new IllegalArgumentException("Pinos derrubados não pode ser negativo: " + pinos);
        }
        if (pinos > 10) {
            throw new IllegalArgumentException("Pinos derrubados não pode ser maior que 10: " + pinos);
        }
        if (encerrado()) {
            throw new IllegalStateException("O jogo já foi encerrado!");
        }

        jogadas[quantidade++] = pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        int pontos = 0;
        int i = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (jogadas[i] == 10) {
                pontos = pontos + 10 + jogadas[i + 1] + jogadas[i + 2];
                i = i + 1;
            } else if (jogadas[i] + jogadas[i + 1] == 10) {
                pontos = pontos + 10 + jogadas[i + 2];
                i = i + 2;
            } else {
                pontos = pontos + jogadas[i] + jogadas[i + 1];
                i = i + 2;
            }
        }
        return pontos;
    }

    public int frame(int primeira) {
        numeroFrame++;
        jogar(primeira);
        return pontuacao();
    }

    public int frame(int primeira, int segunda) {
        numeroFrame++;

        jogar(primeira);
        jogar(segunda);

        return pontuacao();
    }

    public int frame(int primeira, int segunda, int terceira) {
        numeroFrame++;

        if (numeroFrame != 10) {
            throw new IllegalStateException("Três jogadas são permitidas apenas no 10º frame.");
        }

        jogar(primeira);
        jogar(segunda);
        jogar(terceira);

        return pontuacao();
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() {
        int indice = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (indice >= quantidade) {
                return false;
            }
            int primeira = jogadas[indice++];
            if (frame == 9) {
                if (primeira == 10) {
                    return quantidade >= indice + 2;
                }
                if (indice >= quantidade) {
                    return false;
                }
                int segunda = jogadas[indice++];
                if (primeira + segunda == 10) {
                    return quantidade >= indice + 1;
                }
                return true;
            }
            if (primeira != 10) {
                if (indice >= quantidade) {
                    return false;
                }
                indice++;
            }
        }
        return true;
    }
}