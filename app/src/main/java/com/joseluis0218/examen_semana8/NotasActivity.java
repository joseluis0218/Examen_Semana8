package com.joseluis0218.examen_semana8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.joseluis0218.examen_semana8.models.Notas;

import java.util.List;

public class NotasActivity extends AppCompatActivity {
    private static final String TAG = NotasActivity.class.getSimpleName();

    private static final int REGISTER_FORM_REQUEST = 100;

    private RecyclerView notesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        this.notesList = (RecyclerView)findViewById(R.id.notes_list);
        this.notesList.setLayoutManager(new LinearLayoutManager(NotasActivity.this));
        this.notesList.setAdapter(new NotasAdaptador());

        refreshData();
    }

    public void refreshData(){
        NotasAdaptador adapter = (NotasAdaptador) notesList.getAdapter();
        List<Notas> notes = RepositorioNotas.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged(); // Refrezca los cambios en el RV
    }

    public void showRegister(View view){
        startActivityForResult(new Intent(NotasActivity.this, RegistroNotasActivity.class), REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REGISTER_FORM_REQUEST:
                if (resultCode == RESULT_OK) {
                    refreshData();
                }
                break;
            default:
        }

    }

}

