/*

BLACK	\u001B[30m
BLACK_BACKGROUND	\u001B[40m
RED	\u001B[31m
RED_BACKGROUND	\u001B[41m
GREEN	\u001B[32m
GREEN_BACKGROUND	\u001B[42m
YELLOW	\u001B[33m
YELLOW_BACKGROUND	\u001B[43m
BLUE	\u001B[34m
BLUE_BACKGROUND	\u001B[44m
PURPLE	\u001B[35m
PURPLE_BACKGROUND	\u001B[45m
CYAN	\u001B[36m
CYAN_BACKGROUND	\u001B[46m
WHITE	\u001B[37m
WHITE_BACKGROUND	\u001B[47m

RESET    \u001B[0m

*/


package br.com.batalhanaval;

import br.com.batalhanaval.classes.Jogador;

import java.util.Scanner;

public class JogoMain {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int tamanhoTabuleiro = 0;
        int opcaoDePreenchimentoDoTabuleiro = 0;
        String[] barraLateralTabuleiro = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        boolean jogoEmAndamento;

        System.out.println("\u001B[34m" + "Iniciando o jogo de Batalha Naval!" + "\u001B[0m");

        while (tamanhoTabuleiro < 5 || tamanhoTabuleiro > 10) {
            System.out.println("\u001B[36m" + "Preencha o nível de dificuldade de 5 a 10:" + "\u001B[0m");
            System.out.println("(Dificuldade representa tamanho do tabuleiro e # de submarinos)");

            String dadosInput = input.nextLine();

            if (dadosInput.matches("^[5-9]$") || dadosInput.equals("10")) {
                tamanhoTabuleiro = Integer.parseInt(dadosInput);
                if (tamanhoTabuleiro < 5 || tamanhoTabuleiro > 10) {
                    System.out.println("\u001B[31m" + "(Valor digitado inválido.)" + "\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31m" + "(Valor digitado inválido.)" + "\u001B[0m");
            }
        }

        while (opcaoDePreenchimentoDoTabuleiro != 1 && opcaoDePreenchimentoDoTabuleiro != 2) {
            System.out.println("\u001B[34m" + "Preenchendo o tabuleiro do jogador:" + "\u001B[0m");
            System.out.println("\u001B[36m" + "Digite 1 para preencher manualmente ou 2 para preencher automaticamente." + "\u001B[0m");

            String dadosInput = input.nextLine();

            if (dadosInput.matches("^[1-2]$")) {
                opcaoDePreenchimentoDoTabuleiro = Integer.parseInt(dadosInput);
                if (opcaoDePreenchimentoDoTabuleiro != 1 && opcaoDePreenchimentoDoTabuleiro != 2) {
                    System.out.println("\u001B[31m" + "(Valor digitado inválido.)" + "\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31m" + "(Valor digitado inválido.)" + "\u001B[0m");
            }
        }


        Jogador jogador = new Jogador(opcaoDePreenchimentoDoTabuleiro, tamanhoTabuleiro,"COMPUTADOR");
        Jogador computador = new Jogador(2, tamanhoTabuleiro,"COMPUTADOR");


        while (true) {
            jogador.imprimirTabuleiro();
            System.out.println("Placar: Jogador = " + jogador.getPlacar() + " | Computador = " + computador.getPlacar());
            jogador.realizarJogada(computador, tamanhoTabuleiro);
            if (jogador.getPlacar() == tamanhoTabuleiro) {
                jogador.imprimirTabuleiro();
                computador.imprimirTabuleiro();
                System.out.println("O Jogador venceu! Parabéns!");
                break;
            }

            computador.realizarJogadaRandomica(jogador);
            if (computador.getPlacar() == tamanhoTabuleiro) {
                jogador.imprimirTabuleiro();
                computador.imprimirTabuleiro();
                System.out.println("O Computador venceu! Que pena!");
                break;
            }
        }

        input.close();
    }
}