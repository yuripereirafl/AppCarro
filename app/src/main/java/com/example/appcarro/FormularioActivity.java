package com.example.appcarro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private TextView tvID;
    private Spinner spCategorias;
    private Button btSalvar;
    private String acao;
    private Produto produto;
    private RadioButton rdNovo;
    private RadioButton rdUsado;
    private CheckBox chBranco;
    private CheckBox chPreto;
    private CheckBox chCinza;
    private CheckBox chVermelho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etNome = findViewById(R.id.editNome);
        rdNovo = findViewById(R.id.radioButtonNovo);
        rdUsado = findViewById(R.id.radioButtonUsado);
        chBranco = findViewById(R.id.checkBoxBranco);
        chPreto = findViewById(R.id.checkBoxPreto);
        chCinza = findViewById(R.id.checkBoxCinza);
        chVermelho = findViewById(R.id.checkBoxVermelho);

        spCategorias = findViewById(R.id.spinnerCategoria);
        btSalvar = findViewById(R.id.buttonSalvar2);
        acao = getIntent().getStringExtra("acao");


        if(acao.equals("editar"))
        {
            carregarFormulario();
        }
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }

        });

    }


    public void salvar() {
        String nome = etNome.getText().toString();
        if (nome.isEmpty() || spCategorias.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Você deve preencher ambos os campos!!", Toast.LENGTH_LONG).show();
        } else {
            if (acao.equals("inserir")) {
                produto = new Produto();
            }
            produto.setNome(nome);
            produto.setCategoria(spCategorias.getSelectedItem().toString());

            if (acao.equals("inserir")) {
                configurarEstadoProduto(produto);
                configurarCorProduto(produto);
            }
        }

        if (acao.equals("inserir")) {
            etNome.setText("");
            spCategorias.setSelection(0, true);
        }

        finish();
    }





    public void carregarFormulario()
    {

        String idProduto = getIntent().getStringExtra("idProduto");

        tvID = findViewById(R.id.textViewIDProduto);
        produto = new Produto();
        tvID.setText(String.valueOf(idProduto));
        etNome.setText(produto.getNome());
        String[] categorias =getResources().getStringArray(R.array.strCategorias);
        for(int i =0;i<categorias.length;i++){
            if(produto.getCategoria().equals(categorias[i])){
                spCategorias.setSelection(i);
                break;
            }
        }
        if(produto.getEstado().equals(rdNovo.getText().toString())){
            rdNovo.setChecked(true);
        }else if(produto.getEstado().equals(rdUsado.getText().toString())) {
            rdUsado.setChecked(true);
        }
        String[] arrCores = produto.getCor().split(",");
        for (int i = 0; i < arrCores.length; i++) {
            if(arrCores [i].equals(chBranco.getText().toString())){
                chBranco.setChecked(true);
            }
            if(arrCores [i].equals(chPreto.getText().toString())){
                chPreto.setChecked(true);
            }
            if(arrCores [i].equals(chCinza.getText().toString())){
                chCinza.setChecked(true);
            }
            if(arrCores [i].equals(chVermelho.getText().toString())){
                chVermelho.setChecked(true);
            }
        }
    }
    public void configurarEstadoProduto(Produto produto){
        if(rdNovo.isChecked()){
            produto.setEstado(rdNovo.getText().toString());
        }else if(rdUsado.isChecked()) {
            produto.setEstado(rdUsado.getText().toString());
        }
    }
    public void configurarCorProduto(Produto produto){
        String cor = "";

        if(chBranco.isChecked()){
            if(!cor.isEmpty()) cor+=",";
            cor += chBranco.getText().toString();
        }
        if(chPreto.isChecked()){
            if(!cor.isEmpty()) cor+=",";
            cor += chPreto.getText().toString();
        }
        if(chCinza.isChecked()){
            if(!cor.isEmpty()) cor+=",";
            cor += chCinza.getText().toString();

        }
        if(chVermelho.isChecked()){
            if(!cor.isEmpty()) cor+=",";
            cor += chVermelho.getText().toString();
        }
        produto.setCor(cor);
    }
}