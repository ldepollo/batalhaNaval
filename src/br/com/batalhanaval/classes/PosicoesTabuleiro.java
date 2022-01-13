package br.com.batalhanaval.classes;

public enum PosicoesTabuleiro {

    NAVIO_POSICIONADO("N"),
    NAVIO_POSICIONADO_TIRO_AGUA("n"),
    NAVIO_POSICIONADO_TIRO_CERTO("X"),
    TIRO_AGUA("-"),
    TIRO_CERTO("*"),
    VAZIO(" ");

    private final String descricao;

    PosicoesTabuleiro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
