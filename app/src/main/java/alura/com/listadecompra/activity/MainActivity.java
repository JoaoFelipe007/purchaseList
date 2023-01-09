package alura.com.listadecompra.activity;

import static alura.com.listadecompra.activity.ConstantesActivities.CHAVE_ALUNO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import alura.com.listadecompra.R;
import alura.com.listadecompra.model.Produto;
import alura.com.listadecompra.dao.ProdutoDAO;

public class MainActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de Produtos";
    final ProdutoDAO produtoDAO = new ProdutoDAO();
    private ArrayAdapter<Produto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITLE_APPBAR);
        configruaListView();
        configuraFabNovoProduto();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //Cria um menu de contexto para activity
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);/*Vincula a activity de menu, usando uam tecnica de inflar (Vai pegar o arquivo estatico
            e vai transformar em um onjeto de menu)
         */
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {// configura o menu de contexto
        int itemId = item.getItemId();
        if(itemId == R.id.acticity_main_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();/* Desse jeito o menuInfo só vai funcionar para
       os adapter view... e o menu info serve para pegar mais informações do adapter*/
            Produto produtoEscolhido = adapter.getItem(menuInfo.position);
            produtoDAO.remove(produtoEscolhido);
            adapter.remove(produtoEscolhido);
            mostraOTotal();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(produtoDAO.listaTodos());
        mostraOTotal();
    }


    public void configuraFabNovoProduto() {
        FloatingActionButton fabNovoPorduto = findViewById(R.id.activity_main_fab_novo_produto);
        fabNovoPorduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormCadastroProdutoActivity.class));
            }
        });
    }

    private void mostraOTotal() {
        TextView valorTotal = findViewById(R.id.activity_main_valortotal);
        valorTotal.setText("R$ " + produtoDAO.somaTotal().toString());
    }

    private void configruaListView() {
        ListView listProduto = findViewById(R.id.activity_main_listview);
        configuraAdpaterView(listProduto);
        configuraClickDoProduto(listProduto);
        registerForContextMenu(listProduto);// registra um context menu para uma view
    }

//    private void configuraListenerDeClickLongoPorItem(ListView listProduto) {
//        listProduto.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//              Produto produtoEscolhido = (Produto) parent.getItemAtPosition(position);
//              produtoDAO.remove(produtoEscolhido);
//                adapter.remove(produtoEscolhido);
//                mostraOTotal();
//                return false;
//            }
//        });
//}

    private void configuraClickDoProduto(ListView listProduto) {
        listProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoEscolhido = (Produto) parent.getItemAtPosition(position);
                Intent vaiParaFormProduto = new Intent(MainActivity.this, FormCadastroProdutoActivity.class);
                vaiParaFormProduto.putExtra(CHAVE_ALUNO, produtoEscolhido);
                startActivity(vaiParaFormProduto);
            }
        });
    }

    private void configuraAdpaterView(ListView listProduto) {
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1
        );
        listProduto.setAdapter(adapter);
    }
}