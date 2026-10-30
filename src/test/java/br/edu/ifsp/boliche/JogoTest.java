package br.edu.ifsp.boliche;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JogoTest {

    @Test
    void nenhumPino() {
        Jogo jogo = new Jogo();
        for (int i = 0; i < 20; i++) {
            jogo.jogar(0);
        }
        assertTrue(jogo.pontuacao() == 0);
    }

    @Test
    void umPinoPorJogada() {
        Jogo jogo = new Jogo();
        for (int i = 0; i < 20; i++) {
            jogo.jogar(1);
        }
        assertTrue(jogo.pontuacao() == 20);
    }

    @Test
    void umSpare(){
        Jogo jogo = new Jogo();
        jogo.jogar(5);
        jogo.jogar(5);
        jogo.jogar(3);
        for (int i = 0; i < 17; i++){
            jogo.jogar(0);
        }
        assertTrue(jogo.pontuacao() == 16);
    }
}
