package org.knf.dev.demo.mobilewebsimulator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "mobile")
@Entity
public class MobileTempStorage implements Serializable {
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

    public MobileTempStorage(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public MobileTempStorage() {
    }

}