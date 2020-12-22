package com.tasktracker.application.models;

import javax.persistence.*;

@Entity
@Table(name = "attribute_types")
public class AttributeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EAttributeType name;

    public AttributeType() {}

    public AttributeType(EAttributeType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EAttributeType getName() {
        return name;
    }

    public void setName(EAttributeType name) {
        this.name = name;
    }
}
