package de.thb.dim.pizzaProntoGUI.customer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerVO {

    private int id;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String lastName;
    private String firstName;
    private String street;
    private int houseNumber;

    public CustomerVO(Gender gender, LocalDate dateOfBirth, String lastName, String firstName, String street, int houseNumber) {
        this(0, gender, dateOfBirth, lastName, firstName, street, houseNumber);
    }

    public CustomerVO(int id){
        setId(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerVO that = (CustomerVO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
