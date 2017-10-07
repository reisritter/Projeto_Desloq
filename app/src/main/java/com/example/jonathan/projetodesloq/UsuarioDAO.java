package com.example.jonathan.projetodesloq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO extends SQLiteOpenHelper {

    public UsuarioDAO (Context c)
    {
        super(c,"Rodizio",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "Create table Usuario" +
                "(" +
                "   ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   NOME_USUARIO TEXT," +
                "   USER_USUARIO TEXT," +
                "   SENHA_USUARIO TEXT," +
                "   SEXO_USUARIO TEXT" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP DATABASE IF EXISTS Usuario");
        onCreate(db);
    }

    public void insere(Usuario u) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("NOME_USUARIO", u.getNome());
        dados.put("SEXO_USUARIO", String.valueOf(u.getSexo()));
        dados.put("USER_USUARIO", u.getUsuario());
        dados.put("SENHA_USUARIO", u.getSenha());

        db.insert("Usuario", null, dados);
    }

    public List<Usuario> buscaUsuarios() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Usuario;", null);
        List<Usuario> u = new ArrayList<Usuario>();

        while (c.moveToNext()) {

            Usuario usuario = new Usuario();
            usuario.setId(c.getInt(c.getColumnIndex("ID_USUARIO")));
            usuario.setNome(c.getString(c.getColumnIndex("NOME_USUARIO")));
            usuario.setSexo(c.getString(c.getColumnIndex("SEXO_USUARIO")));
            usuario.setUsuario(c.getString(c.getColumnIndex("USER_USUARIO")));
            usuario.setSenha(c.getString(c.getColumnIndex("SENHA_USUARIO")));

            u.add(usuario);
        }
        c.close();
        return u;
    }

    public int validaLogin(String user, String senha) {

        int cont = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT ID_USUARIO FROM Usuario WHERE USER_USUARIO = '"+user+"' and SENHA_USUARIO = '"+senha+"';", null);

        while (c.moveToNext())
           cont++;

        c.close();
        return cont;
    }
}
