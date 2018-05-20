package com.messages;

import Entites.User.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Message implements Serializable {

    private int id;
    private Utilisateur sender;
    private Utilisateur reciver;
    private MessageType type;
    private String msg;
    private int count;
    private Status status;
    private byte[] voiceMsg;
    private ArrayList<Utilisateur> list;
    private ArrayList<Utilisateur> users;
    
    public Message()
    {
        
    }
    public Utilisateur getSender() {
        return sender;
    }
    public void setSender(Utilisateur sender) {
        this.id=sender.getId();
        this.sender = sender;
    }
    public Utilisateur getReciver() {
        return reciver;
    }
    public void setReciver(Utilisateur reciver) {
        this.reciver = reciver;
    }
    public byte[] getVoiceMsg() {
        return voiceMsg;
    }
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }
    public ArrayList<Utilisateur> getUserlist() {
        return list;
    }
    public void setUserlist(HashMap<Integer, Utilisateur> userList) {
        this.list = new ArrayList<>(userList.values());
    }
    public void setOnlineCount(int count){
        this.count = count;
    }
    public int getOnlineCount(){
        return this.count;
    }
    public ArrayList<Utilisateur> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<Utilisateur> users) {
        this.users = users;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }
    public void setVoiceMsg(byte[] voiceMsg) {
        this.voiceMsg = voiceMsg;
    }
}
