package com.fatec.sigvs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.fatec.sigvs.model.Cliente;

import static org.junit.jupiter.api.Assertions.*;

public class TIReq09CadastrarCliente3Tests {

    @ParameterizedTest(name = "Registro {index}: CPF {0} - Nome {1}")
    @CsvFileSource(files = "C:/edson/dados/clientes.csv", numLinesToSkip = 1)
    public void ct01_cadastrar_clientes_csv(
            String cpf,
            String nome,
            String cep,
            String endereco,
            String bairro,
            String cidade,
            String complemento,
            String email) {

        // 1. Dado que as informações do CSV são lidas
        ClienteDTO clienteDto = new ClienteDTO(cpf, nome, cep, endereco, bairro, cidade, complemento, email);

        // 2. Quando o cliente é instanciado/processado
        Cliente c = new Cliente();
        c.setCpf(clienteDto.cpf());
        c.setNome(clienteDto.nome());
        c.setCep(clienteDto.cep());
        c.setEndereco(clienteDto.endereco());
        c.setBairro(clienteDto.bairro());
        c.setCidade(clienteDto.cidade());
        c.setComplemento(clienteDto.complemento());
        c.setEmail(clienteDto.email());
        c.setDataCadastro();

        // 3. Então o assert é apresentado individualmente para cada linha no relatório
        // do JUnit
        assertNotNull(c, "O cliente não deveria ser nulo para o CPF: " + cpf);
        assertNotNull(c.getDataCadastro(), "A data de cadastro deve ser preenchida");
    }
}
