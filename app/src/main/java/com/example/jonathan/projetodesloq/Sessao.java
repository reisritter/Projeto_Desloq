package com.example.jonathan.projetodesloq;


import android.content.Context;
import android.content.SharedPreferences;

public class Sessao {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context c;

    public Sessao(Context c)
    {
        this.c = c;
        prefs = c.getSharedPreferences("app", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean l)
    {
        editor.putBoolean("logged",l);
        editor.commit();
    }

    public boolean loggedin()
    {
        return prefs.getBoolean("logged",false);
    }
}
