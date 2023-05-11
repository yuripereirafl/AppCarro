package com.example.appcarro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar;
    private ListView lvProdutos;
    private List<Produto> listProdutos;
    private ArrayAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProdutos = findViewById(R.id.lvprodutos);
        btnAdicionar = findViewById( R.id.buttonSalvar2);

        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                deletar( position );
                return true;
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        FormularioActivity.class);
                i.putExtra("acao", "inserir" );
                startActivity( i );
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarProdutos();
    }

    private void carregarProdutos(){
        listProdutos = ProdutoDAO.getProdutos(this);

        if( listProdutos.isEmpty() ){
            lvProdutos.setEnabled(false);
            String[] listaVazia = {"Lista Vazia!"};
            adapter = new  ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVazia);
        }else {
            lvProdutos.setEnabled(true);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listProdutos);
        }
        lvProdutos.setAdapter( adapter );
    }


    private void deletar(int posicao){
        Produto produto = listProdutos.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setMessage("Confirma a exclusão de " + produto.getNome()+"? ");
        alerta.setNeutralButton("Não", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProdutoDAO.excluir(MainActivity.this, produto.getId() );
                carregarProdutos();
            }
        });
        alerta.show();

    }



}