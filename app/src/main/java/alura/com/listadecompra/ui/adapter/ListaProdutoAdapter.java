package alura.com.listadecompra.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alura.com.listadecompra.R;
import alura.com.listadecompra.model.Produto;

public class ListaProdutoAdapter extends BaseAdapter {
    private final List<Produto> produtoList = new ArrayList<>();
    private Context contex;

    public ListaProdutoAdapter(Context contex) {
        this.contex = contex;
    }

    @Override
    public int getCount() {
        return produtoList.size();
    }

    @Override
    public Produto getItem(int position) {
        return produtoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return produtoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // cria a view para cada elemento
        View viewCriada = LayoutInflater.from(contex).inflate(R.layout.item_produto, parent,false);// Faz a mesma coisa que o getMenuInflater só que para views
        Produto produtoDevolvido = produtoList.get(position);

        TextView nome = viewCriada.findViewById(R.id.item_produto_nome);
        TextView preco = viewCriada.findViewById(R.id.item_produto_preco);

        nome.setText(produtoDevolvido.getNomeProduto());
        preco.setText(produtoDevolvido.getPreco().toString());

        return viewCriada;
    }

    public void clear() {
        produtoList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Produto> listaProduto) {
        produtoList.addAll(listaProduto);
    }

    public void remove(Produto produtoEscolhido) {
        produtoList.remove(produtoEscolhido);
        notifyDataSetChanged();
    }
}
