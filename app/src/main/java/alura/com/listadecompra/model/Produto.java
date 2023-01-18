package alura.com.listadecompra.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private Long id =0l;
    private String nomeProduto;
    private Double preco;
    private String marca;

    public Produto( ) {
    }

    public Produto(String nomeProduto, Double preco, String marca) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return nomeProduto + " - "+preco;
    }
}
