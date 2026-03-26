package com.fatec.sigvs;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.fatec.sigvs.model.Cliente;

/**
 * Objetivo de teste - validar a integração entre a classe Cliente e a ValidaCPF
 * Segue uma abordagem de desenvolvimento orientado a testes de aceitação.
 */
public class TIReq09CadastrarClienteTests3 {
    @Test
    public void ct01_cadastrar_cliente_com_cpf_valido() {
        // dado que as informacoes de cliente sao validas
        ClienteDTO cliente = new ClienteDTO("95388326047", "Joao", "12345678", "Rua 1", "Bairro 1", "Cidade 1",
                "Complemento 1", "joao@gmail.com");
        // quando o cliente é cadastrado
        Cliente c = new Cliente();
        c.setCpf(cliente.cpf());
        c.setNome(cliente.nome());
        c.setCep(cliente.cep());
        c.setEndereco(cliente.endereco());
        c.setBairro(cliente.bairro());
        c.setCidade(cliente.cidade());
        c.setComplemento(cliente.complemento());
        c.setEmail(cliente.email());
        c.setDataCadastro();
        // então o cliente é cadastrado
        assertNotNull(c);

    }
}
