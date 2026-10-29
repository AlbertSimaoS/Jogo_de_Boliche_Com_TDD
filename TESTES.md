# Lista de testes (TDD)

## Pontuação (partidas de exemplo)

- [ ] Nenhum pino: 20 jogadas de 0 → 0
- [ ] Um pino por jogada: 20 jogadas de 1 → 20
- [ ] Um spare: 5, 5, 3 e 17 jogadas de 0 → 16
- [ ] Um strike: 10, 3, 4 e 16 jogadas de 0 → 24
- [ ] Spare em todos os frames: 21 jogadas de 5 → 150
- [ ] Jogo perfeito: 12 jogadas de 10 → 300
- [ ] Partida mista: 10 · 7,3 · 9,0 · 10 · 0,8 · 8,2 · 0,6 · 10 · 10 · 10,8,1 → 167

## Validações

- [] V1: jogada < 0 lança `IllegalArgumentException`
- [] V1: jogada > 10 lança `IllegalArgumentException`
- [] V2: frames 1–9, segunda jogada que passa de 10 pinos (7 e 4) lança `IllegalArgumentException`
- [] V2: a regra vale além do primeiro frame; soma exatamente 10 é válida; strike seguido de strike é válido
- [] V2: jogada inválida não é registrada
- [] V3: jogada depois de 20 jogadas sem bônus lança `IllegalStateException`
- [] V3: jogada depois das jogadas extras (jogo perfeito, spare e strike no 10º frame) lança `IllegalStateException`