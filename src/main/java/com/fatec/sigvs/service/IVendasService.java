package com.fatec.sigvs.service;

import java.math.BigDecimal;

import com.fatec.sigvs.model.Venda;
import com.fatec.sigvs.model.VendaDTO;

public interface IVendasService {
    Venda realizarVenda(VendaDTO vendaDTO);

    String primeiraCompra(String cpf);

    BigDecimal calculaPagamento(String primeiraCompra, String dataVenda, String valorCompra);
}
