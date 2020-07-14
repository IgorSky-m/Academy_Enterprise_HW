package by.academy.dto;

import by.academy.exceptions.NullDtoParameterException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Objects;


public class ExpensesDto implements Serializable {
    private static final int DEFAULT_SCALE_ROUND =3;
    private Integer id;
    private Date date;
    private Integer receiver;
    private BigDecimal value;

    public ExpensesDto(){

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(id == null) throw new NullDtoParameterException();
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) throw new NullDtoParameterException();
        this.date = date;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        if (receiver == null) throw new NullDtoParameterException();
        this.receiver = receiver;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Double getDoubleValue(){
        return value.doubleValue();
    }



    public void setValue(Double value){
        if (value == null) throw new NullDtoParameterException();
        this.value = new BigDecimal(value).setScale(DEFAULT_SCALE_ROUND,RoundingMode.HALF_EVEN);
    }

    public void setValue(BigDecimal value) {
        if (value == null) throw new NullDtoParameterException();
        this.value = value.setScale(DEFAULT_SCALE_ROUND,RoundingMode.HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpensesDto that = (ExpensesDto) o;
        return id.equals(that.id) &&
                date.equals(that.date) &&
                receiver.equals(that.receiver) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, receiver, value);
    }
}
