package org.knf.dev.demo.backend.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "backend")
@Entity

public class BackEndTempStorage implements Serializable {
    @Id
    private String key;

    @Column(length=2500)
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BackEndTempStorage(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public BackEndTempStorage() {
    }

}