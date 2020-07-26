package by.shop.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Stock implements Serializable {
    @Id
    @GenericGenerator(
            name = "gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property",value = "product")
    )
    @GeneratedValue(generator = "gen")
    String id;

    @Column(name ="value")
    int value = 0;

    @OneToOne
    @PrimaryKeyJoinColumn
    Product product;

}
