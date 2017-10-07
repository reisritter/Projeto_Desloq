package com.example.jonathan.projetodesloq;


import android.app.Activity;
import android.widget.EditText;
import android.widget.RadioGroup;

public class FormularioHelper {

    private EditText campoUsuario;
    private EditText campoSenha;
    private EditText campoNome;
    private RadioGroup radioSexo;

    public FormularioHelper (Activity a)
    {
        this.campoUsuario = (EditText) a.findViewById(R.id.EditUsuario);
        this.campoSenha = (EditText) a.findViewById(R.id.EditSenha);
        this.campoNome = (EditText) a.findViewById(R.id.EditUsuario);
        this.radioSexo = (RadioGroup) a.findViewById(R.id.RadioGroup_sexo);
    }

    public Usuario pegarDadosUsuario()
    {
        Usuario u = new Usuario();

        u.setNome(campoNome.getText().toString());
        u.setUsuario(campoUsuario.getText().toString());
        u.setSenha(campoSenha.getText().toString());

        if(radioSexo.getCheckedRadioButtonId() == R.id.Radio_F)
            u.setSexo("F");
        else if(radioSexo.getCheckedRadioButtonId() == R.id.Radio_M)
            u.setSexo("M");

        return u;
    }

}
