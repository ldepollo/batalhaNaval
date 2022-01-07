package br.com.batalhanaval.classes;

import java.util.Scanner;

public class Jogador {
    String[][] tabuleiro = new String[10][10];
    String[] barraTopoTabuleiro = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] barraLateralTabuleiro = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    Scanner input = new Scanner(System.in);
    private int tamanhoTabuleiroJogo;
    private int placar = 0;

    public Jogador(int opcaoDePreenchimento, int tamanhoTabuleiro) {
        tamanhoTabuleiroJogo = tamanhoTabuleiro;

        inicializarTabuleiro(tamanhoTabuleiroJogo);

        if (opcaoDePreenchimento == 1) {
            preencherTabuleiroNaMao(tamanhoTabuleiroJogo);
        } else {
            preencherTabuleiroRandomicamente(tamanhoTabuleiroJogo);
        }
    }

    public void imprimirTabuleiro() {
        System.out.println("-".repeat(4 * tamanhoTabuleiroJogo + 5));
        System.out.println(" ".repeat(2 * tamanhoTabuleiroJogo - 1) + "JOGADOR" + " ".repeat(2 * tamanhoTabuleiroJogo - 1));
        System.out.println("-".repeat(4 * tamanhoTabuleiroJogo + 5));
        System.out.printf("|   |");
        for (int i = 0; i < tamanhoTabuleiroJogo; i++) {
            System.out.printf(" %s |", barraTopoTabuleiro[i]);
        }
        System.out.printf("%n");
        for (int i = 0; i < tamanhoTabuleiroJogo; i++) {
            System.out.printf("| %s |", barraLateralTabuleiro[i]);
            for (int j = 0; j < tamanhoTabuleiroJogo; j++) {
                System.out.printf(" %s |", tabuleiro[i][j]);
            }
            System.out.printf("%n");
        }
    }

    public String getCasaTabuleiro(int x, int y) {
        return tabuleiro[x][y];
    }

    public void setCasaTabuleiro(int x, int y, String valor) {
        tabuleiro[x][y] = valor;
    }

    public int getPlacar() {
        return placar;
    }

    public void setPlacar(int placar) {
        this.placar = placar;
    }

    private void inicializarTabuleiro(int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = " ";
            }
        }
    }

    private void preencherTabuleiroNaMao(int tamanho) {
        Scanner input = new Scanner(System.in);
        String posicao;
        int posicaoLinha, posicaoColuna;

        imprimirTabuleiro();

        System.out.println("\u001B[34m" + "Informe as posições no formato Letra e Número (Exemplo: B3)" + "\u001B[0m");

        for (int i = 0; i < tamanho; i++) {
            do {
                System.out.println("\u001B[36m" + "Digite a " + (i + 1) + "ª posição:" + "\u001B[0m");
                posicao = input.nextLine();

                posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
                posicaoColuna = Utilidade.converterPosicaoColunaParaInt(posicao.substring(1));

                if (Utilidade.validarEntradaSemMensagem(posicaoLinha, posicaoColuna, tamanhoTabuleiroJogo)) {
                    if (tabuleiro[posicaoLinha][posicaoColuna].equals("N")) {
                        System.out.println("\u001B[31m" + "Essa posição já está preenchida!" + "\u001B[0m");
                        System.out.println("\u001B[36m" + "Digite a " + (i + 1) + "ª posição:" + "\u001B[0m");
                        posicao = input.nextLine();

                        posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
                        posicaoColuna = Utilidade.converterPosicaoColunaParaInt(posicao.substring(1));
                    }
                }
            } while (!Utilidade.validarEntrada(posicaoLinha, posicaoColuna, tamanhoTabuleiroJogo));
            tabuleiro[posicaoLinha][posicaoColuna] = "N";
        }

        input.close();
    }

    private void preencherTabuleiroRandomicamente(int tamanho) {
        int posicaoLinha, posicaoColuna;

        for (int i = 0; i < tamanho; i++) {
            posicaoLinha = (int) (Math.random() * tamanho);
            posicaoColuna = (int) (Math.random() * tamanho);

            if (tabuleiro[posicaoLinha][posicaoColuna].equals("N")) {
                do {
                    posicaoLinha = (int) (Math.random() * tamanho);
                    posicaoColuna = (int) (Math.random() * tamanho);
                } while (tabuleiro[posicaoLinha][posicaoColuna].equals("N"));
                tabuleiro[posicaoLinha][posicaoColuna] = "N";
            } else {
                tabuleiro[posicaoLinha][posicaoColuna] = "N";
            }
        }
    }

    public void realizarJogada(Jogador inimigo, int validaTamanhoTabuleiro) {
        String posicao;
        int posicaoLinha, posicaoColuna;

        do {
            System.out.println("\u001B[36m" + "Sua vez! Informe a casa do ataque no formato Letra e Número (Exemplo: B3)" + "\u001B[0m");
            posicao = input.nextLine();

            //colocar a condição se a posição for vazia, setar uma posição de valor invalido em posicaoLinha e posicaoColuna
            if(posicao.equals("")){
                posicaoLinha = 10; //10 para forçar um valor invalido e retornar ao laço.
                posicaoColuna = 10;
            }else{
                posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
                posicaoColuna = Utilidade.converterPosicaoColunaParaInt(posicao.substring(1));
            }

                if (Utilidade.validarEntradaSemMensagem(posicaoLinha, posicaoColuna, tamanhoTabuleiroJogo)) {

                        if (tabuleiro[posicaoLinha][posicaoColuna].equals("*") ||
                                tabuleiro[posicaoLinha][posicaoColuna].equals("-") ||
                                tabuleiro[posicaoLinha][posicaoColuna].equals("X") ||
                                tabuleiro[posicaoLinha][posicaoColuna].equals("n")) {

                            System.out.println("Você já atacou nessa casa! Digite uma nova casa");
                            posicao = input.nextLine();


                                posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
                                posicaoColuna = Utilidade.converterPosicaoColunaParaInt(posicao.substring(1));
                            }

                }
        } while (!Utilidade.validarEntrada(posicaoLinha, posicaoColuna, tamanhoTabuleiroJogo));

        String casaInimigo = inimigo.getCasaTabuleiro(posicaoLinha, posicaoColuna);
        String casaJogador = getCasaTabuleiro(posicaoLinha, posicaoColuna);

        if (casaInimigo.equals("N")) {
            System.out.println("Você acertou um navio inimigo!");
            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, " ");
            placar = placar + 1;

            if (casaJogador.equals("N")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
            } else if (casaJogador.equals(" ")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
            }
        }

        if (casaInimigo.equals("n")) {
            System.out.println("Você acertou um navio inimigo!");
            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, "-");
            placar = placar + 1;

            if (casaJogador.equals("N")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
            } else if (casaJogador.equals(" ")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
            }
        }

        if (casaInimigo.equals("X")) {
            System.out.println("Você acertou um navio inimigo!");
            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
            placar = placar + 1;

            if (casaJogador.equals("N")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
            } else if (casaJogador.equals(" ")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
            }
        }

        if (casaInimigo.equals(" ") ||
                casaInimigo.equals("-") ||
                casaInimigo.equals("*")) {
            System.out.println("Você errou o tiro!");

            if (casaJogador.equals(" ")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "-");
            } else if (casaJogador.equals("N")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "n");
            }
        }
    }

    public void realizarJogadaRandomica(Jogador inimigo) {
        int posicaoLinha, posicaoColuna;

        posicaoLinha = (int) (Math.random() * tamanhoTabuleiroJogo);
        posicaoColuna = (int) (Math.random() * tamanhoTabuleiroJogo);

        while (tabuleiro[posicaoLinha][posicaoColuna].equals("*") ||
                tabuleiro[posicaoLinha][posicaoColuna].equals("-") ||
                tabuleiro[posicaoLinha][posicaoColuna].equals("X") ||
                tabuleiro[posicaoLinha][posicaoColuna].equals("n")) {
            posicaoLinha = (int) (Math.random() * tamanhoTabuleiroJogo);
            posicaoColuna = (int) (Math.random() * tamanhoTabuleiroJogo);
        }

        System.out.println("Jogada do computador: " + barraLateralTabuleiro[posicaoLinha] + posicaoColuna);

        String casaInimigo = inimigo.getCasaTabuleiro(posicaoLinha, posicaoColuna);
        String casaJogador = getCasaTabuleiro(posicaoLinha, posicaoColuna);

        if (casaInimigo.equals("N")) {
            System.out.println("O Computador acertou seu navio!");
            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, " ");
            placar = placar + 1;

            if (casaJogador.equals("N")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
            } else if (casaJogador.equals(" ")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
            }
        }

        if (casaInimigo.equals("n")) {
            System.out.println("O Computador acertou seu navio!");
            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, "-");
            placar = placar + 1;

            if (casaJogador.equals("N")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
            } else if (casaJogador.equals(" ")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
            }
        }

        if (casaInimigo.equals("X")) {
            System.out.println("O Computador acertou seu navio!");
            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
            placar = placar + 1;

            if (casaJogador.equals("N")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
            } else if (casaJogador.equals(" ")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
            }
        }

        if (casaInimigo.equals(" ") ||
                casaInimigo.equals("-") ||
                casaInimigo.equals("*")) {
            System.out.println("O computador errou o tiro!");

            if (casaJogador.equals(" ")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "-");
            } else if (casaJogador.equals("N")) {
                setCasaTabuleiro(posicaoLinha, posicaoColuna, "n");
            }
        }
    }
}

