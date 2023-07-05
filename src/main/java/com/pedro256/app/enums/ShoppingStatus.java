package com.pedro256.app.enums;

public enum ShoppingStatus {
    INICIADO("Iniciado",0),
    EM_PROCESSO("Em Processo",1),
    FINALIZADO("Finalizado",2);

    private final String nome;
    private final int valor;


    ShoppingStatus(String nome,int valor){
        this.nome = nome;
        this.valor  = valor;
    }
    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }
}