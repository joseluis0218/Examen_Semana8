package com.joseluis0218.examen_semana8;

import com.joseluis0218.examen_semana8.models.Notas;
import com.joseluis0218.examen_semana8.models.User;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepositorioNotas {

    private static List<Notas> notes = new ArrayList<>();
    public static List<Notas> list(){
        notes = SugarRecord.listAll(Notas.class);
        return notes;
    }

    public static void create(String title, String content, Date date, Boolean state){
        Notas notas = new Notas(title, content, date, state);
        SugarRecord.save(notas);
    }

    public static void delete(Long id){
        Notas notas = SugarRecord.findById(Notas.class, id);
        SugarRecord.delete(notas);
    }
    public static Notas get(Long id){
        for (Notas note : notes) {
            if(note.getId().equals(id))
                return note;
        }
        return null;
    }
}
