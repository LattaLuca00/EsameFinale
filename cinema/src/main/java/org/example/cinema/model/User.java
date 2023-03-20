package org.example.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cinema.enums.UserRole;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    //SE NON SPECIFICO, L'ENUM ALL'INTERNO DEL DATABASE SALVERA SOLO GLI INDICI IN CUI SONO STATI SPECIFICATI
    private UserRole role;
}
