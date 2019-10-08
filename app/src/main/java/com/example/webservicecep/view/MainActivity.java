package com.example.webservicecep.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.webservicecep.R;
import com.example.webservicecep.controller.MainControl;

public class MainActivity extends AppCompatActivity {

    private MainControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control = new MainControl(this);
    }

    public void pesquisarCep(View view) {
        control.pesquisarCepAction();
    }
}
