package by.academy.it.interfaces;

import java.sql.Date;

public interface IClientDto {

    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    String getSecondName();

    void setSecondName(String secondName);

    String getEmail();

    void setEmail(String email);

    Date getDateOfBirth();

    void setDateOfBirth(Date dateOfBirth);

    Character getGender();

    void setGender(Character gender);
}
