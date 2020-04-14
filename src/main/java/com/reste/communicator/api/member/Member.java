package com.reste.communicator.api.member;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Member {
    private String nick;
    private int id;
    private static int idd;
    private String hashId;

    public Member() {}

    public Member(String nick) {
        this.id = idd;
        idd++;
        this.nick = nick;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String input = this.nick + this.id;
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashText = no.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            this.hashId = hashText;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }

    protected int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getHashId() {
        return hashId;
    }
}
