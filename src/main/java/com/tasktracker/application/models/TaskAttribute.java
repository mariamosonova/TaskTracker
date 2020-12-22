package com.tasktracker.application.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "attribute")
public class TaskAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attribute_id;

    @Column(name = "value")
    private String value;

    @NotBlank
    @Column(name = "taskId")
    private String taskId;

    @OneToOne
    @JoinColumn(name="id")
    private AttributeType type;

    public TaskAttribute() {}

    public TaskAttribute(String value, String taskId, AttributeType type) {
        this.value = value;
        this.taskId = taskId;
        this.type = type;

    }

    public Long getAttributeId() {
        return attribute_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public String getTaskId() {
        return taskId;
    }
}
