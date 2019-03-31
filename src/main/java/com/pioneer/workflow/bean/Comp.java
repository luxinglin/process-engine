package com.pioneer.workflow.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comp implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long compId;
    private String compName;

    @OneToMany(mappedBy = "comp", targetEntity = Person.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Person> people;

    public Comp(String compName) {
        this.compName = compName;
    }

    public Comp() {

    }

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}