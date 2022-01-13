package br.com.batalhanaval;

import br.com.batalhanaval.classes.Cor;
import br.com.batalhanaval.classes.Partida;
import br.com.batalhanaval.classes.Tutorial;

import java.sql.SQLOutput;
import java.util.Scanner;

public class JogoMain {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String opcaoTutorial;
        String opcaoResetarJogo;

        System.out.println("");
        System.out.println(Cor.AZUL.get() + "~".repeat(70) + Cor.RESET.get());
        System.out.println(Cor.AZUL.get() + " ".repeat(20) + "BATALHA NAVAL LET'S CODE!" + Cor.RESET.get());
        System.out.println(Cor.AZUL.get() + " ".repeat(8) + "Autores: Lucas Depollo, Lucas Luzini e Rodrigo Lucchesi" + Cor.RESET.get());
        System.out.println(Cor.AZUL.get() + "~".repeat(70) + Cor.RESET.get());
        System.out.println("");
        System.out.println(Cor.AZUL.get() + "Bem-vindo ao melhor jogo de batalha naval escrito em Java!" + Cor.RESET.get());

        System.out.println("Caso queira ver o Tutorial para aprender a jogar, digite \"S\". Digite qualquer outra coisa para iniciar o jogo.");
        opcaoTutorial = input.nextLine();
        if (opcaoTutorial.equals("S") || opcaoTutorial.equals("s")) {
            new Tutorial(input);
        }

        new Partida(input);

        while(true) {
            System.out.println("Deseja jogar novamente? [S/N]");
            opcaoResetarJogo = input.nextLine();

            if (opcaoResetarJogo.equals("S") || opcaoResetarJogo.equals("s")) {
                new Partida(input);
            } else if (opcaoResetarJogo.equals("N") || opcaoResetarJogo.equals("n")) {
                break;
            } else {
                System.out.println(Cor.VERMELHO.get() + "Você digitou algo errado!" + Cor.RESET.get());
            }
        }

        input.close();
    }
}