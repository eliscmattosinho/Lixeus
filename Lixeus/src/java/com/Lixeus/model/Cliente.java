/*
 * Author: Elis Mattosinho
 * Descrição: Classe modelo que representa a entidade Cliente.
 */

package com.Lixeus.model;

import java.time.LocalDate;

public class Cliente {
    private int id_cliente;
    private String nome;
    private String email;
    private String cpf;
    private String tel;
    private LocalDate data_nasc;
    private String senha;
    private Boolean ativo;

    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente=" + id_cliente +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", tel='" + tel + '\'' +
                ", data_nasc=" + data_nasc +
                ", senha='" + senha + '\'' +
                ", ativo='" + ativo + '\'' +
                '}';
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
