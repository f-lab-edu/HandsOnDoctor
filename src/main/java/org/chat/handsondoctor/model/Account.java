package org.chat.handsondoctor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String userId;
    private String email;
    private String password;
    private String userName;
    private String name;
    private String phoneNumber;
    private Date createdAt;
    private String role;
    private String refreshToken;
    private Date tokenExpiryDate;
}
