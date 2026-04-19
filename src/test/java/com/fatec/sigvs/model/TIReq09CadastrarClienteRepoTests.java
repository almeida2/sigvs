package com.fatec.sigvs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
/**
 * Objetivo de teste - validar a integração entre a classe Cliente, ValidaCPF e
 * a camada de persistencia
 * Segue uma abordagem de teste sequencial utilizando o padrão Gherkin.
 */
public class TIReq09CadastrarClienteRepoTests {

    @Autowired
    private IClienteRepository repository;

    public String obtemDataAtual() {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = dataAtual.format(pattern);
        return data;
    }

    @Test
    void ct01_cadastrar_cliente_com_sucesso() {
        // dado que as informacoes sao validas
        Cliente cliente = new ClienteBuilder().criarClienteValido();
        // quando confirmo a operacao
        Cliente novoCliente = repository.save(cliente);
        // entao cliente cadastrado com sucesso
        assertNotNull(novoCliente.getId());
        assertEquals("34525227095", novoCliente.getCpf());
        assertEquals(obtemDataAtual(), novoCliente.getDataCadastro());
    }

    @Test
    void ct02_cadastrar_cliente_com_cpf_duplicado() {
        try {
            // dado que as informacoes do cpf sao duplicadas
            Cliente c1 = new ClienteBuilder().criarClienteValido();
            repository.save(c1);
            // quando confirmo a operacao
            Cliente c2 = new ClienteBuilder().criarClienteValido();
            repository.saveAndFlush(c2);
            // entao excecao DataIntegrityViolationException deve ser lançada
            fail("A exceção DataIntegrityViolationException deve ser lançada");
        } catch (DataIntegrityViolationException e) {
            // Obter a causa raiz, onde o Hibernate ou o JDBC para detalhar o erro
            Throwable causaRaiz = e.getRootCause();
            String mensagem = (causaRaiz != null) ? causaRaiz.getMessage() : e.getMessage();
            System.out.println("Mensagem de erro: " + mensagem);

            // Verificar se a mensagem contém termos técnicos do db
            // O H2 usa "Unique index or primary key violation", o MySQL usa "Duplicate
            // entry"
            assertTrue(mensagem.toLowerCase().contains("Unique index or primary key violation") ||
                    mensagem.toLowerCase().contains("constraint") ||
                    mensagem.toLowerCase().contains("unique"),
                    "A mensagem deveria indicar duplicidade ou violação de constraint");
        }

    }

}