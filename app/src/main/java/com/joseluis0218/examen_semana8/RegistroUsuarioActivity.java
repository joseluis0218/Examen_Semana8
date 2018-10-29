package com.joseluis0218.examen_semana8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.joseluis0218.examen_semana8.activities.MainActivity;
import com.joseluis0218.examen_semana8.repository.RepositorioUsuario;

public class RegistroUsuarioActivity extends AppCompatActivity {
    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText usernameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        fullnameInput = (EditText)findViewById(R.id.fullname);
        emailInput = (EditText)findViewById(R.id.email);
        usernameInput = (EditText)findViewById(R.id.username);
        passwordInput = (EditText)findViewById(R.id.password);
    }
    public void callRegister(View view){
        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(fullname.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        RepositorioUsuario.create(fullname, email,username, password);

        finish();

        Intent intent = new Intent(RegistroUsuarioActivity.this,MainActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        startActivity(intent);

    }

}
