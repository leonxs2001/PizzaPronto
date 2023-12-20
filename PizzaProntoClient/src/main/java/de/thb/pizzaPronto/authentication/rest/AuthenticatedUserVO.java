package de.thb.pizzaPronto.authentication.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserVO {

    private String token;

    private String username;

    private Role role;

    public AuthenticatedUserVO(UserVO user, String token){
        this.setToken(token);
        this.setUsername(user.getUsername());
        this.setRole(user.getRole());
    }

}