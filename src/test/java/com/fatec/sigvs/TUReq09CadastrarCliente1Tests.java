package com.fatec.sigvs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fatec.sigvs.model.ValidaCpf;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Objetivo de teste - teste unitario da classe ValidaCPF.
 * A seta (->) em Java é o operador principal das Expressões Lambda,
 * introduzidas no Java 8. Ele separa os parâmetros da função do seu corpo
 * (implementação), * permitindo escrever código de forma mais concisa,
 * especialmente ao trabalhar com interfaces funcionais e coleções (Streams API)
 */
@DisplayName("Requisito 09 - Cadastro de Cliente")
class TUReq09CadastrarCliente1Tests {

    @Test
    @DisplayName("CT01 - Deve validar a criação de um cliente com CPF válido")
    void deveValidarCpfQuandoClientePossuiDadosValidos() {
        assertTrue(new ValidaCpf("95388326047").isValido(),
                () -> "O CPF " + "95388326047" + " deveria ser considerado válido");
    }
}
