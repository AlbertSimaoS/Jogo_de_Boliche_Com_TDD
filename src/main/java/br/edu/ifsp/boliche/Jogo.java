package br.edu.ifsp.boliche;

public class Jogo {

    private static final int FRAMES = 10;
    private static final int TOTAL_DE_PINOS = 10;

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

        if (ehSegundaJogadaDeFrameNormal() && jogadas[quantidade - 1] + pinos > 10) {
            throw new IllegalArgumentException("O frame não pode passar de 10 pinos");
        }

        jogadas[quantidade++] = pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        int pontos = 0;
        int i = 0;
        for (int frame = 0; frame < FRAMES; frame++) {
            if (ehStrike(i)) {          // strike tem que vir antes do spare
                pontos += pontosDeStrike(i);
                i += 1;
            } else if (ehSpare(i)) {
                pontos += pontosDeSpare(i);
                i += 2;
            } else {
                pontos += pontosDeFrameAberto(i);
                i += 2;
            }
        }
        return pontos;
    }

    private boolean ehStrike(int i) {
        return jogadas[i] == TOTAL_DE_PINOS;
    }

    private boolean ehSpare(int i) {
        return jogadas[i] + jogadas[i + 1] == TOTAL_DE_PINOS;
    }

    private int pontosDeStrike(int i) {
        return TOTAL_DE_PINOS + jogadas[i + 1] + jogadas[i + 2];
    }

    private int pontosDeSpare(int i) {
        return TOTAL_DE_PINOS + jogadas[i + 2];
    }

    private int pontosDeFrameAberto(int i) {
        return jogadas[i] + jogadas[i + 1];
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

    private boolean ehSegundaJogadaDeFrameNormal() {
        int indice = 0;
        for (int frame = 0; frame < 9; frame++) {
            if (indice >= quantidade) {
                return false;
            }
            if (jogadas[indice] == 10) {
                indice++;
            } else if (indice + 1 == quantidade) {
                return true;
            } else {
                indice += 2;
            }
        }
        return false;
    }
}