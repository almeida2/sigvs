package com.fatec.sigvs.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sigvs.model.Cliente;
import com.fatec.sigvs.model.ClienteDTO;
import com.fatec.sigvs.servico.IClienteService;
import com.fatec.sigvs.servico.IEnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ClienteController", description = "Endpoints para gerenciamento de clientes")
@CrossOrigin("*") // desabilita o cors do spring security
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    Logger logger = LogManager.getLogger(this.getClass());

    IClienteService clienteService;
    IEnderecoService enderecoService;

    // injecao da dependencia pelo metodo construtor
    public ClienteController(IClienteService clienteService, IEnderecoService enderecoService) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
        // this.enderecoService = new EnderecoServiceMock(); // stub para o consulta cep
        // excluir do import
    }

    /*
     * As informacoes do cliente sao recebidas em um arquivo DTO. Entidades podem
     * conter informacoes sensiveis que não devem ser expostas diretamente para o
     * app cliente
     * As informacoes de endereco sao fornecidas automaticamente na interface
     * do usuario.
     */

    @Operation(summary = "Cadastra um novo cliente", description = "Recebe um DTO de cliente e persiste no banco de dados após validações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação (ex: CPF ou CEP inválido)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<ResponseApi<Cliente>> saveCliente(@RequestBody ClienteDTO clienteDTO) {
        logger.info(">>>>>> apicontroller cadastro de cliente iniciado...");

        try {
            Cliente novoCliente = clienteService.cadastrar(clienteDTO);
            ResponseApi<Cliente> response = new ResponseApi<>(novoCliente, "Cliente cadastrado com sucesso.");
            logger.info(">>>>>> apicontroller cliente cadastrado");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            // Captura exceção de CEP inválido
            ResponseApi<Cliente> response = new ResponseApi<>(null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (Exception e) {
            // Captura qualquer outro erro inesperado
            ResponseApi<Cliente> response = new ResponseApi<>("Erro inesperado ao cadastrar cliente.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Busca um cliente por CPF", description = "Retorna os dados do cliente encontrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        return clienteService.consultarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Retorna todos os clientes", description = "Lista todos os clientes cadastrados na base de dados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/all")
    public ResponseEntity<ResponseApi<List<Cliente>>> getAll() {
        logger.info(">>>>>> api cliente controller consulta todos iniciado...");
        List<Cliente> clientes = clienteService.consultaTodos();
        ResponseApi<List<Cliente>> response = new ResponseApi<>(clientes, "Lista de clientes cadastrados");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
