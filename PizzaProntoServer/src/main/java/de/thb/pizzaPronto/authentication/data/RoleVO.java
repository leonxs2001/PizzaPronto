package de.thb.pizzaPronto.authentication.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO {
    private static int nextId = 1;
    private Integer id;
    private String name;

    public RoleVO(String name){
        setId(nextId++);
        this.setName(name);
    }
}
