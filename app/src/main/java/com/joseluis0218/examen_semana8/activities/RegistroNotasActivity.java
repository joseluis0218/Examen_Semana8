package com.joseluis0218.examen_semana8.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.joseluis0218.examen_semana8.R;
import com.joseluis0218.examen_semana8.activities.MainActivity;
import com.joseluis0218.examen_semana8.repository.RepositorioNotas;

import java.util.Date;

public class RegistroNotasActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText titleInput;

    private EditText contentInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_notas);
        titleInput = (EditText)findViewById(R.id.title_input);
        contentInput = (EditText)findViewById(R.id.content_input);
    }


    public void callRegister(View view){
        String titulo = titleInput.getText().toString();
        String content = contentInput.getText().toString();
        Date date = new Date();
        if(titulo.isEmpty() || content.isEmpty()){
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        RepositorioNotas.create(titulo,content,date,false,false);
        Toast.makeText(this, "Nota registrada correctamente", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();


    }

}