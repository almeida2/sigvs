package com.fatec.sigvs.service;

import java.util.List;
import com.fatec.sigvs.model.Produto;

public interface IProdutoService {
    Produto cadastrarProduto(Produto produto);
    List<Produto> listarTodos();
}
