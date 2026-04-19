package com.fatec.sigvs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
/**
 * Objetivo de teste - validar a integração entre a classe Cliente e a ValidaCPF
 * Segue uma abordagem de teste sequencial utilizando o padrão Gherkin.
 */
public class TIReq12AtualizarInfClienteRepoTests {
    Cliente cliente;
    ClienteBuilder builder;
    @Autowired
    private IClienteRepository repository;

    @BeforeEach
    public void setup() {
        builder = new ClienteBuilder();
        cliente = builder.criarClienteValido();
        repository.save(cliente);
    }

    @Test
    public void ct01_atualizar_nome_do_cliente_com_sucesso() {
        // dado que as informacoes sao validas
        String novoNome = "Maria";
        cliente.setNome(novoNome);
        repository.save(cliente);
        // quando consulto o cliente pelo cpf
        Optional<Cliente> clienteConsultado = repository.findByCpf(cliente.getCpf());
        // entao retorna os detalhes do cliente
        assertTrue(clienteConsultado.isPresent());
        assertEquals(novoNome, clienteConsultado.get().getNome());

    }
}