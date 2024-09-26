package org.eventticket.eventticketapp.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin {
    @Id
    private int id;
    private String username;
    private String password;
}
