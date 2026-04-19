package com.fatec.sigvs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Objetivo de teste - teste unitario da classe ValidaCPF.
 * A seta (->) em Java é o operador principal das Expressões Lambda,
 * introduzidas no Java 8. Ele separa os parâmetros da função do seu corpo
 * (implementação), * permitindo escrever código de forma mais concisa,
 * especialmente ao trabalhar com interfaces funcionais e coleções (Streams API)
 */
@DisplayName("Requisito 09 - Cadastro de Cliente")
class TUReq09CadastrarClienteTests {

    @Test
    @DisplayName("CT01 - Verifica o comportamento da aplicação de um cliente com CPF válido")
    void ct01_quando_dados_validos_retorna_true() {
        assertTrue(new ValidaCpf("95388326047").isValido(),
                () -> "O CPF " + "95388326047" + " deveria ser considerado válido");
    }

    @Test
    @DisplayName("CT02 - Verifica o comportamento da aplicação de um cliente com CPF inválido")
    void ct02_quando_dados_invalidos_retorna_false() {
        assertFalse(new ValidaCpf("").isValido(),
                () -> "O CPF deveria ser considerado inválido");
    }
}
