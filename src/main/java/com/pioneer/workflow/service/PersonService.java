package com.pioneer.workflow.service;

import com.pioneer.workflow.bean.Person;
import com.pioneer.workflow.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {
    @Autowired
    PersonRepository personRepository;


    public List<Person> listPerson() {
        List<Person>  list = personRepository.findAll();
        for(Person person : list){
            person.setComp(null);
        }
        return list;
    }

    public Person findById(Long id) {
        return personRepository.findOne(id);
    }

    public void savePerson(Person user) {
        this.personRepository.save(user);
    }

    public void deleteById(Long id) {
        this.personRepository.delete(id);
    }
}
