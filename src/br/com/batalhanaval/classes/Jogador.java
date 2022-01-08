package br.com.batalhanaval.classes;

import java.util.Scanner;

public class Jogador {
    String[][] tabuleiro;
    String[] barraTopoTabuleiro = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] barraLateralTabuleiro = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    Scanner input = new Scanner(System.in);
    private final int tamanhoTabuleiroJogo;
    private int placar = 0;
    String regex;

    public Jogador(int opcaoDePreenchimento, int tamanhoTabuleiro) {
        this.tamanhoTabuleiroJogo = tamanhoTabuleiro;
        this.tabuleiro = new String[this.tamanhoTabuleiroJogo][this.tamanhoTabuleiroJogo];
        inicializarTabuleiro(this.tamanhoTabuleiroJogo);

        this.regex = "(?i)^[A-" + this.barraLateralTabuleiro[(this.tamanhoTabuleiroJogo - 1)] + "][0-" + (this.tamanhoTabuleiroJogo - 1) + "]$";

        if (opcaoDePreenchimento == 1) {
            preencherTabuleiroNaMao();
        } else {
            preencherTabuleiroRandomicamente();
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

    private void preencherTabuleiroNaMao() {
        String posicao;
        int posicaoLinha;
        int posicaoColuna;

        imprimirTabuleiro();

        System.out.println("\u001B[34m" + "Informe as posições no formato Letra e Número (Exemplo: B3)" + "\u001B[0m");


        for (int i = 0; i < tamanhoTabuleiroJogo; i++) {
            do {
                do {

                    System.out.println("\u001B[36m" + "Digite a " + (i + 1) + "ª posição:" + "\u001B[0m");
                    posicao = input.nextLine();

                } while (Utilidade.validarInputs(posicao, regex));

                posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
                posicaoColuna = Integer.parseInt(posicao.substring(1));

            } while (!Utilidade.validarEntradaRepetida(tabuleiro[posicaoLinha][posicaoColuna]));

            tabuleiro[posicaoLinha][posicaoColuna] = "N";
        }
    }

    private void preencherTabuleiroRandomicamente() {
        int posicaoLinha;
        int posicaoColuna;

        for (int i = 0; i < tamanhoTabuleiroJogo; i++) {
            posicaoLinha = (int) (Math.random() * tamanhoTabuleiroJogo);
            posicaoColuna = (int) (Math.random() * tamanhoTabuleiroJogo);

            if (tabuleiro[posicaoLinha][posicaoColuna].equals("N")) {
                do {
                    posicaoLinha = (int) (Math.random() * tamanhoTabuleiroJogo);
                    posicaoColuna = (int) (Math.random() * tamanhoTabuleiroJogo);
                } while (tabuleiro[posicaoLinha][posicaoColuna].equals("N"));
                tabuleiro[posicaoLinha][posicaoColuna] = "N";
            } else {
                tabuleiro[posicaoLinha][posicaoColuna] = "N";
            }
        }
    }

    public void realizarJogada(Jogador inimigo) {
        String posicao;
        int posicaoLinha;
        int posicaoColuna;

        do {
            do {

                System.out.println("\u001B[36m" + "Sua vez! Informe a casa do ataque no formato Letra e Número (Exemplo: B3)" + "\u001B[0m");
                posicao = input.nextLine();

            } while (Utilidade.validarInputs(posicao, regex));

            posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
            posicaoColuna = Integer.parseInt(posicao.substring(1));

        } while (Utilidade.validarAtaqueRepetido(tabuleiro[posicaoLinha][posicaoColuna], "\u001B[31m" + "Você já atacou nessa casa!" + "\u001B[0m"));

        this.resultadoAtaque(inimigo, posicaoLinha, posicaoColuna,
                "Você acertou um navio inimigo!", "Você errou o tiro!");
    }

    public void realizarJogadaRandomica(Jogador inimigo) {
        int posicaoLinha;
        int posicaoColuna;

        do {
            posicaoLinha = (int) (Math.random() * tamanhoTabuleiroJogo);
            posicaoColuna = (int) (Math.random() * tamanhoTabuleiroJogo);
        }
        while (Utilidade.validarAtaqueRepetido(tabuleiro[posicaoLinha][posicaoColuna], ""));

        System.out.println("Jogada do computador: " + barraLateralTabuleiro[posicaoLinha] + posicaoColuna);

        this.resultadoAtaque(inimigo, posicaoLinha, posicaoColuna,
                "O Computador acertou seu navio!", "O computador errou o tiro!");
    }

    public void resultadoAtaque (Jogador inimigo,
                                int posicaoLinha, int posicaoColuna,
                                String mensagemAcerto, String mensagemErro) {

        String casaInimigo = inimigo.getCasaTabuleiro(posicaoLinha, posicaoColuna);
        String casaJogador = this.getCasaTabuleiro(posicaoLinha, posicaoColuna);

        switch (casaInimigo) {

            case "N":
                if (!mensagemAcerto.equals("")) {
                    System.out.println(mensagemAcerto);
                }

                inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, " ");
                this.setPlacar(this.getPlacar() + 1);

                if (casaJogador.equals("N")) {
                    this.setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
                } else if (casaJogador.equals(" ")) {
                    this.setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
                }
                break;

            case "n":
                if (!mensagemAcerto.equals("")) {
                    System.out.println(mensagemAcerto);
                }

                inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, "-");
                this.setPlacar(this.getPlacar() + 1);

                if (casaJogador.equals("N")) {
                    this.setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
                } else if (casaJogador.equals(" ")) {
                    this.setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
                }
                break;

            case "X":
                if (!mensagemAcerto.equals("")) {
                    System.out.println(mensagemAcerto);
                }

                inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
                this.setPlacar(this.getPlacar() + 1);

                if (casaJogador.equals("N")) {
                    this.setCasaTabuleiro(posicaoLinha, posicaoColuna, "X");
                } else if (casaJogador.equals(" ")) {
                    this.setCasaTabuleiro(posicaoLinha, posicaoColuna, "*");
                }
                break;

            case " ":
            case "-":
            case "*":
                if (!mensagemAcerto.equals("")) {
                    System.out.println(mensagemErro);
                }

                if (casaJogador.equals(" ")) {
                    this.setCasaTabuleiro(posicaoLinha, posicaoColuna, "-");
                } else if (casaJogador.equals("N")) {
                    this.setCasaTabuleiro(posicaoLinha, posicaoColuna, "n");
                }
                break;

            default:
                break;
        }
    }
}

