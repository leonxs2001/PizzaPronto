package de.thb.pizzaPronto.authentication.data;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO implements IUserDAO{
    private List<UserVO> users;

    public UserDAO(){
        users = new ArrayList<>();
    }

    @Override
    public void saveUser(UserVO user) {
        if(user == null){
            throw new NullPointerException("User should not be null.");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        int appearanceIndex = users.indexOf(user);
        if(appearanceIndex != -1){
            this.users.set(appearanceIndex, user);
        }else{
            users.add(user);
        }
    }

    @Override
    public UserVO getUserByUsername(String username) throws UsernameNotFoundException {
        for(UserVO user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }

        throw new UsernameNotFoundException("There is no User with the given username");
    }

    @Override
    public UserVO getUserById(int id) throws IdNotFoundException {
        for(UserVO user : users){
            if(user.getId() == id){
                return user;
            }
        }

        throw new IdNotFoundException("There is no User with the given username");
    }
}
