package com.fatec.sigvs.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(
    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas 11 números.")
    String cpf, 

    @NotBlank(message = "O nome é obrigatório.")
    String nome, 

    @NotBlank(message = "O CEP é obrigatório.")
    String cep, 

    String endereco, 
    String bairro, 
    String cidade,
    String complemento, 

    @Email(message = "O e-mail informado é inválido.")
    String email
) {
    // Records são imutáveis e já possuem construtor, equals, hashCode e toString por padrão!
}
