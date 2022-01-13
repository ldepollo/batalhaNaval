package br.com.batalhanaval.classes;

import java.util.Scanner;

public class Tutorial {
    Scanner input;

    public Tutorial(Scanner input) {
        this.input = input;

        System.out.println("");
        System.out.println(Cor.AZUL.get() + "~".repeat(70) + Cor.RESET.get());
        System.out.println(Cor.AZUL.get() + " ".repeat(17) + "TUTORIAL BATALHA NAVAL LET'S CODE" + Cor.RESET.get());
        System.out.println(Cor.AZUL.get() + "~".repeat(70) + Cor.RESET.get());
        System.out.println("");

        System.out.println("Batalha naval é um jogo de tabuleiro de dois jogadores, no qual os jogadores têm de adivinhar em que quadrados estão os navios do oponente.");
        System.out.println("O jogo funcionará no formato em que você jogará contra o computador.");
        System.out.println("Inicialmente, defina o tamanho do tabuleiro. O tamanho do tabuleiro define também a quantidade de navios no jogo.");
        System.out.println("Cada navio ocupa uma casa no tabuleiro.");
        System.out.println("Em seguida, você poderá escolher se prefere preencher o tabuleiro manualmente ou automaticamente.");
        System.out.println("");

        System.out.println("Após isso o jogo se inicia. Será pedido para você informar a casa na qual deseja atacar. Insira as informações no formato Letra e Número (Exemplo: B3)");

        System.out.println("As marcações no tabuleiro podem ser os seguintes:");
        System.out.println("");

        System.out.println("- "+Cor.FUNDO_AMARELO.get()+Cor.PRETO.get()+" N "+ Cor.RESET.get()+" : Casa onde há um navio seu posicionado.");
        System.out.println("- "+Cor.FUNDO_AMARELO.get()+Cor.PRETO.get()+" n "+ Cor.RESET.get()+" : Casa onde há um navio seu posicionado e você tentou atirar, porém não acertou um navio inimigo.");
        System.out.println("- "+Cor.FUNDO_AMARELO.get()+Cor.PRETO.get()+" X "+ Cor.RESET.get()+" : Casa onde há um navio seu posicionado e você acertou um navio inimigo!");
        System.out.println("- "+Cor.FUNDO_AMARELO.get()+Cor.PRETO.get()+" - "+ Cor.RESET.get()+" : Casa onde não há um navio seu e você tentou atirar, porém não acertou um navio inimigo.");
        System.out.println("- "+Cor.FUNDO_AMARELO.get()+Cor.PRETO.get()+" * "+ Cor.RESET.get()+" : Casa onde não há um navio seu e você acertou um navio inimigo!");
        System.out.println("- "+Cor.FUNDO_AMARELO.get()+Cor.PRETO.get()+"   "+ Cor.RESET.get()+" : Casa vazia");

        System.out.println("");
        System.out.println("À medida em que os navios forem sendo atingidos, o placar é atualizado.");
        System.out.println("Quando um navio é atingido, ele é removido do tabuleiro.");
        System.out.println("O jogo termina quando o primeiro jogador atingir a pontuação máxima, que corresponde ao tamanho do tabuleiro.");
        System.out.println("");

        System.out.println("Pressione Enter para iniciar o jogo!");
        input.nextLine();
    }
}
