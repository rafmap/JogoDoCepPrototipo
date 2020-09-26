package com.example.jogodocep;

import android.net.ConnectivityManager;
import android.net.Network;
import android.util.Log;

public class NotificacaoConexao extends ConnectivityManager.NetworkCallback {
    //Um Call Back para saber se houve ou não mudanças no estado da conexão
    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        Log.v("PDM", "Wi-Fi está conectado");
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        Log.i("PDM", "Desligaram o Wi-Fi");
    }
}
