package br.com.batalhanaval;

import br.com.batalhanaval.classes.Cor;
import br.com.batalhanaval.classes.Partida;

import java.util.Scanner;

public class JogoMain {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String opcaoResetarJogo;

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