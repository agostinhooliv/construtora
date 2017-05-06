package br.com.construtora.model;

import java.io.Serializable;

/**
 * Created by agostinhooliv on 04/05/17.
 */

public class Despesas implements Serializable {

    private int id;
    private String data;
    private String vencimento;
    private String tipo;
    private String valor;
    private String status;
    private String observacao;

    public Despesas() {
    }

    public Despesas(String data, String vencimento, String tipo, String valor, String status, String observacao) {
        this.data = data;
        this.vencimento = vencimento;
        this.tipo = tipo;
        this.valor = valor;
        this.status = status;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
