package com.fatec.sigvs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TIReq16RegistrarVendaTests {

    // entidade
    private Vendas v;

    @ParameterizedTest(name = "Registro {index}: CPF {0}")
    @CsvFileSource(resources = "/dataset-vendas.csv", numLinesToSkip = 1, emptyValue = "", nullValues = "NULL")
    public void ct_verifica_comportamento_cadastro(String cpf, String dataVencimento, String servicoContratado,
            String valor, String re) {
        try {
            v = new Vendas();
            v.setCpf(cpf);
            v.setServicoContratado(servicoContratado);
            v.setDataEmissao(LocalDate.now());
            // Simulando o comportamento do Service: Tratando a String antes da Entidade
            LocalDate vencimento = null;
            if (dataVencimento != null && !dataVencimento.equals("NULL")) {
                vencimento = LocalDate.parse(dataVencimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
            v.setDataVencimento(vencimento);
            v.setValor(valor);
        } catch (Exception e) {
            assertEquals(re, e.getMessage());
        }
    }
}
