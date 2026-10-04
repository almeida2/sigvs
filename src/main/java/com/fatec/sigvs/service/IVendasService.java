package com.fatec.sigvs.service;

import java.math.BigDecimal;

import com.fatec.sigvs.model.Venda;
import com.fatec.sigvs.model.VendaDTO;

public interface IVendasService {
    Venda realizarVenda(VendaDTO vendaDTO);

    String isPrimeiraCompra(String cpf);

}
