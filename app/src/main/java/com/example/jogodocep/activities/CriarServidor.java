package com.example.jogodocep.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jogodocep.NotificacaoConexao;
import com.example.jogodocep.R;
import com.example.jogodocep.model.Jogador;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CriarServidor extends AppCompatActivity {
    TextView tvStatus;
    RadioGroup radioGroup;
    RadioButton radioButton, rbEloi, rbMorlock;
    Button btCriarServer;
    ServerSocket welcomeSocket;
    DataOutputStream socketOutput;
    BufferedReader socketEntrada;
    DataInputStream fromClient;
    boolean continuarRodando = false;
    String ipDevice;
    int ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_servidor);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btCriarServer = (Button) findViewById(R.id.btCriarServer);
        NotificacaoConexao nc = new NotificacaoConexao();
    }


    public void onClickCriarServer(View v) {
        if (estaWifiConectado()) {
            int selectedId = radioGroup.getCheckedRadioButtonId();//pega o id do Radio Button selecionado
            radioButton = (RadioButton) findViewById(selectedId);
            if (radioButton != null) {
                if (radioButton.getText() == "Morlock") {

                } else {

                }
            } else {
                Toast.makeText(this, "Selecione uma raça", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean estaWifiConectado() {
        ConnectivityManager connManager;//classe para pegar informações de conectividade
        connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);//retorna um Connectivity Manager
        Network[] networks = connManager.getAllNetworks();//
        NetworkInfo networkInfo;//classe para pegar informações da rede
        boolean temConexao = false;//<------------------------------------------------------------------------
        for (Network myNetwork : networks) {//percorrer esse vetor
            networkInfo = connManager.getNetworkInfo(myNetwork);
            //Método depreciado, na versão mais nova se sugere usar callback para isso, mas por enquanto é suficiente
            //vai entrar neste if quando uma conexão está conectada
            if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                NetworkCapabilities networkFeatures;//maneira mais correta de pegar informações sobre a capacidade da rede
                //a partir de uma rede consegue-se saber essa informação
                networkFeatures = connManager.getNetworkCapabilities(myNetwork);
                //só vai entrar neste if somente se esta rede conectada tem capacidade de Wi-Fi
                if (networkFeatures.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    temConexao = true;//<---------------------------------------------------------------------
                    Log.v("PDM", "Tem acesso a WI-FI");
                    //Vamos ouvir mudanças nessa conexão
                    NetworkRequest.Builder builder = new NetworkRequest.Builder();
                    builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
                    NotificacaoConexao nfc = new NotificacaoConexao();
                    connManager.registerNetworkCallback(builder.build(), nfc);
                }
            }
        }
        return temConexao;//<--------------------------------------------------------------------------------
    }


    public void ligarServidor() {
        if (estaWifiConectado()) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            String macAddress = wifiManager.getConnectionInfo().getMacAddress();
            ip = wifiManager.getConnectionInfo().getIpAddress();
            String ipAddress = String.format("%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
            Log.v("PDM", "IP: " + ipAddress);
            Log.v("PDM", "MAC: " + macAddress);
            ipDevice = ipAddress;
            tvStatus.setText("Ativo em: " + ipAddress);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    ligarServerCodigo();
                }
            });
            t.start();
        }
    }


    public void ligarServerCodigo() {
        //Desabilitar o Botão de Ligar
        btCriarServer.post(new Runnable() {
            @Override
            public void run() {
                btCriarServer.setEnabled(false);
            }
        });

        String result = "";
        try {
            Log.v("PDM", "Ligando o Server");
            welcomeSocket = new ServerSocket(9090);
            Socket connectionSocket = welcomeSocket.accept();
            Log.v("PDM", "Nova conexão");

            //Instanciando os canais de stream
            fromClient = new DataInputStream(connectionSocket.getInputStream());
            socketOutput = new DataOutputStream(connectionSocket.getOutputStream());
            continuarRodando = true;
            while (continuarRodando) {
                result = fromClient.readUTF();
                if (result.compareTo("PING") == 0) {
                    //enviar Pong
                    //pongs++;
                    socketOutput.writeUTF("PONG");
                    socketOutput.flush();
                    //atualizarStatus();
                }
            }

            Log.v("PDM", result);
            //Enviando dados para o servidor
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}