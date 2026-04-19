package com.fatec.sigvs.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
/**
 * Objetivo de teste - validar a integração entre a classe Cliente, ValidaCPF e
 * a camada de persistencia
 * Segue uma abordagem de teste sequencial utilizando o padrão Gherkin.
 */
public class TIReq13ExcluirClienteRepoTests {
    @Autowired
    private IClienteRepository repository;

    @Test
    void ct01_excluir_cliente_com_sucesso() {
        // dado que as informacoes sao validas
        Cliente cliente = new ClienteBuilder().criarClienteValido();
        repository.save(cliente);
        // quando consulto o cliente pelo cpf
        repository.findByCpf(cliente.getCpf());
        // entao retorna os detalhes do cliente

    }
}