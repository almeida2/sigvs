package com.fatec.sigvs.model;

public class ClienteBuilder {
    private String cpf;
    private String nome;
    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String complemento;
    private String email;

    public ClienteBuilder() {
        this.cpf = "34525227095";
        this.nome = "Joao";
        this.cep = "01001000";
        this.endereco = "Praça da Sé";
        this.bairro = "Sé";
        this.cidade = "São Paulo";
        this.complemento = "complemento";
        this.email = "joao@gmail.com";
    }

    public ClienteBuilder comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder comCep(String cep) {
        this.cep = cep;
        return this;
    }

    public ClienteBuilder comEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public ClienteBuilder comBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public ClienteBuilder comCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public ClienteBuilder comComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public ClienteBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public ClienteBuilder comDataCadastro(String dataCadastro) {
        return this;
    }

    public Cliente criarClienteValido() {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setCep(cep);
        cliente.setEndereco(endereco);
        cliente.setBairro(bairro);
        cliente.setCidade(cidade);
        cliente.setComplemento(complemento);
        cliente.setEmail(email);
        cliente.setDataCadastro();
        return cliente;
    }
}