package com.fatec.sigvs.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Objetivo de teste - validar a integração entre a classe Cliente e a ValidaCPF
 * Segue uma abordagem de data-driven.
 */
public class TIReq09CadastrarCliente2Tests {
    // entidade
    private Cliente cliente;

    @ParameterizedTest(name = "Registro {index}: CPF {0}")
    @CsvFileSource(resources = "/dataset10.csv", numLinesToSkip = 1, emptyValue = "", nullValues = "NULL")
    public void ct_verifica_comportamento_cadastro(String cpf, String nome, String cep, String endereco, String bairro,
            String cidade, String complemento, String email, String re) {
        try {
            cliente = new Cliente();
            cliente.setCpf(cpf);
            cliente.setNome(nome);
            cliente.setCep(cep);
            cliente.setEndereco(endereco);
            cliente.setComplemento(complemento);
            cliente.setBairro(bairro);
            cliente.setCidade(cidade);
            cliente.setDataCadastro();
            cliente.setEmail(email);
            assertNotNull(cliente);
            assertEquals(re, "satisfatorio");
        } catch (Exception e) {
            assertEquals(re, e.getMessage());
        }
    }
}