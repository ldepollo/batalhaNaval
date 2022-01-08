package br.com.batalhanaval.classes;

public final class Utilidade {

    private Utilidade() {}

    public static int converterPosicaoLinhaParaInt(String posicaoLinha) {
        switch (posicaoLinha) {
            case "A":
            case "a":
                return 0;
            case "B":
            case "b":
                return 1;
            case "C":
            case "c":
                return 2;
            case "D":
            case "d":
                return 3;
            case "E":
            case "e":
                return 4;
            case "F":
            case "f":
                return 5;
            case "G":
            case "g":
                return 6;
            case "H":
            case "h":
                return 7;
            case "I":
            case "i":
                return 8;
            case "J":
            case "j":
                return 9;
            default:
                return 10;
        }
    }

    public static boolean validarInputs(String posicao, String regex){
        if (posicao.matches(regex)) {
            return false;
        } else {
            System.out.println(Cor.VERMELHO.get() + "Valor inválido! Favor entrar no formato Letra e Número (Ex: B3)" + Cor.RESET.get());
            return true;
        }
    }

    public static boolean validarEntradaRepetida(String casaTabuleiro){
        if (casaTabuleiro.equals(PosicoesTabuleiro.NAVIO_POSICIONADO.getDescricao())) {
            System.out.println(Cor.VERMELHO.get() + "Essa posição já está preenchida!" + Cor.RESET.get());
            return false;
        } else {
            return true;
        }
    }

    public static boolean validarAtaqueRepetido(String casaTabuleiro, String mensagem){
        if (casaTabuleiro.equals(PosicoesTabuleiro.TIRO_CERTO.getDescricao()) ||
                casaTabuleiro.equals(PosicoesTabuleiro.TIRO_AGUA.getDescricao()) ||
                casaTabuleiro.equals(PosicoesTabuleiro.NAVIO_POSICIONADO_TIRO_CERTO.getDescricao()) ||
                casaTabuleiro.equals(PosicoesTabuleiro.NAVIO_POSICIONADO_TIRO_AGUA.getDescricao())) {

            if (!mensagem.equals("")) {
                System.out.println(mensagem);
            }

            return true;
        } else {
            return false;
        }
    }
}