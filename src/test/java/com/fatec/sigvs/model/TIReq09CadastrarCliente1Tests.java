package com.fatec.sigvs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Objetivo de teste - validar a integração entre a classe Cliente e a ValidaCPF
 * Segue uma abordagem de teste sequencial utilizando o padrão Gherkin.
 */
public class TIReq09CadastrarCliente1Tests {
    Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new ClienteBuilder().criarClienteValido();
    }

    @Test
    public void ct01_cadastrar_cliente_com_cpf_valido() {
        // dado que as informacoes de cliente sao validas
        // quando a operação é confirmada
        // então o cliente é cadastrado
        assertNotNull(cliente);

    }

    @Test
    public void ct02_cadastrar_cliente_com_cpf_em_branco() {
        try {
            // dado que as informações do cliente são invalidas
            // quando a operação é confirmada
            cliente.setCpf("");
        } catch (Exception e) {
            // então mensagem cliente invalido
            assertEquals("CPF invalido", e.getMessage());
        }
    }

    @Test
    public void ct03_cadastrar_cliente_com_cpf_invalido() {
        try {
            // dado que as informações do cliente são invalidas
            // quando a operação é confirmada
            cliente.setCpf(null);
        } catch (Exception e) {
            // então mensagem cliente invalido
            assertEquals("CPF invalido", e.getMessage());
        }
    }

    @Test
    public void ct04_cadastrar_cliente_com_cpf_invalido() {
        try {
            // dado que as informações do cliente são invalidas
            // quando a operação é confirmada
            cliente.setCpf("12345678901");
        } catch (Exception e) {
            // então mensagem cliente invalido
            assertEquals("CPF invalido", e.getMessage());
        }
    }
}
