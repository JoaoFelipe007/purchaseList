package alura.com.listadecompra.model.dao;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import alura.com.listadecompra.model.Produto;

public class ProdutoDAO {
    private final static List<Produto> produtos = new ArrayList<>();
    private static Long contadorDeIds =1l;

    public void salvaProduto(Produto produto) {
        produto.setId(contadorDeIds);
        produtos.add(produto);
        contadorDeIds++;
    }

    public void editaProduto(Produto produto){
        Produto produtoEscholhido = buscaProdutoPeloId(produto);
        if(produtoEscholhido != null){
            Integer posicao = produtos.indexOf(produtoEscholhido);
            produtos.set(posicao,produto);
        }
    }

    @Nullable
    private Produto buscaProdutoPeloId(Produto produto) {
        Produto produtoEscholhido = null;
        for(Produto p : produtos){
            if(p.getId().equals(produto.getId())){
                produtoEscholhido = p;
            }
        }
        return produtoEscholhido;
    }

    public List<Produto> listaTodos() {
        return new ArrayList<>(produtos);
    }

    public Double somaTotal() {
        Double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    public void remove(Produto produto) {
        Produto produtEscholido = buscaProdutoPeloId(produto);
        produtos.remove(produtEscholido);
    }
}
