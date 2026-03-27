package com.fatec.sigvs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.fatec.sigvs.model.Cliente;

import static org.junit.jupiter.api.Assertions.*;

public class TIReq09CadastrarCliente3Tests {
    // entidade
    private Cliente cliente;

    @ParameterizedTest(name = "Registro {index}: CPF {0}")
    @CsvFileSource(resources = "/dataset10.csv", numLinesToSkip = 1)
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
