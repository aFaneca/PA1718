# Trabalho Prático de Programação Avançada - 2017/18
**Grupo:** G8

**Objetivo:** Simular o jogo 9CS - 9 Card Siege

## Como se joga o 9CS?
O jogo possui 9 cartas:
- 7 Cartas de Eventos
- 1 Carta de Enemy Track
- 1 Carta de Status Track

O Objetivo é defender uma fortaleza de um cerco de tropas inimigas. O cerco tem a duração de 3 dias. Em cada dia, 7 eventos acontecerão (representados por cada carta de eventos). **Se o jogador sobreviver até ao final do 3º dia, vence o jogo.**

### Objetivos
- Afastar os inimigos
- Manter a moral do povo alta
- Manter os suplementos (supplies)
Se algum dos objetivos não for cumprido, a população irá render-se e o jogador perde o jogo.

### As cartas de evento
Cada carta tem 3 eventos (serão ignorados os 2 eventos que não dizem respeito ao dia atual em que se vai no jogo).
Para cada evento, tem-se a seguinte informação:
- Nome do Evento (irrelevante)
- Divisão do dia (que permite saber qual evento corresponde a qual dia)
- O Evento em si
- As Ordens de avanço (o símbolo da(s) facção/facções inimigas que devem avançar)
- Action Point Allowances (o nr. de ações que o jogador pode fazer nesta jogada)
- Nr. da Carta (irrelevante)

### A carta de *Enemy Tracks*
A fortaleza é atacada por 3 tipos de inimigos diferentes, com forças diferentes:
- Inimigos a tentar escalar a fortaleza com escadas (Força: 2)
- Inimigos a tentar "rebentar" o portão com um aríete (Força: 3)
- Inimigos numa torre de cerco a tentar movimentar-se até à fortaleza (Força: 4)

Todos os inimigos começam a "3" unidades de distância da fortaleza. Os inimigos vão avançando para perto do Castelo até chegarem à Closed Combat Area. **Uma vez nessa zona, todos têm força = 4**.

Na parte inferior da carta, existe um *trebuchet count*. Inicialmente, o jogador tem 3 aríetes à sua disposição.

### A carta de *Status Tracks*
A carta mostra as forças atuais dos recursos do jogador e um mapa para o túnel, que permite chegar até às linhas inimigas.
Todos os recursos começam no topo (em "4"). Quando algum dos recursos chegar a "0", o jogador é forçado a render-se e perde o jogo.

Os 3 recursos que o jogador tem que controlar são:
- A força da muralha
- A moral do povo
- Os suprimentos existentes dentro da fortaleza

O jogador pode tentar movimentar alguns soldados pelo túnel para o meio das unidades inimigas. Inicialmente, os jogadores estão todos no castelo e ele terá que os movimentar aos poucos até passarem o túnel (o castelo representa 1 unidade e o túnel 2 unidades de distância).

Existe também uma área onde se contabiliza a quantidade de suprimentos que foram capturados do inimigo (*raided*). A quantidade máxima de unidades que os seus soldados podem carregar é de 2.

## Links Úteis
- [REGRAS DO JOGO](https://github.com/aFaneca/PA1718/blob/master/Documenta%C3%A7%C3%A3o/9CS_Rules_20171217.pdf)
- [PlayerAid](https://github.com/aFaneca/PA1718/blob/master/Documenta%C3%A7%C3%A3o/9CS_PlayerAid_20171217.pdf)
- [Cartas (P/ Impressão)](https://github.com/aFaneca/PA1718/blob/master/Documenta%C3%A7%C3%A3o/9CS_Cards_20171217.pdf)
