package com.fatec.sigvs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fatec.sigvs.model.ValidaCpf;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Requisito 09 - Cadastro de Cliente")
class Req09CadastrarClienteTests2 {

    @Test
    @DisplayName("CT01 - Deve validar a criação de um cliente com CPF válido")
    void deveValidarCpfQuandoClientePossuiDadosValidos() {
        assertTrue(new ValidaCpf("95388326047").isValido(),
                () -> "O CPF " + "95388326047" + " deveria ser considerado válido");
    }
}
