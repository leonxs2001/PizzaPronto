package de.thb.dim.pizzaProntoView.customer.rest;

import de.thb.dim.pizzaProntoView.exception.CustomerNoDateOfBirthException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
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

    public CustomerVO(int id, Gender gender, String lastName, String firstName, String street, int houseNumber) {
        this(id, gender, null, lastName, firstName, street, houseNumber);
    }


    public CustomerVO(int id) {
        setId(id);
    }

    /**
     * the age of customer is a drived attribute, i.e. age is only calculated
     * in the method and is not a instance variable
     *
     * @return age - short
     * @throws CustomerNoDateOfBirthException
     */
    public short calculateAge() throws CustomerNoDateOfBirthException {
        short alter = -1;
        Period age;
        LocalDate today = LocalDate.now();

        if (dateOfBirth == null) {
            throw new CustomerNoDateOfBirthException("Internal error: No date of birth.");
        }

        if (dateOfBirth != null) {
            age = Period.between(dateOfBirth, today);
            alter = (short) age.getYears();
        }
        return alter;
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

    @Override
    public String toString() {
        return "CustomerVO{" +
                "id=" + id +
                ", gender=" + gender +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}
