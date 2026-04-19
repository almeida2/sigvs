package com.fatec.sigvs.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.sigvs.model.Cliente;
import com.fatec.sigvs.model.ClienteDTO;
import com.fatec.sigvs.model.IClienteRepository;

@SpringBootTest
public class Req09CadastrarClienteTests {
    @Autowired
    IClienteService clienteService;
    @Autowired
    IClienteRepository repository;
    ClienteDTO cliente;
    String hoje;

    @BeforeEach
    public void preRequisitoDeTeste() {
        repository.deleteAll();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        hoje = LocalDate.now().format(pattern);
        // simula a entrada de usuario pela interface
        cliente = new ClienteDTO("48400451007", "Jose da Silva", "01310000", "Avenida Paulista", "Bela Vista",
                "São Paulo", "123", "jose@gmail.com");
    }

    @Test
    void ct01_quando_dados_validos_cliente_cadastrado_com_sucesso() {
        // dado que as informacoes sao validas
        // quando confirmo a operacao
        Cliente novoCliente = clienteService.cadastrar(cliente);
        // entao cliente cadastrado com sucesso e informacoes disponiveis para consulta
        assertNotNull(novoCliente.getId());
        assertEquals("48400451007", novoCliente.getCpf());
        assertEquals(hoje, novoCliente.getDataCadastro());
        assertEquals("Avenida Paulista", novoCliente.getEndereco());
        assertEquals("Bela Vista", novoCliente.getBairro());
        assertEquals("São Paulo", novoCliente.getCidade());

    }

    @Test
    void ct02_quando_cpf_duplicado_cliente_nao_cadastrado() {
        try {
            // dado que o cliente ja esta cadastrado
            clienteService.cadastrar(cliente);
            // quando confirmo a operacao
            clienteService.cadastrar(cliente);

        } catch (IllegalArgumentException e) {
            // entao cliente nao cadastrado
            assertEquals("Cliente com este CPF já cadastrado.", e.getMessage());

        }
    }

}