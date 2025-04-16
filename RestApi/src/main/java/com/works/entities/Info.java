package com.works.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private String username;
    private String roles;
    private String sessionID;
    private String url;
    private Long time;
    private String agent;
    private String ip;

    public Info(String username, String roles, String sessionID, String url, Long time, String agent, String ip) {
        this.username = username;
        this.roles = roles;
        this.sessionID = sessionID;
        this.url = url;
        this.time = time;
        this.agent = agent;
        this.ip = ip;
    }
}
