package com.authApp.service;

import com.mashape.unirest.http.Unirest;

public class NoteService {
    public String getNotes(){
        try {
            return Unirest.get("https://" + System.getenv("RHOST") + "/note")
                    .asString().getBody().toString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void addNote(String note){
        try {
            Unirest.post("https://" + System.getenv("RHOST") + "/note")
                    .header("Content-Type", "application/json")
                    .body(note).asString().getBody().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
