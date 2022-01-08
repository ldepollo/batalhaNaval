package br.com.batalhanaval.classes;

import java.util.Scanner;

public class Jogador {
    Scanner input;
    String[][] tabuleiro;
    String[] barraTopoTabuleiro = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] barraLateralTabuleiro = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private final int tamanhoTabuleiroJogo;
    private int placar = 0;
    String nomeJogador;
    String regex;

    public Jogador(int opcaoDePreenchimento, int tamanhoTabuleiro, String nome, Scanner input) {
        this.input = input;
        this.tamanhoTabuleiroJogo = tamanhoTabuleiro;
        this.tabuleiro = new String[this.tamanhoTabuleiroJogo][this.tamanhoTabuleiroJogo];
        inicializarTabuleiro(this.tamanhoTabuleiroJogo);
        nomeJogador = nome;

        this.regex = "(?i)^[A-" + this.barraLateralTabuleiro[(this.tamanhoTabuleiroJogo - 1)] + "][0-" + (this.tamanhoTabuleiroJogo - 1) + "]$";

        if (opcaoDePreenchimento == 1) {
            preencherTabuleiroNaMao();
        } else {
            preencherTabuleiroRandomicamente();
        }
    }

    public void imprimirTabuleiro() {
        System.out.println("-".repeat(4 * tamanhoTabuleiroJogo + 5));
        System.out.println(" ".repeat(2 * tamanhoTabuleiroJogo - nomeJogador.length()/2+3) + nomeJogador + " ".repeat(2 * tamanhoTabuleiroJogo - nomeJogador.length()/2+3));
        System.out.println("-".repeat(4 * tamanhoTabuleiroJogo + 5));
        System.out.printf("|   |");
        for (int i = 0; i < tamanhoTabuleiroJogo; i++) {
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

    public int getPlacar() {
        return placar;
    }

    public void setPlacar(int placar) {
        this.placar = placar;
    }

    private void inicializarTabuleiro(int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = " ";
            }
        }
    }

    private void preencherTabuleiroNaMao() {
        String posicao;
        int posicaoLinha;
        int posicaoColuna;

        imprimirTabuleiro();

        System.out.println(Cor.AZUL.get() + "Informe as posições no formato Letra e Número (Exemplo: B3)" + Cor.RESET.get());


        for (int i = 0; i < tamanhoTabuleiroJogo; i++) {
            do {
                do {

                    System.out.println(Cor.CYAN.get() + "Digite a " + (i + 1) + "ª posição:" + Cor.RESET.get());
                    posicao = input.nextLine();

                } while (Utilidade.validarInputs(posicao, regex));

                posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
                posicaoColuna = Integer.parseInt(posicao.substring(1));

            } while (!Utilidade.validarEntradaRepetida(tabuleiro[posicaoLinha][posicaoColuna]));

            tabuleiro[posicaoLinha][posicaoColuna] = PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao();
        }
    }

    private void preencherTabuleiroRandomicamente() {
        int posicaoLinha;
        int posicaoColuna;

        for (int i = 0; i < tamanhoTabuleiroJogo; i++) {
            posicaoLinha = (int) (Math.random() * tamanhoTabuleiroJogo);
            posicaoColuna = (int) (Math.random() * tamanhoTabuleiroJogo);

            if (tabuleiro[posicaoLinha][posicaoColuna].equals(PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao())) {
                do {
                    posicaoLinha = (int) (Math.random() * tamanhoTabuleiroJogo);
                    posicaoColuna = (int) (Math.random() * tamanhoTabuleiroJogo);
                } while (tabuleiro[posicaoLinha][posicaoColuna].equals(PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao()));
                tabuleiro[posicaoLinha][posicaoColuna] = PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao();
            } else {
                tabuleiro[posicaoLinha][posicaoColuna] = PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao();
            }
        }
    }

    public void realizarJogada(Jogador inimigo) {
        String posicao;
        int posicaoLinha;
        int posicaoColuna;

        do {
            do {

                System.out.println(Cor.CYAN.get() + "Sua vez! Informe a casa do ataque no formato Letra e Número (Exemplo: B3)" + Cor.RESET.get());
                posicao = input.nextLine();

            } while (Utilidade.validarInputs(posicao, regex));

            posicaoLinha = Utilidade.converterPosicaoLinhaParaInt(posicao.substring(0, 1));
            posicaoColuna = Integer.parseInt(posicao.substring(1));

        } while (Utilidade.validarAtaqueRepetido(tabuleiro[posicaoLinha][posicaoColuna], Cor.VERMELHO.get() + "Você já atacou nessa casa!" + Cor.RESET.get()));

        this.resultadoAtaque(inimigo, posicaoLinha, posicaoColuna,
                "Você acertou um navio inimigo!", "Você errou o tiro!");
    }

    public void realizarJogadaRandomica(Jogador inimigo) {
        int posicaoLinha;
        int posicaoColuna;

        do {
            posicaoLinha = (int) (Math.random() * tamanhoTabuleiroJogo);
            posicaoColuna = (int) (Math.random() * tamanhoTabuleiroJogo);
        }
        while (Utilidade.validarAtaqueRepetido(tabuleiro[posicaoLinha][posicaoColuna], ""));

        System.out.println("Jogada do computador: " + barraLateralTabuleiro[posicaoLinha] + posicaoColuna);

        this.resultadoAtaque(inimigo, posicaoLinha, posicaoColuna,
                "O Computador acertou seu navio!", "O computador errou o tiro!");
    }

    public void resultadoAtaque(Jogador inimigo,
                                int posicaoLinha, int posicaoColuna,
                                String mensagemAcerto, String mensagemErro) {

        String casaInimigo = inimigo.getCasaTabuleiro(posicaoLinha, posicaoColuna);
        String casaJogador = this.getCasaTabuleiro(posicaoLinha, posicaoColuna);

        if (casaInimigo.equals(PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao())) {
            if (!mensagemAcerto.equals("")) {
                System.out.println(mensagemAcerto);
            }

            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.VAZIO.getDescricao());
            this.setPlacar(this.getPlacar() + 1);

            if (casaJogador.equals(PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao())) {
                this.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.NAVIO_POSICIONADO_TIRO_CERTO.getDescricao());
            } else if (casaJogador.equals(PosicoesTabuleiro.VAZIO.getDescricao())) {
                this.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.TIRO_CERTO.getDescricao());
            }
        }

        if (casaInimigo.equals(PosicoesTabuleiro.NAVIO_POSICIONADO_TIRO_AGUA.getDescricao())) {
            if (!mensagemAcerto.equals("")) {
                System.out.println(mensagemAcerto);
            }

            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.TIRO_AGUA.getDescricao());
            this.setPlacar(this.getPlacar() + 1);

            if (casaJogador.equals(PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao())) {
                this.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.NAVIO_POSICIONADO_TIRO_CERTO.getDescricao());
            } else if (casaJogador.equals(PosicoesTabuleiro.VAZIO.getDescricao())) {
                this.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.TIRO_CERTO.getDescricao());
            }
        }

        if (casaInimigo.equals(PosicoesTabuleiro.NAVIO_POSICIONADO_TIRO_CERTO.getDescricao())) {
            if (!mensagemAcerto.equals("")) {
                System.out.println(mensagemAcerto);
            }

            inimigo.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.TIRO_CERTO.getDescricao());
            this.setPlacar(this.getPlacar() + 1);

            if (casaJogador.equals(PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao())) {
                this.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.NAVIO_POSICIONADO_TIRO_CERTO.getDescricao());
            } else if (casaJogador.equals(PosicoesTabuleiro.VAZIO.getDescricao())) {
                this.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.TIRO_CERTO.getDescricao());
            }
        }

        if (casaInimigo.equals(PosicoesTabuleiro.TIRO_AGUA.getDescricao()) ||
                casaInimigo.equals(PosicoesTabuleiro.TIRO_CERTO.getDescricao()) ||
                casaInimigo.equals(PosicoesTabuleiro.VAZIO.getDescricao())) {

            if (!mensagemAcerto.equals("")) {
                System.out.println(mensagemErro);
            }

            if (casaJogador.equals(PosicoesTabuleiro.VAZIO.getDescricao())) {
                this.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.TIRO_AGUA.getDescricao());
            } else if (casaJogador.equals(PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao())) {
                this.setCasaTabuleiro(posicaoLinha, posicaoColuna, PosicoesTabuleiro.NAVIO_POSICIONADO_TIRO_AGUA.getDescricao());
            }
        }
    }
}

