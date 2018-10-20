package com.joseluis0218.examen_semana8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joseluis0218.examen_semana8.models.User;
import com.orm.SugarRecord;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button registrar_button;
    private Button login_button;
    private EditText username_input;
    private EditText password_input;
    String username = "";
    String password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar_button = (Button) findViewById(R.id.registrar_boton);
        login_button = (Button)findViewById(R.id.ingresar_button);
        username_input = (EditText) findViewById(R.id.usuario_input);
        password_input = (EditText) findViewById(R.id.contraseña_input);
        if (this.getIntent().getExtras() != null) {
            username = this.getIntent().getExtras().getString("username");
            username_input.setText(username);

            password = this.getIntent().getExtras().getString("password");
            password_input.setText(password);
        }
        registrar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistroUsuarioActivity.class);
                startActivity(i);
            }
        });

            login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<User> users = SugarRecord.listAll(User.class);
                    username =  username_input.getText().toString();
                    password = password_input.getText().toString();
                    for (User u : users) {
                        if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                            Intent i = new Intent(MainActivity.this, NotasActivity.class);
                            Toast.makeText(MainActivity.this, "Bienvenido usuario : "+username, Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });

        }
    }


