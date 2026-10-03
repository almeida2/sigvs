package com.fatec.sigvs.model;

import java.util.List;

public class VendaDTO {
    private List<ItemVendaDTO> itens;

    // Getters e Setters
    public List<ItemVendaDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaDTO> itens) {
        this.itens = itens;
    }
}
