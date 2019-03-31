package com.pioneer.workflow.dao;

import com.pioneer.workflow.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


    Person findByPersonName(String personName);

}