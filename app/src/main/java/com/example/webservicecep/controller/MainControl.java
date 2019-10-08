package com.example.webservicecep.controller;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webservicecep.R;
import com.example.webservicecep.model.Endereco;
import com.example.webservicecep.model.EnderecoDTO;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SaxAsyncHttpResponseHandler;

import org.xml.sax.helpers.DefaultHandler;

import cz.msebera.android.httpclient.Header;

public class MainControl {

    private Activity activity;
    private EditText editTextCep;
    private EditText editTextRua;
    private EditText editTextCidade;
    private EditText editTextUf;

    public MainControl(Activity activity) {
        this.activity = activity;
        initComponents();
    }

    private void initComponents() {

        editTextCep = activity.findViewById(R.id.editTextCep);
        editTextCidade = activity.findViewById(R.id.editTextCidade);
        editTextRua = activity.findViewById(R.id.editTextRua);
        editTextUf = activity.findViewById(R.id.editTextUf);
    }

    private void desabilitarForm(){
        editTextRua.setEnabled(false);
        editTextCidade.setEnabled(false);
        editTextUf.setEnabled(false);
    }

    private void habilitarForm(){
        editTextRua.setEnabled(true);
        editTextCidade.setEnabled(true);
        editTextUf.setEnabled(true);
    }

    private void limparDados(){
        editTextRua.setText("");
        editTextCidade.setText("");
        editTextUf.setText("");
    }

    private void pesquisarPorCep(String cep){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://viacep.com.br/ws/" + cep + "/json", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(activity, "Iniciou", Toast.LENGTH_SHORT).show();
                desabilitarForm();
                editTextRua.setText("...");
                editTextCidade.setText("...");
                editTextUf.setText("...");
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                //retorno em string do viacep em Json
                String enderecoJason = new String(bytes);
                //conversao da string json para objeto
                Gson gson = new Gson();
                //conversao direta
                EnderecoDTO eDTO = gson.fromJson(enderecoJason, EnderecoDTO.class);
                Endereco endereco = eDTO.getEndereco();
                carregarForm(endereco);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                habilitarForm();
                limparDados();
            }
        });
    }

    private void carregarForm(Endereco e) {
        habilitarForm();
        editTextCidade.setText(e.getCidade());
        editTextRua.setText(e.getRua());
        editTextUf.setText(e.getUf());
    }

    public void pesquisarCepAction(){
        String cep = editTextCep.getText().toString();
        pesquisarPorCep(cep);
    }
}
