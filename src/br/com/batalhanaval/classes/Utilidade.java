package br.com.batalhanaval.classes;

public final class Utilidade {

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

    public static int converterPosicaoColunaParaInt(String posicaoColuna) {
        switch (posicaoColuna) {
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            default:
                return 10;
        }
    }

    public static boolean validarEntrada(int posicaoLinha, int posicaoColuna, int tamanhoTabuleiroJogo) {
        if (posicaoLinha == 10 || posicaoColuna == 10 || posicaoLinha >= tamanhoTabuleiroJogo || posicaoColuna >= tamanhoTabuleiroJogo) {
            System.out.println("\u001B[31m" + "Posição inválida! Favor entrar no formato Letra e Número (Ex: B3)" + "\u001B[0m");
            return false;
        } else {
            return true;
        }
    }

    public static boolean validarEntradaSemMensagem(int posicaoLinha, int posicaoColuna, int tamanhoTabuleiroJogo) {
        if (posicaoLinha == 10 || posicaoColuna == 10 || posicaoLinha >= tamanhoTabuleiroJogo || posicaoColuna >= tamanhoTabuleiroJogo) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validarInputs(String posicao){
        if (posicao.equals("")) {
            System.out.println("\u001B[31m" + "Valor inválido! Favor entrar no formato Letra e Número (Ex: B3)" + "\u001B[0m");
            return false;
        } else {
            return true;
        }
    }

    
}
