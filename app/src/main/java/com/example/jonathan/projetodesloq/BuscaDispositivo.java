package com.example.jonathan.projetodesloq;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BuscaDispositivo extends ListActivity {

    /*  Um adaptador para conter os elementos da lista de dispositivos descobertos.
 */
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_busca_dispositivo);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*  Esse trecho não é essencial, mas dá um melhor visual à lista.
            Adiciona um título à lista de dispositivos pareados utilizando
        o layout text_header.xml.
        */
        ListView lv = getListView();
        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.content_busca_dispositivo, lv, false);
        ((TextView) header.findViewById(R.id.ListaDisp)).setText("\nDispositivos próximos\n");
        lv.addHeaderView(header, null, false);

        /*  Cria um modelo para a lista e o adiciona à tela.
            Para adicionar um elemento à lista, usa-se arrayAdapter.add().
         */
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        setListAdapter(arrayAdapter);

        /*  Usa o adaptador Bluetooth padrão para iniciar o processo de descoberta.
         */
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        btAdapter.startDiscovery();

        /*  Cria um filtro que captura o momento em que um dispositivo é descoberto.
            Registra o filtro e define um receptor para o evento de descoberta.
         */
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /*  Este método é executado quando o usuário seleciona um elemento da lista.
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        /*  Extrai nome e endereço a partir do conteúdo do elemento selecionado.
            Nota: position-1 é utilizado pois adicionamos um título à lista e o
        valor de position recebido pelo método é deslocado em uma unidade.
         */
        String item = (String) getListAdapter().getItem(position-1);
        String devName = item.substring(0, item.indexOf("\n"));
        String devAddress = item.substring(item.indexOf("\n")+1, item.length());

        /*  Utiliza um Intent para encapsular as informações de nome e endereço.
            Informa à Activity principal que tudo foi um sucesso!
            Finaliza e retorna à Activity principal.
         */
        Intent intent = new Intent(getBaseContext(), Cadastro_Placa_Dispositivo.class);
        intent.putExtra("nome_disp", devName);
        intent.putExtra("mac", devAddress);
        startActivity(intent);
        /*setResult(RESULT_OK, intent);
        finish();*/

        /*Intent intent = new Intent(getBaseContext(), Main_recebe_formulario.class);
        Bundle bundle = getIntent().getExtras();
        bundle.putString("nome_disp", devName);
        bundle.putString("mac", devAddress);
        startActivity(intent.putExtras(bundle));*/
    }

    /*  Define um receptor para o evento de descoberta de dispositivo.
     */
    private final BroadcastReceiver receiver = new BroadcastReceiver() {

        /*  Este método é executado sempre que um novo dispositivo for descoberto.
         */
        public void onReceive(Context context, Intent intent) {

            /*  Obtem o Intent que gerou a ação.
                Verifica se a ação corresponde à descoberta de um novo dispositivo.
                Obtem um objeto que representa o dispositivo Bluetooth descoberto.
                Exibe seu nome e endereço na lista.
             */
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                arrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };

    /*  Executado quando a Activity é finalizada.
     */
    @Override
    protected void onDestroy() {

        super.onDestroy();

        /*  Remove o filtro de descoberta de dispositivos do registro.
         */
        unregisterReceiver(receiver);
    }

}
