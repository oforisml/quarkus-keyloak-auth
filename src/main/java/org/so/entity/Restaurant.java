package org.so.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "restaurant")
@Table(name = "restaurant")
public class Restaurant extends PanacheEntity implements Serializable {
    private String name;
    private String location;

    @Column(name = "type_name")
    private String type;
}
