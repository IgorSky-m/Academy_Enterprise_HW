package by.academy.dto;

import by.academy.exceptions.NullDtoParameterException;


import java.io.Serializable;
import java.util.Objects;

public class ReceiversDto implements Serializable {
    Integer id;
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) throw new NullDtoParameterException();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new NullDtoParameterException();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiversDto that = (ReceiversDto) o;
        return id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
