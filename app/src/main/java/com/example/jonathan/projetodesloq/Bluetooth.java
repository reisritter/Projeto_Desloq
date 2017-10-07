package com.example.jonathan.projetodesloq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Jonathan on 19/06/2016.
 */
public class Bluetooth extends BroadcastReceiver {

    //public static Intent REQUEST_ENABLE_BT = 1;
    private BluetoothListener listener;
    private ArrayList<BluetoothDevice> lista;
    private BluetoothAdapter dispositivo;

    private Bluetooth(BluetoothListener listener) {
        this.listener = listener;
        lista = new ArrayList<BluetoothDevice>();
    }

    public static Bluetooth startFindDevices(Context context, BluetoothListener listener) throws IOException {

        Bluetooth bluetooth = new Bluetooth(listener);

        // Pego o adapter
        bluetooth.dispositivo = BluetoothAdapter.getDefaultAdapter();



        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Dispositivo não suporta Bluetooth
        }
        // verifico se o bluetooth esta desabilitado, se tiver habilito
        // retorno null (tratar)

        if (!bluetooth.dispositivo.isEnabled()) {
            bluetooth.dispositivo.enable();
            return null;
        }

        // Registro os Broadcast necessarios para a busca de dispositivos
        IntentFilter filter = new IntentFilter(BluetoothListener.ACTION_FOUND);
        IntentFilter filter2 = new IntentFilter(BluetoothListener.ACTION_DISCOVERY_FINISHED);
        IntentFilter filter3 = new IntentFilter(BluetoothListener.ACTION_DISCOVERY_STARTED);
        context.registerReceiver(bluetooth, filter);
        context.registerReceiver(bluetooth, filter2);
        context.registerReceiver(bluetooth, filter3);

        // inicio a busca e retorno a classe instanciada.
        bluetooth.dispositivo.startDiscovery();
        return bluetooth;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        //caso encontre um dispositivo:
        if (action.compareTo(BluetoothDevice.ACTION_FOUND) == 0) {

            //pega as o dispositivo encontrado:
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            //se a lista já tiver esse dispositivo eu retorno para o proximo
            //isso permite que sejá mostrado somente uma vez meu dispositivo
            //problema muito comum em exemplos
            if (lista.contains(device)) {
                return;
            }

            //adiciono o dispositivo na minha lista:
            lista.add(device);

        } else if (action.compareTo(BluetoothAdapter.ACTION_DISCOVERY_FINISHED) == 0) {
            //caso o discovery chegue ao fim eu desregistro meus broadcasts
            //SEMPRE FAÇA ISSO quando terminar de usar um broadcast
            context.unregisterReceiver(this);
        }

        if (listener != null) {
            //se foi definido o listener eu aviso a quem ta gerenciando.
            listener.action(action);
        }
    }

    public static Bluetooth getBondedDevices(Context applicationContext) throws IOException {
        Bluetooth bluetooth = new Bluetooth(null);

        // Pega o dispositivo
        bluetooth.dispositivo = BluetoothAdapter.getDefaultAdapter();

        if (!bluetooth.dispositivo.isEnabled()) {
            bluetooth.dispositivo.enable();
        }

        // Pega a lista de dispositivos pareados
        Set<BluetoothDevice> pairedDevicesList = bluetooth.dispositivo.getBondedDevices();

        // Adiciono na lista e depois retorno a mesma.
        if (pairedDevicesList.size() > 0) {
            for (BluetoothDevice device : pairedDevicesList) {
                bluetooth.lista.add(device);
            }
        }

        return bluetooth;
    }

    public BluetoothListener getListener() {
        return listener;
    }

    public void setListener(BluetoothListener listener) {
        this.listener = listener;
    }

    public ArrayList<BluetoothDevice> getLista() {
        return lista;
    }

    public void setLista(ArrayList<BluetoothDevice> lista) {
        this.lista = lista;
    }

    public BluetoothAdapter getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(BluetoothAdapter dispositivo) {
        this.dispositivo = dispositivo;
    }
}
