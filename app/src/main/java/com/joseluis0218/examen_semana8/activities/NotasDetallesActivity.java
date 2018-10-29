package com.joseluis0218.examen_semana8.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.joseluis0218.examen_semana8.R;
import com.joseluis0218.examen_semana8.models.Notas;
import com.joseluis0218.examen_semana8.repository.RepositorioNotas;

public class NotasDetallesActivity extends AppCompatActivity {
    private static final String TAG = NotasDetallesActivity.class.getSimpleName();
    private Long id;
    private TextView titleText;
    private TextView contentText;
    private CheckBox favoriteStar;
    private CheckBox archivateIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_detalles);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleText = (TextView)findViewById(R.id.title_text);
        contentText = (TextView)findViewById(R.id.content_text);
        favoriteStar = (CheckBox)findViewById(R.id.favorite_star);
        archivateIcon = (CheckBox)findViewById(R.id.archivar_icon);
        id = getIntent().getExtras().getLong("ID");
        Log.e(TAG, "ID: " + id);

        // Get Note by ID from Repository
        Notas note = RepositorioNotas.read(id);

        titleText.setText(note.getTitle());
        contentText.setText(note.getContent());
        if(note.getFavorite()){
            favoriteStar.setVisibility(View.VISIBLE);   // Hidden view
        }else{
            favoriteStar.setVisibility(View.GONE);  // Show view
        }

        if(note.getArchivate()){
            archivateIcon.setVisibility(View.VISIBLE);   // Hidden view
        }else{
            archivateIcon.setVisibility(View.GONE);  // Show view
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    }

