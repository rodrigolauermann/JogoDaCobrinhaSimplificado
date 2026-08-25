# JogoDaCobrinhaSimplificado
Jogo da cobrinha simples feito em Java.

## Classes
- **Snake** - representa as posições da cobra.
- **Food** - gera a posição da maçã.
- **Coordinate** - utilizada pelas classes Snake e Food.
- **Board** - responsável pela visualização do tabuleiro e da cobra.
- **Game** - responsável pela lógica do jogo.
- **App** - ponto de partida do programa.

## Como executar
 
### Pré-requisitos
- Java JDK 17 (ou versão compatível definida no `pom.xml`)
- Maven instalado (`mvn -version` para conferir)
  
### Clonar o repositório
```bash
git clone https://github.com/rodrigolauermann/JogoDaCobrinhaSimplificado.git
cd JogoDaCobrinhaSimplificado/jogodacobrinha
```
 
### Executar
```bash
mvn exec:java
```
