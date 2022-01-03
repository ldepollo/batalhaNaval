package br.com.batalhanaval.classes;

import java.util.Scanner;

public class Jogador {
    private int tamanhoTabuleiroJogo;
    String[][] tabuleiro = new String[10][10];
    String[] barraTopoTabuleiro = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] barraLateralTabuleiro = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public Jogador(int opcaoDePreenchimento,int tamanhoTabuleiro) {
        tamanhoTabuleiroJogo = tamanhoTabuleiro;

        inicializarTabuleiro(tamanhoTabuleiroJogo);

        if (opcaoDePreenchimento == 1) {
            preencherTabuleiroNaMao(tamanhoTabuleiroJogo);
        } else {
            preencherTabuleiroRandomicamente(tamanhoTabuleiroJogo);
        }
    }

    public void imprimirTabuleiro() {
        System.out.println("-".repeat(4*tamanhoTabuleiroJogo+5));
        System.out.println(" ".repeat(2*tamanhoTabuleiroJogo-1)+ "JOGADOR"+ " ".repeat(2*tamanhoTabuleiroJogo-1));
        System.out.println("-".repeat(4*tamanhoTabuleiroJogo+5));
        System.out.printf("|   |");
        for (int i = 0; i < tamanhoTabuleiroJogo; i++){
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

        System.out.println("Informe as posições no formato Letra e Número (Exemplo: B3)");

        for (int i = 0; i < tamanho; i++) {
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
}
