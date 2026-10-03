package com.fatec.sigvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.sigvs.model.ItemVendaDTO;
import com.fatec.sigvs.model.Produto;
import com.fatec.sigvs.model.Venda;
import com.fatec.sigvs.model.VendaDTO;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Venda realizarVenda(VendaDTO vendaDTO) {
        Venda novaVenda = new Venda();

        for (ItemVendaDTO itemDTO : vendaDTO.getItens()) {
            // Busca o produto pelo ID enviado no JSON
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Produto não encontrado com o ID: " + itemDTO.getProdutoId()));

            // Executa a baixa do estoque (lança exceção se não houver saldo)
            produto.baixarEstoque(itemDTO.getQuantidade());

            // Adiciona o item à venda e atualiza o total
            novaVenda.adicionarProduto(produto, itemDTO.getQuantidade());
        }

        // Salva a venda e atualiza o estoque do produto de forma atômica
        return vendaRepository.save(novaVenda);
    }
}
