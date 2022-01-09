package br.com.batalhanaval.classes;

public enum Cor {
    PRETO("\u001B[30m"),
    VERMELHO("\u001B[31m"),
    VERDE("\u001B[32m"),
    AMARELO("\u001B[33m"),
    AZUL("\u001B[34m"),
    ROXO("\u001B[35m"),
    CYAN("\u001B[36m"),
    BRANCO("\u001B[37m"),
    FUNDO_PRETO("\u001B[40m"),
    FUNDO_VERMELHO("\u001B[41m"),
    FUNDO_VERDE("\u001B[42m"),
    FUNDO_AMARELO("\u001B[43m"),
    FUNDO_AZUL("\u001B[44m"),
    FUNDO_ROXO("\u001B[45m"),
    FUNDO_CYAN("\u001B[46m"),
    FUNDO_BRANCO("\u001B[47m"),
    RESET("\u001B[0m");

    private final String descricao;

    Cor(String descricao) {
        this.descricao = descricao;
    }

    public String get() {
        return descricao;
    }
}

/*

BLACK	\u001B[30m
BLACK_BACKGROUND	\u001B[40m
RED	\u001B[31m
RED_BACKGROUND	\u001B[41m
GREEN	\u001B[32m
GREEN_BACKGROUND	\u001B[42m
YELLOW	\u001B[33m
YELLOW_BACKGROUND	\u001B[43m
BLUE	\u001B[34m
BLUE_BACKGROUND	\u001B[44m
PURPLE	\u001B[35m
PURPLE_BACKGROUND	\u001B[45m
CYAN	\u001B[36m
CYAN_BACKGROUND	\u001B[46m
WHITE	\u001B[37m
WHITE_BACKGROUND	\u001B[47m

RESET    \u001B[0m

*/