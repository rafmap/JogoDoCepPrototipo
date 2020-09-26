package com.example.jogodocep.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jogodocep.R;
import com.example.jogodocep.activities.ConectarCliente;

public class MainActivity extends AppCompatActivity {
    Button btJogar, btSessao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btJogar = (Button) findViewById(R.id.btJogar);
        btSessao = (Button) findViewById(R.id.btSessao);
    }
    public void onClickJogar(View v){
        Intent intent = new Intent(this, ConectarCliente.class);
        startActivity(intent);
    }
    public void onClickSessao(View v){
        Intent intent = new Intent(this, CriarServidor.class);
        startActivity(intent);
    }
}