package com.prueba.nisum.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//** Model User
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;

    @Getter
    @Setter
    @Entity
    public static class Phone {
        @Id
        @GeneratedValue
        private UUID id;
        private String number;
        private String citycode;
        private String contrycode;
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withName(String name) {
            user.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder withPhones(List<Phone> phones) {
            user.phones = phones;
            return this;
        }

        public Builder withCreated(LocalDateTime created) {
            user.created = created;
            return this;
        }

        public Builder withModified(LocalDateTime modified) {
            user.modified = modified;
            return this;
        }

        public Builder withLastLogin(LocalDateTime lastLogin) {
            user.lastLogin = lastLogin;
            return this;
        }

        public Builder withToken(String token) {
            user.token = token;
            return this;
        }

        public Builder withIsActive(boolean isActive) {
            user.isActive = isActive;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
