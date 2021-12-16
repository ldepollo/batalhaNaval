package br.com.batalhanaval.classes;

import java.util.Scanner;

public class Jogador {
    String[][] tabuleiro = new String[10][10];
    String[] barraLateralTabuleiro = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public Jogador(int opcaoDePreenchimento) {
        inicializarTabuleiro();

        if (opcaoDePreenchimento == 1) {
            preencherTabuleiroNaMao();
        } else {
            preencherTabuleiroRandomicamente();
        }
    }

    public void imprimirTabuleiro() {
        System.out.println("---------------------------------------------");
        System.out.println("                   JOGADOR                   ");
        System.out.println("---------------------------------------------");
        System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");

        for (int i = 0; i < 10; i++) {
            System.out.printf("| %s |", barraLateralTabuleiro[i]);
            for (int j = 0; j < 10; j++) {
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

    private void inicializarTabuleiro() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = " ";
            }
        }
    }

    private void preencherTabuleiroNaMao() {
        Scanner input = new Scanner(System.in);
        String posicao;
        int posicaoLinha, posicaoColuna;

        imprimirTabuleiro();

        System.out.println("Informe as posições no formato Letra e Número (Exemplo: B3)");

        for (int i = 0; i < 10; i++) {
            do {
                System.out.println("Digite a " + (i + 1) + "ª posição:");
                posicao = input.nextLine();

                posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
                posicaoColuna = Utilidade.converterPosicaoColunaParaInt(posicao.substring(1));

                if (Utilidade.validarEntradaSemMensagem(posicaoLinha, posicaoColuna)) {
                    if (tabuleiro[posicaoLinha][posicaoColuna].equals("N")) {
                        System.out.println("Essa posição já está preenchida!");
                        System.out.println("Digite a " + (i + 1) + "ª posição:");
                        posicao = input.nextLine();

                        posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
                        posicaoColuna = Utilidade.converterPosicaoColunaParaInt(posicao.substring(1));
                    }
                }
            } while (!Utilidade.validarEntrada(posicaoLinha, posicaoColuna));
            tabuleiro[posicaoLinha][posicaoColuna] = "N";
        }

        input.close();
    }

    private void preencherTabuleiroRandomicamente() {
        int posicaoLinha, posicaoColuna;

        for (int i = 0; i < 10; i++) {
            posicaoLinha = (int) (Math.random() * 10);
            posicaoColuna = (int) (Math.random() * 10);

            if (tabuleiro[posicaoLinha][posicaoColuna].equals("N")) {
                do {
                    posicaoLinha = (int) (Math.random() * 10);
                    posicaoColuna = (int) (Math.random() * 10);
                } while (tabuleiro[posicaoLinha][posicaoColuna].equals("N"));
                tabuleiro[posicaoLinha][posicaoColuna] = "N";
            } else {
                tabuleiro[posicaoLinha][posicaoColuna] = "N";
            }
        }
    }
}
