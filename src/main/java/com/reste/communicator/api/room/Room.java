package com.reste.communicator.api.room;

import com.reste.communicator.api.member.Member;
import com.reste.communicator.api.message.Message;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

public class Room {
    private int id;
    private static int idd = 0;
    private String hashKey;
    private Date created;
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();

    public Room() {
        this.id = idd;
        idd++;
        this.created = new Date();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String input = "room" + this.id;
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashText = no.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            this.hashKey = hashText;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }

    protected int getId() {
        return id;
    }

    public String getHashKey() {
        return hashKey;
    }

    public Date getCreated() {
        return created;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<String> getMembersNick() {
        ArrayList<String> members = new ArrayList<>();
        for (Member member : this.members) {
            members.add(member.getNick());
        }

        return members;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
