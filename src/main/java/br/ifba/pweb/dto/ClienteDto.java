package br.ifba.pweb.dto;

public class ClienteDto {

    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String data_cadastro;
    private String UID_Usuario;

    /*
    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cnpj = cliente.getCNPJ();
        this.endereco = cliente.getEndereco();
        this.data_cadastro = cliente.getDataCadastro();
        this.UID_Usuario = cliente.getUsuario().getUID();
    }
    */

    public ClienteDto ()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataCadastro() {
        return data_cadastro;
    }

    public void setDataCadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getUID_Usuario() {
        return UID_Usuario;
    }

    public void setUID_Usuario(String UID_Usuario) {
        this.UID_Usuario = UID_Usuario;
    }
}
