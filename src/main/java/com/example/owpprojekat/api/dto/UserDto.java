package com.example.owpprojekat.api.dto;

import com.example.owpprojekat.api.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class UserDto {

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private String username;
        private String email;
        private String name;
        private String lastname;
        private Date dateOfBirth;
        private String address;
        private String phoneNum;
        private LocalDateTime regDateTime;
        private Role role;

        public Get(Long id, String username, String email, String name, String lastname, Date dateOfBirth, String address, String phoneNum, LocalDateTime regDateTime, Role role) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.name = name;
            this.lastname = lastname;
            this.dateOfBirth = dateOfBirth;
            this.address = address;
            this.phoneNum = phoneNum;
            this.regDateTime = regDateTime;
            this.role = role;
        }

        public Get() {
        }
    }

    @Getter
    @Setter
    public static class Add {
        private String username;
        private String password;
        private String email;
        private String name;
        private String lastname;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date dateOfBirth;
        private String address;
        private String phoneNum;

    }

    @Getter
    @Setter
    public static class Login {
        private String username;
        private String password;
    }
}
