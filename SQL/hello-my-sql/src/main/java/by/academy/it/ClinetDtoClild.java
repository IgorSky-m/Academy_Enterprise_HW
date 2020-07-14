package by.academy.it;

import java.sql.Date;

public class ClinetDtoClild extends ClientDto {
    private Integer id;
    private String name;
    private String secondName;
    private String email;
    private Date dateOfBirth;
    private Character gender;

    public ClinetDtoClild(){
        this.id = 5;
        this.name="igo";
    }

    public Integer getId(){
        return super.getId();
    }


}
