package com.example.jonathan.projetodesloq;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity {

    Button cadastrar, logar;
    EditText login,senha;
    UsuarioDAO usuarioDAO;
    Sessao sessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cadastrar = (Button) findViewById(R.id.Botao_Cad);
        logar = (Button) findViewById(R.id.Botao_login);
        login = (EditText) findViewById(R.id.EditUsuario);
        senha = (EditText) findViewById(R.id.EditSenha);
        usuarioDAO = new UsuarioDAO(this);
        sessao = new Sessao(this);

        if(sessao.loggedin())
        {
            startActivity(new Intent(getBaseContext(),Aplicacao.class));
            finish();
        }

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cont = usuarioDAO.validaLogin(login.getText().toString(),senha.getText().toString());

                if(cont > 0) {

                    Toast.makeText(TelaLogin.this, "Logado!", Toast.LENGTH_LONG).show();
                    sessao.setLoggedin(true);
                    startActivity(new Intent(TelaLogin.this,Aplicacao.class));
                    finish();
                }
                else
                {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator),"Usuário/Senha inválido!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    login.setText("");
                    senha.setText("");
                    login.requestFocus();
                }

            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(TelaLogin.this,Tela_Cadastro_Usuario.class);
                startActivity(i);
            }
        });
    }

}
