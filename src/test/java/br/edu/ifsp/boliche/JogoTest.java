package br.edu.ifsp.boliche;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        for(int i = 0; i < 16; i++)
            jogo.jogar(0);
        assertTrue(jogo.pontuacao() == 24);
    }

    @Test
    void spareEmTodosOsFrames(){
        for (int i = 0; i < 21; i++)
            jogo.jogar(5);
        assertTrue(jogo.pontuacao() == 150);
    }

    @Test
    void jogoPerfeito(){
        for(int i = 0; i < 12; i++)
            jogo.jogar(10);
        assertTrue(jogo.pontuacao() == 300);
    }

    @Test
    void jogoMisto(){
        jogo.frame(10);
        jogo.frame(7,3);
        jogo.frame(9,0);
        jogo.frame(10);
        jogo.frame(0,8);
        jogo.frame(8,2);
        jogo.frame(0,6);
        jogo.frame(10);
        jogo.frame(10);
        jogo.frame(10,8,1);
        assertTrue(jogo.pontuacao() == 167);
    }

    @Test
    void jogadaComPinosNegativosLancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> jogo.jogar(-1));
    }

    @Test
    void jogadaComMaisDeDezPinosLancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> jogo.jogar(11));
    }

    @Test
    void segundaJogadaQuePassaDeDezPinosNoFrameLancaIllegalArgumentException() {
        jogo.jogar(7);
        assertThrows(IllegalArgumentException.class, () -> jogo.jogar(4));
    }

    @Test
    void jogadaDepoisDeVinteJogadasSemBonusLancaIllegalStateException() {
        for (int i = 0; i < 20; i++)
            jogo.jogar(0);
        assertThrows(IllegalStateException.class, () -> jogo.jogar(0));
    }

    @Test
    void jogadaDepoisDoJogoPerfeitoLancaIllegalStateException() {
        for (int i = 0; i < 12; i++)
            jogo.jogar(10);
        assertThrows(IllegalStateException.class, () -> jogo.jogar(1));
    }

    @Test
    void jogadaDepoisDaJogadaExtraDoSpareLancaIllegalStateException() {
        for (int i = 0; i < 21; i++)
            jogo.jogar(5);
        assertThrows(IllegalStateException.class, () -> jogo.jogar(0));
    }

    @Test
    void jogadaDepoisDasDuasJogadasExtrasDoStrikeLancaIllegalStateException() {
        for (int i = 0; i < 18; i++)
            jogo.jogar(0);
        jogo.jogar(10);
        jogo.jogar(5);
        jogo.jogar(3);
        assertThrows(IllegalStateException.class, () -> jogo.jogar(0));
    }

    @Test
    void strikeNoDecimoFrameAindaAceitaAsJogadasExtras() {
        for (int i = 0; i < 18; i++)
            jogo.jogar(0);
        jogo.jogar(10);
        jogo.jogar(5);
        assertTrue(!jogo.encerrado());
        jogo.jogar(3);
        assertTrue(jogo.encerrado());
    }
}
