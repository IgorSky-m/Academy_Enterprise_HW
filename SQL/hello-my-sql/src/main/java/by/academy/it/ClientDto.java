package by.academy.it;

import by.academy.it.interfaces.IClientDto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

//ЗАДАТЬ ВОПРОС ПРО ТО, КАК ЛУЧШЕ СДЕЛАТЬ ЭТОТ КЛАСС (FINAL или имплементировать интерфейс)
public class ClientDto implements Serializable, IClientDto {

    private Integer id;
    private String name;
    private String secondName;
    private String email;
    private Date dateOfBirth;
    private Character gender;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return id.equals(clientDto.id) &&
                Objects.equals(name, clientDto.name) &&
                Objects.equals(secondName, clientDto.secondName) &&
                Objects.equals(email, clientDto.email) &&
                Objects.equals(dateOfBirth, clientDto.dateOfBirth) &&
                Objects.equals(gender, clientDto.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, email, dateOfBirth, gender);
    }
}
