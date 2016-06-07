package com.waghih.iiumchatroom;

/**
 * Created by farooq on 27/2/2016.
 */
public class Chat {
    private String message;
    private String author;

    private Chat(){}

    Chat(String message, String author){
        this.message = message;
        this.author = author;
    }

    public String getMessage(){
        return message;
    }

    public String getAuthor(){
        return author;
    }
}
