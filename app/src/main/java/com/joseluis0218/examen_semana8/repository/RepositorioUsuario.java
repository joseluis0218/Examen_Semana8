package com.joseluis0218.examen_semana8.repository;

import com.joseluis0218.examen_semana8.models.User;
import com.orm.SugarRecord;

public class RepositorioUsuario {


    public static void create(String fullname, String email,String username, String password){
        User user = new User(fullname, email, username, password);
        SugarRecord.save(user);
    }
}
