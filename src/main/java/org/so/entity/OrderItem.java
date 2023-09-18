package org.so.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "order_item")
@Table(name = "order_item")
public class OrderItem extends PanacheEntity implements Serializable {
    private Long orderId;

    private Long menuItemId;

    private BigDecimal price;
}
