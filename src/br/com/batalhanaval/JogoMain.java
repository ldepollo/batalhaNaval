package br.com.batalhanaval;

import br.com.batalhanaval.classes.Jogador;

import java.util.Scanner;

public class JogoMain {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int tamanhoTabuleiro = 0;
    int opcaoDePreenchimentoDoTabuleiro = 0;

    System.out.println("Iniciando o jogo de Batalha Naval!");

    while (tamanhoTabuleiro<5 || tamanhoTabuleiro>10) {
      System.out.println("Preencha o nível de dificuldade de 5 a 10:");
      System.out.println("(Dificuldade representa tamanho do tabuleiro e # de submarinos)");
      tamanhoTabuleiro = Integer.parseInt(input.nextLine());
    }

    while (opcaoDePreenchimentoDoTabuleiro != 1 && opcaoDePreenchimentoDoTabuleiro != 2) {
      System.out.println("Preenchendo o tabuleiro do jogador:");
      System.out.println("Digite 1 para preencher manualmente ou 2 para preencher automaticamente.");
      opcaoDePreenchimentoDoTabuleiro = Integer.parseInt(input.nextLine());
    }

    Jogador jogador = new Jogador(opcaoDePreenchimentoDoTabuleiro, tamanhoTabuleiro);
    Jogador computador = new Jogador(2,10);

    jogador.imprimirTabuleiro();

  }
}