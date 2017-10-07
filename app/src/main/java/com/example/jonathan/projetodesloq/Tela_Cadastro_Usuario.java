package com.example.jonathan.projetodesloq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class Tela_Cadastro_Usuario extends AppCompatActivity {

    Button salvar;
    EditText nome,usuario,senha;
    RadioGroup sexo;
    UsuarioDAO usuarioDAO;
    Usuario u;
    FormularioHelper formHelper;
    String erro = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__cadastro__usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        salvar = (Button) findViewById(R.id.Botao_Salvar);
        usuarioDAO = new UsuarioDAO(Tela_Cadastro_Usuario.this);
        u = new Usuario();

        nome = (EditText)findViewById(R.id.EditNome);
        usuario = (EditText)findViewById(R.id.EditUsuario);
        senha = (EditText)findViewById(R.id.EditSenha);
        sexo = (RadioGroup)findViewById(R.id.RadioGroup_sexo);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                u.setNome(nome.getText().toString());
                u.setUsuario(usuario.getText().toString());
                u.setSenha(senha.getText().toString());

                if(sexo.getCheckedRadioButtonId() == R.id.Radio_F)
                    u.setSexo("F");
                else if(sexo.getCheckedRadioButtonId() == R.id.Radio_M)
                    u.setSexo("M");

                if(validar() == 0)
                {
                    usuarioDAO.insere(u);
                    usuarioDAO.close();

                    List<Usuario> lista = usuarioDAO.buscaUsuarios();
                    Toast.makeText(Tela_Cadastro_Usuario.this,"Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Tela_Cadastro_Usuario.this,TelaLogin.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(Tela_Cadastro_Usuario.this,erro, Toast.LENGTH_LONG).show();
            }
        });

    }

    private int validar()
    {
        int val = 0;
        erro = "";

        if(nome.getText().toString().isEmpty())
        {
            erro += "Campo nome vazio!\n";
            val++;
        }
        if(usuario.getText().toString().isEmpty())
        {
            erro += "Campo usuario vazio!\n";
            val++;
        }
        if(senha.getText().toString().isEmpty())
        {
            erro += "Campo senha vazio!\n";
            val++;
        }
        if(sexo.getCheckedRadioButtonId() == -1)
        {
            erro += "Nenhuma opção de gênero selecionado!";
            val++;
        }

        return val;
    }

}
