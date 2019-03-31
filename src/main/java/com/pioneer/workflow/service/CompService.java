package com.pioneer.workflow.service;

import com.pioneer.workflow.bean.Comp;
import com.pioneer.workflow.dao.CompRepository;
import com.pioneer.workflow.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompService {
    @Autowired
    CompRepository compRepository;
    @Autowired
    PersonRepository personRepository;

    public List<Comp> listComp() {
        List<Comp> list = this.compRepository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        this.compRepository.delete(id);
    }

    public void saveComp(Comp comp) {
        this.compRepository.save(comp);
    }

    public Comp findById(Long id) {
        Comp comp = this.compRepository.findOne(id);
        return comp;
    }
}
