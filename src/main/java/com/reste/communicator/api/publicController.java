package com.reste.communicator.api;

import com.reste.communicator.api.member.Member;
import com.reste.communicator.api.message.Message;
import com.reste.communicator.api.room.Room;
import com.reste.communicator.api.statement.Statement;
import com.reste.communicator.api.statement.Status;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin // origins = "http://localhost:4200" only for one website!
@RequestMapping("/api/")
public class publicController {
    // todo przy uruchomieniu importować pokoje z pliku
    private ArrayList<Room> rooms = new ArrayList<>();

    @RequestMapping("/create/room")
    public Room createRoom() {
        Room room = new Room();
        rooms.add(room);
        return room;
    }

    @GetMapping("/create/member/{roomKey}/{nick}")
    public Member createMember(@PathVariable String roomKey, @PathVariable String nick) {
        for (Room r : rooms) {
            if (r.getHashKey().equals(roomKey)) {
                if (r.getMembers().size() > 0) {
                    for (Member m : r.getMembers()) {
                        if (m.getNick().equals(nick)) {
                            return null;
                        }
                    }
                    Member member = new Member(nick);
                    r.addMember(member);
                    return member;
                } else {
                    Member member = new Member(nick);
                    r.addMember(member);
                    return member;
                }
            }
        }
        return null;
    }

    @GetMapping("/log/member/{roomKey}/{nickHashId}")
    public Member logMember(@PathVariable String roomKey, @PathVariable String nickHashId) {
        for (Room r : rooms) {
            if (r.getHashKey().equals(roomKey)) {
                for (Member m : r.getMembers()) {
                    if (m.getHashId().equals(nickHashId)) {
                        return m;
                    }
                }
            }
        }
        return null;
    }

    @GetMapping("/send/message/{roomKey}/{nickHashId}/{message}")
    public Statement sendMessage(
            @PathVariable String roomKey,
            @PathVariable String nickHashId,
            @PathVariable String message) {
        for (Room r : rooms) {
            if (r.getHashKey().equals(roomKey)) {
                for (Member m : r.getMembers()) {
                    if (m.getHashId().equals(nickHashId)) {
                        r.addMessage(new Message(message, m));
                        return new Statement(Status.success, "message send success!");
                    }
                }
            }
        }
        return new Statement(Status.failed, "can't send the message!");
    }

    @GetMapping("/stream/{roomKey}/{nickHashId}")
    public ArrayList<Message> streamMessage(
            @PathVariable String roomKey, @PathVariable String nickHashId) {
        for (Room r : rooms) {
            if (r.getHashKey().equals(roomKey)) {
                for (Member m : r.getMembers()) {
                    if (m.getHashId().equals(nickHashId)) {
                        return r.getMessages();
                    }
                }
            }
        }
        return null;
    }

    @GetMapping("/stream/members/{roomKey}/{nickHashId}")
    public ArrayList<String> streamMember(
            @PathVariable String roomKey, @PathVariable String nickHashId) {
        for (Room r : rooms) {
            if (r.getHashKey().equals(roomKey)) {
                for (Member m : r.getMembers()) {
                    if (m.getHashId().equals(nickHashId)) {
                        return r.getMembersNick();
                    }
                }
            }
        }
        return null;
    }
}
