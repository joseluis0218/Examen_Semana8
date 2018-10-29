package com.joseluis0218.examen_semana8.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.joseluis0218.examen_semana8.R;
import com.joseluis0218.examen_semana8.adapters.NotasAdaptador;
import com.joseluis0218.examen_semana8.fragments.FirstFragment;
import com.joseluis0218.examen_semana8.fragments.SecondFragment;
import com.joseluis0218.examen_semana8.fragments.ThirdFragment;
import com.joseluis0218.examen_semana8.models.Notas;
import com.joseluis0218.examen_semana8.repository.RepositorioNotas;

import java.util.List;

public class NotasActivity extends AppCompatActivity {
    private static final String TAG = NotasActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;

    private RecyclerView notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        this.notesList = (RecyclerView) findViewById(R.id.notes_list);
        this.notesList.setLayoutManager(new LinearLayoutManager(NotasActivity.this));
        List<Notas> notes = RepositorioNotas.list();
        this.notesList.setAdapter(new NotasAdaptador(notes));

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        showFirstFragment(bottomNavigationView);
                        break;
                    case R.id.menu_favoritos:
                        showSecondFragment(bottomNavigationView);
                        break;
                    case R.id.menu_archivados:
                        showThirdFragment(bottomNavigationView);
                        break;
                }
                return true;
            }
        });
        refreshData();
    }

    public void refreshData() {
        NotasAdaptador adapter = (NotasAdaptador) notesList.getAdapter();
        List<Notas> notes = RepositorioNotas.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged(); // Refrezca los cambios en el RV
    }

    public void showRegister(View view) {
        startActivityForResult(new Intent(NotasActivity.this, RegistroNotasActivity.class), REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        NotasAdaptador adapter = (NotasAdaptador) notesList.getAdapter();

        List<Notas> notes = RepositorioNotas.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();

    }


    public void showFirstFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new FirstFragment();
        fragmentManager.beginTransaction().replace(R.id.content,
                fragment).addToBackStack("tag").commit();
    }

    public void showSecondFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new SecondFragment();
        fragmentManager.beginTransaction().replace(R.id.content,
                fragment).addToBackStack("tag").commit();
    }

    public void showThirdFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new ThirdFragment();
        fragmentManager.beginTransaction().replace(R.id.content,
                fragment).addToBackStack("tag").commit();
    }
}



