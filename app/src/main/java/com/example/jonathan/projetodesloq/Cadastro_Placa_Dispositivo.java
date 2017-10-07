package com.example.jonathan.projetodesloq;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Cadastro_Placa_Dispositivo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__placa__dispositivo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TextView nomeText = new TextView(this);
        //TextView mac_disp = new TextView(this);

        TextView nomeText = (TextView) findViewById(R.id.nome_disp);
        TextView mac_disp = (TextView) findViewById(R.id.mac);
        Intent intent = getIntent();

        String nome = intent.getStringExtra("nome_disp");
        String mac = intent.getStringExtra("mac");

        nomeText.setText(nome);
        //setContentView(nomeText);
        mac_disp.setText(mac);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
