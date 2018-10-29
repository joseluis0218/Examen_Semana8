package com.joseluis0218.examen_semana8.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joseluis0218.examen_semana8.R;
import com.joseluis0218.examen_semana8.adapters.NotasAdaptador;
import com.joseluis0218.examen_semana8.models.Notas;
import com.joseluis0218.examen_semana8.repository.RepositorioNotas;

import java.util.List;

public class SecondFragment extends Fragment {

    private static final String TAG = FirstFragment.class.getSimpleName();

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        notesList.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Notas> notes = RepositorioNotas.listFavorite();

        notesList.setAdapter(new NotasAdaptador(notes));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
