package com.fatec.sigvs.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class VendaAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) {
        // Aqui você faz o papel do Service/Mapper
        String dataStr = accessor.getString(1);
        LocalDate data = (dataStr == null || dataStr.equals("NULL")) ? null
                : LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Retorna a entidade montada (ou tenta montar)
        Vendas v = new Vendas();
        v.setCpf(accessor.getString(0));
        v.setDataVencimento(data);
        v.setServicoContratado(accessor.getString(2));
        v.setValor(accessor.getString(3));
        return v;
    }
}