package by.shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "section_id")
    String id;

    @Column(name = "section_name")
    String sectionName;

    @OneToMany(mappedBy = "section")
    Set<Product> products;
}
