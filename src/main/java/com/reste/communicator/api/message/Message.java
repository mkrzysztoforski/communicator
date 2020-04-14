package com.reste.communicator.api.message;

import com.reste.communicator.api.member.Member;

import java.util.Date;

public class Message {
    private String text;
    private Date date;
    private Member member;

    public Message() {}

    public Message(String text, Member member) {
        this.text = text;
        this.member = member;
        this.date = new Date();
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public String getMember() {
        return member.getNick();
    }
}
