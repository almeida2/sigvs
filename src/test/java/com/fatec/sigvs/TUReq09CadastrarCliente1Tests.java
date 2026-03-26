package com.fatec.sigvs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fatec.sigvs.model.ValidaCpf;

/**
 * Teste unitario da classe ValidaCpf
 */
public class TUReq09CadastrarCliente1Tests {
    @Test
    public void ct01_cadastrar_cliente_com_cpf_valido() {
        // dado que as informacoes de cliente sao validas
        ClienteDTO cliente = new ClienteDTO("95388326047", "Joao", "12345678", "Rua 1", "Bairro 1", "Cidade 1",
                "Complemento 1", "joao@gmail.com");
        // quando o cliente é cadastrado
        ValidaCpf cpf = new ValidaCpf(cliente.cpf());
        // então o cpf é valido
        assertTrue(cpf.isValido());
    }
}
