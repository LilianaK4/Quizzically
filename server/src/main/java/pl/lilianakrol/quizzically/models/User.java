package pl.lilianakrol.quizzically.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;


@Data
@Entity
@Table(name = "users")
public
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;


    @Column(name = "username", nullable = false)
    private String username;


    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    private Instant created;

    private boolean enabled;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getCreated() {
        return created;
    }


}
