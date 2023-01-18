package alura.com.listadecompra.ui.activity;

import static alura.com.listadecompra.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import alura.com.listadecompra.R;
import alura.com.listadecompra.model.Produto;
import alura.com.listadecompra.dao.ProdutoDAO;

public class FormCadastroProdutoActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR_ADCIONA_ALUNO = "Adiciona um novo produto";
    public static final String TITLE_APPBAR_EDITA_ALUNO = "Edita um produto";
    final ProdutoDAO produtoDAO = new ProdutoDAO();
    private EditText nomeProduto;
    private EditText precoProduto;
    private EditText marcaProduto;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro_produto);
        buscaViews();
        Intent dadosPorduto = getIntent();
        carregaProduto(dadosPorduto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_produto_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.acticity_main_form_menu_adicionar){
            preencheProduto();
            if(produto.getId() >  1){
                produtoDAO.editaProduto(produto);
            }else {
                produtoDAO.salvaProduto(produto);
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void carregaProduto(Intent dadosPorduto) {
        if (dadosPorduto.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITLE_APPBAR_EDITA_ALUNO);
            produto = (Produto) dadosPorduto.getSerializableExtra(CHAVE_ALUNO);
            nomeProduto.setText(produto.getNomeProduto());
            precoProduto.setText(produto.getPreco().toString());
            marcaProduto.setText(produto.getMarca());
        } else {
            setTitle(TITLE_APPBAR_ADCIONA_ALUNO);
            produto = new Produto();
        }
    }

    public void preencheProduto() {
        String nome = nomeProduto.getText().toString();
        Double preco = Double.parseDouble(precoProduto.getText().toString());
        String marca = marcaProduto.getText().toString();

        produto.setNomeProduto(nome);
        produto.setPreco(preco);
        produto.setMarca(marca);
    }

    public void buscaViews() {
        nomeProduto = findViewById(R.id.activity_form_cadastro_nome);
        precoProduto = findViewById(R.id.activity_form_cadastro_preco);
        marcaProduto = findViewById(R.id.activity_form_cadastro_marca);
    }
}