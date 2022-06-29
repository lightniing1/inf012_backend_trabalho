package br.ifba.pweb.dto;

import br.ifba.pweb.model.Status;

public class ChamadoDto {

    private Long id;
    private String assunto;
    private Status status;
    private String complemento;
    private Long cliente_id;
    private String nome_cliente;
    private String data_cadastro;

    public String getCadastro() {
        return data_cadastro;
    }

    public void setCadastro(String cadastro) {
        this.data_cadastro = cadastro;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public ChamadoDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }
}
