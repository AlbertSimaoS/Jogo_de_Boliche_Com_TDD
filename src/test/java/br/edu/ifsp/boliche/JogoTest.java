package br.edu.ifsp.boliche;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JogoTest {

    private final Jogo jogo = new Jogo();

    @Test
    void nenhumPino() {
        for (int i = 0; i < 20; i++) {
            jogo.jogar(0);
        }
        assertTrue(jogo.pontuacao() == 0);
    }

    @Test
    void umPinoPorJogada() {
        for (int i = 0; i < 20; i++) {
            jogo.jogar(1);
        }
        assertTrue(jogo.pontuacao() == 20);
    }

    @Test
    void umSpare(){
        jogo.jogar(5);
        jogo.jogar(5);
        jogo.jogar(3);
        for (int i = 0; i < 17; i++){
            jogo.jogar(0);
        }
        assertTrue(jogo.pontuacao() == 16);
    }

    @Test
    void umStrike(){
        jogo.jogar(10);
        jogo.jogar(3);
        jogo.jogar(4);
        for(int i = 0; i < 17; i++)
            jogo.jogar(0);
        assertTrue(jogo.pontuacao() == 24);
    }

    @Test
    void spareEmTodosOsFrames(){
        for (int i = 0; i < 20; i++)
            jogo.jogar(5);
        assertTrue(jogo.pontuacao() == 150);
    }
}
