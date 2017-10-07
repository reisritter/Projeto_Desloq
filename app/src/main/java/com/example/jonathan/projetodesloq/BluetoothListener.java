package com.example.jonathan.projetodesloq;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
/**
 * Created by Jonathan on 19/06/2016.
 */
public interface BluetoothListener {

    public static final String ACTION_DISCOVERY_STARTED = BluetoothAdapter.ACTION_DISCOVERY_STARTED;
    public static final String ACTION_FOUND = BluetoothDevice.ACTION_FOUND;
    public static final String ACTION_DISCOVERY_FINISHED = BluetoothAdapter.ACTION_DISCOVERY_FINISHED;

    public void action(String action);
}
