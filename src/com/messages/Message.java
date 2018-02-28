package com.messages;

import Entites.User.Client;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Message implements Serializable {

    private int id;
    private Client sender;
    private Client reciver;
    private MessageType type;
    private String msg;
    private int count;
    private Status status;
    private byte[] voiceMsg;
    private ArrayList<Client> list;
    private ArrayList<Client> users;
    
    public Message()
    {
        
    }
    public Client getSender() {
        return sender;
    }
    public void setSender(Client sender) {
        this.id=sender.getID();
        this.sender = sender;
    }
    public Client getReciver() {
        return reciver;
    }
    public void setReciver(Client reciver) {
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
    public ArrayList<Client> getUserlist() {
        return list;
    }
    public void setUserlist(HashMap<Integer, Client> userList) {
        this.list = new ArrayList<>(userList.values());
    }
    public void setOnlineCount(int count){
        this.count = count;
    }
    public int getOnlineCount(){
        return this.count;
    }
    public ArrayList<Client> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<Client> users) {
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
