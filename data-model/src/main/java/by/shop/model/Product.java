package by.shop.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
//обьекты и связи
//log4j подключить
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid2")
    String id;
    String name;

    @Column(name = "product_number")
    String productNumber;
    @Column(name = "serial_number")
    String serialNumber;
    @Column(name = "produce_date")
    Date produceDate;
    @Column(name = "description")
    String description;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Stock stock;

    @ManyToOne
    @JoinColumn(name = "section_id")
    Section section;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(productNumber, product.productNumber) &&
                Objects.equals(serialNumber, product.serialNumber) &&
                Objects.equals(produceDate, product.produceDate) &&
                Objects.equals(description, product.description);
    }




    @Override
    public int hashCode() {
        return Objects.hash(id, name, productNumber, serialNumber, produceDate, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", produceDate=" + produceDate +
                ", description='" + description + '\'' +
                '}';
    }
}
