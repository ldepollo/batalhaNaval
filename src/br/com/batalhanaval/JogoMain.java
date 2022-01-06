package br.com.batalhanaval;

import br.com.batalhanaval.classes.Jogador;

import java.util.Scanner;

public class JogoMain {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int tamanhoTabuleiro = 0;
    int opcaoDePreenchimentoDoTabuleiro = 0;
    boolean jogoEmAndamento;

    System.out.println("Iniciando o jogo de Batalha Naval!");

    while (tamanhoTabuleiro<5 || tamanhoTabuleiro>10) {
      System.out.println("Preencha o nível de dificuldade de 5 a 10:");
      System.out.println("(Dificuldade representa tamanho do tabuleiro e # de submarinos)");

      if (input.hasNextInt()) {
        tamanhoTabuleiro = Integer.parseInt(input.nextLine());

        if(tamanhoTabuleiro<5 || tamanhoTabuleiro>10) {
            System.out.println("\u001B[31m" + "(Valor digitado inválido.)" + "\u001B[0m");
            input = new Scanner(System.in);
        }

      }else{
        System.out.println("\u001B[31m" + "(Valor digitado inválido.)" + "\u001B[0m");
        input = new Scanner(System.in);
      }
    }

    while (opcaoDePreenchimentoDoTabuleiro != 1 && opcaoDePreenchimentoDoTabuleiro != 2) {
      System.out.println("Preenchendo o tabuleiro do jogador:");
      System.out.println("Digite 1 para preencher manualmente ou 2 para preencher automaticamente.");
      //opcaoDePreenchimentoDoTabuleiro = Integer.parseInt(input.nextLine());


      if (input.hasNextInt()) {
        opcaoDePreenchimentoDoTabuleiro = Integer.parseInt(input.nextLine());

        if(opcaoDePreenchimentoDoTabuleiro != 1 && opcaoDePreenchimentoDoTabuleiro != 2) {
          System.out.println("\u001B[31m" + "(Valor digitado inválido.)" + "\u001B[0m");
          input = new Scanner(System.in);
        }

      }else{
        System.out.println("\u001B[31m" + "(Valor digitado inválido.)" + "\u001B[0m");
        input = new Scanner(System.in);
      }
    }


    Jogador jogador = new Jogador(opcaoDePreenchimentoDoTabuleiro, tamanhoTabuleiro);
    Jogador computador = new Jogador(2,tamanhoTabuleiro);


    while(true) {
      jogador.imprimirTabuleiro();
      System.out.println("Placar: Jogador = " + jogador.getPlacar() + " | Computador = " + computador.getPlacar());
      jogador.realizarJogada(computador);
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