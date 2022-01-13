package br.com.batalhanaval.classes;

import java.util.Scanner;

public class Partida {
    Scanner input;
    int tamanhoTabuleiro;
    int opcaoDePreenchimentoDoTabuleiro;

    public Partida(Scanner input) {
        this.input = input;
        this.tamanhoTabuleiro = this.definirTamanhoTabuleiro();
        this.opcaoDePreenchimentoDoTabuleiro = this.definirOpcaoPreenchimentoTabuleiro();

        Jogador jogador = new Jogador(opcaoDePreenchimentoDoTabuleiro, tamanhoTabuleiro,"JOGADOR", input);
        Jogador computador = new Jogador(2, tamanhoTabuleiro,"COMPUTADOR", input);

        this.iniciarJogo(jogador, computador);
    }

    public int definirTamanhoTabuleiro() {
        String dadosInput;
        int tamanho;

        System.out.println(Cor.CYAN.get() + "Para começar, preencha o nível de dificuldade de 5 a 10:" + Cor.RESET.get());
        System.out.println("(Dificuldade representa tamanho do tabuleiro e # de submarinos)");

        dadosInput = input.nextLine();

        while(!dadosInput.matches("^[5-9]$") && !dadosInput.equals("10")) {
            System.out.println(Cor.VERMELHO.get() + "(Valor digitado inválido.)" + Cor.RESET.get());
            dadosInput = input.nextLine();
        }

        tamanho = Integer.parseInt(dadosInput);
        return tamanho;
    }

    public int definirOpcaoPreenchimentoTabuleiro() {
        String dadosInput;
        int opcaoPreenchimento = 0;

        System.out.println("");
        System.out.println(Cor.AZUL.get() + "Preenchendo o tabuleiro do jogador:" + Cor.RESET.get());
        System.out.println(Cor.CYAN.get() + "Digite 1 para preencher manualmente ou 2 para preencher automaticamente." + Cor.RESET.get());

        dadosInput = input.nextLine();

        while(!dadosInput.matches("^[1-2]$")) {
            System.out.println(Cor.VERMELHO.get() + "(Valor digitado inválido.)" + Cor.RESET.get());
            dadosInput = input.nextLine();
        }

        opcaoPreenchimento = Integer.parseInt(dadosInput);
        return opcaoPreenchimento;
    }

    public void iniciarJogo(Jogador jogador, Jogador computador) {
        while (true) {
            jogador.imprimirTabuleiro();
            System.out.println(Cor.FUNDO_AMARELO.get()+Cor.PRETO.get()+ "Placar: Jogador = " + jogador.getPlacar() + " | Computador = " + computador.getPlacar() + Cor.RESET.get());
            jogador.realizarJogada(computador);
            if (jogador.getPlacar() == tamanhoTabuleiro) {
                jogador.imprimirTabuleiro();
                computador.imprimirTabuleiro();
                System.out.println(Cor.FUNDO_VERDE.get()+Cor.PRETO.get() + "O Jogador venceu! Parabéns!" + Cor.RESET.get());
                break;
            }

            computador.realizarJogadaRandomica(jogador);
            if (computador.getPlacar() == tamanhoTabuleiro) {
                jogador.imprimirTabuleiro();
                computador.imprimirTabuleiro();
                System.out.println(Cor.FUNDO_AMARELO.get()+Cor.PRETO.get() + "O Computador venceu! Que pena!" + Cor.RESET.get());
                break;
            }
        }
    }
}
