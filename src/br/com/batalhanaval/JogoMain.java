package br.com.batalhanaval;

import br.com.batalhanaval.classes.Jogador;

import java.util.Scanner;

public class JogoMain {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int opcaoDePreenchimentoDoTabuleiro = 0;

    System.out.println("Iniciando o jogo de Batalha Naval!");

    while (opcaoDePreenchimentoDoTabuleiro != 1 && opcaoDePreenchimentoDoTabuleiro != 2) {
      System.out.println("Preenchendo o tabuleiro do jogador:");
      System.out.println("Digite 1 para preencher manualmente ou 2 para preencher automaticamente.");
      opcaoDePreenchimentoDoTabuleiro = Integer.parseInt(input.nextLine());
    }

    Jogador jogador = new Jogador(opcaoDePreenchimentoDoTabuleiro);
    Jogador computador = new Jogador(2);

    jogador.imprimirTabuleiro();
  }
}