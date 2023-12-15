package de.thb.dim.pizzaProntoGUI.authentication.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Integer id;

    private String username;

    private String password;

    private Role role;


    public UserVO(String username, String password, Role role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public UserVO(String username, String password) {
        this(username, password, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO user = (UserVO) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
