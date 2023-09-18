package org.so.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "menu")
@Table(name = "menu")
public class Menu extends PanacheEntity implements Serializable {

    private Long restaurantId;

    private Boolean active;

    @Transient
    private List<MenuItem> menuItems;
}
