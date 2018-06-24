# Trabalho Prático de Programação Avançada - 2017/18
**Grupo:** G8

**Objetivo:** Simular o jogo 9CS - 9 Card Siege

## Screenshots
![Novo Jogo](http://afaneca.com/imagens/9CS-novoJogo.png)

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

Todos os inimigos começam a "3" unidades de distância da fortaleza. Os inimigos vão avançando para perto do Castelo até chegarem à Closed Combat Area. **Uma vez nessa zona, todos têm força = 4**. Se mais do que 2 unidades estiverem nessa fase ao mesmo tempo, o jogador perde controlo da fortaleza e perde automaticamente o jogo.

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

### Sequência de Ações
No início de cada turno, devem ser realizadas as seguintes ações, pela sua ordem:

1. **Verificar se se tem soldados no túnel.**
  Se sim:
    - Rodar um dado
      - Se sair 2-6, os soldados podem continuar pelo túnel, porque não foram detetados.
      - Se sair 1, os soldados foram capturados (ver [Soldados Capturados])
      
2. **Retirar Carta do Topo do Baralho**
    1. Aplicar o evento da carta
    2. Avançar os inimigos como instruído na carta
    3. Tomar as ações permitidas pelo Action Points Allowances da carta
3. **Realizar evento**
  - Existem dois tipos de evento:
      1. Eventos Regulares 
      2. Eventos de Ataque Trebuchet (o inimigo automaticamente lança todos os seus aríetes.
        - Se o inimigo tem 3 trebuchets (força total)
          - A força da muralha é automaticamente reduzida em 2 unidades
        - Se o inimigo tem 2 trebuchets
          - A força da muralha é automaticamente reduzida em 1 unidade
        - Se o inimigo tem 1 trebuchet
          - Rodar Dado
            - Se resultado é 4, 5 ou 6 => a muralha é atingida e a sua força é reduzida em 1 unidade
            - Se resultado é 1, 2 ou 3 => a muralha não é atingida 
4. **Movimento dos Inimigos**
   - Nenhuma unidade inimiga se deve mover quando existem ataques de trebuchets
   - Uma carta pode mandar mover:
      - Nenhuma facção
      - 1 ou mais facções
      - A facção mais lenta (com o ícone da espada; as unidades mais longe do castelo, avançarão 1 posição; se 2 ou mais estiverem na posição mais atrasada, ambas as facções avançam)
 
5. **Ações do Jogador**
  - O círculo amarelo na lateral esquerda do evento determina a quantidade de ações que o jogador pode efetuar. O jogador é obrigado a gastar pelo menos 1 dessas ações por jogada.
  - As ações disponíveis são:
    - Ataque de Arqueiros
    - Ataque de Agua a Ferver (Boiling Water)
    - Ataque de Close Combat
    - Coupure (reparar danos na muralha)
    - Motivar as tropas (Rally Troops)
    - Movimentar soldados no túnel
    - Fazer raid por supplies
    - Sabotagem
   
  - Todas as ações podem ser efetuadas múltiplas vezes numa jogada, excepto o ataque de água a ferver.
   
    1. **Ataque de Arqueiros**
      - Só pode ser usado contra Escadas, Aríetes e Torres de cerco.
      - O jogador deve escolher a track que quer atacar e rodar um dado.
        - Se o resultado for superior à força dessa facção => a facção inimiga afasta-se 1 unidade do castelo
        - Senão => o ataque falhou
    2. **Ataque de Água a Ferver**
      - Só pode ser usado contra inimigos num espaço circular.
      - A ação é igual ao Ataque de Arqueiros, mas com **+1 DRM** (acrescenta-se 1 valor ao resultado do dado).
      - Se o resultado do dado for "1", a moral do povo é reduzida em 1 unidade, para além do ataque falhar (se o evento em ação tiver 1 bónus adicional de +1 DRM, esta consequência é ignorada).
      - Esta ação só pode ser jogada 1 vez por turno. Se + do que 1 facção estiverem numa área circular, deve escolher 1 para atacar.
    3. **Close Combat**
      - Todas as unidades inimigas nessa área tem Força = 4.
      - Rodar um dado (para cada uma das unidades inimigas nesta área):
        - Se resultado > 4 => afastar essa facção 1 unidade
        - Se resultado == 1 => reduz a moral do povo em 1 unidade
        - Senão => Perde o jogo
      - Se no final do turno, estiverem 2 facções nesta área => perde o jogo.
      - Os DRM (Die Roll Modification) não têm efeito sobre as unidades inimigas nesta área
   4. **Reparar Muralha**
      - Rodar um dado
        - Se resultado > 4 => A força da Muralha aumenta em 1 unidade
        - Senão => nada acontece
   5. **Motivar as Tropas**
      - Rodar um dado
        - Se resultado > 4 => A moral do povo aumenta em 1 unidade
        - Senão => nada acontece
        - (OPCIONAL) O Jogador pode aumentar as chances de sucesso, ao dar ao povo 1 unidade de supplies (fica com -1 supplies), ganhando assim um bónus de +1 DRM (ou seja, adiciona +1 ao resultado do dado). <- Esta decisão é tomada ANTES de rodar o dado
   6. **Movimentar Soldados no Túnel**
      - Existem 3 possibilidades de movimento no túnel:
        - Free => o jogador tem direito a 1 free movement por turno (sem gastar action points) <- sair do túnel tb é um free movement!
        - Fast => o soldado movimenta-se diretamente até ao fim do túnel (custa 1 AP para entrar no túnel e +1 para atravessar imediatamente o túnel)
        - Automatic Night Movement => Todas as unidades no túnel ao fim do dia são movidas de volta até ao castelo; esta ação não tem custos.
      - Se estiverem unidades em território inimigo ao final do dia, serão automaticamente capturadas.
   7. **Fazer raid de supplies**
      - Só pode acontecer quando tiver unidades em território inimigo
      - Rodar um dado
        - Se 3,4 ou 5 => raid com sucesso de 1 supply
        - Se 6 => raid com sucesso de 2 supplies
        - Se 1 => os soldados são capturados
   8. **Sabotagem**
      - Só pode acontecer quando tiver unidades em território inimigo
      - Os soldados tentam danificar os aríetes (trebuchets) para impedir que disparem
      - Rodar um dado
        - Se 5 ou 6 => 1 trebuchet é danificado (inimigos ficam com menos 1 trebuchet)
        - Se 1 => os soldados são capturados
      - A sabotagem pode ser feita ao mesmo tempo que se carregam supplies
   9. **Action Points Adicionais**
      - Pode ter acesso a action points (AP) adicionais se sacrificar a sua moral, por exemplo.
      - Baixar em 1 unidade o valor da sua moral ou supply => acesso a +1 AP
      - Só se pode ter acesso a 1 AP adicional por turno
        
6. **Verificar se ganhou ou perdeu o jogo**
    - No final de cada turno, verificar se ganhou ou perdeu o jogo.
    - Ao final do turno, perdeu o jogo se:
      - 2 facções estiverem na zona de close combat
      - 1 das Forças (muralha, moral ou supplies) estiver a 0
    - Perde o jogo **imediatamanete** se:
      - Uma 3ª facção avançar para a zona de close combat
      - Uma segunda força fica a 0
    - Ganhou o jogo se tiver aguentado até ao final do terceiro dia.
 
 7. **Fase do Final do Dia**
  - 1 dia acaba quando todas as 6 cartas de eventos tiverem sido jogadas
  - Ao final de cada dia:
    - As cartas devem ser rebaralhadas para o próximo dia
    - Reduzir os supplies em 1 unidade
    - Mover soldados no túnel de volta para o castelo
    - Soldados em território inimigo são capturados
  
  
### Quando soldados são capturados
  - O que fazer:
    1. Os soldados são abandonados e substituídos por novos soldados no castelo
    2. A moral é reduzida em 1 unidade
    

    
## Links Úteis
- [REGRAS DO JOGO](https://github.com/aFaneca/PA1718/blob/master/Documenta%C3%A7%C3%A3o/9CS_Rules_20171217.pdf)
- [PlayerAid](https://github.com/aFaneca/PA1718/blob/master/Documenta%C3%A7%C3%A3o/9CS_PlayerAid_20171217.pdf)
- [Cartas (P/ Impressão)](https://github.com/aFaneca/PA1718/blob/master/Documenta%C3%A7%C3%A3o/9CS_Cards_20171217.pdf)
