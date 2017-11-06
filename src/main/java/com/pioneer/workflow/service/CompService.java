package com.pioneer.workflow.service;

import com.pioneer.workflow.bean.Comp;
import com.pioneer.workflow.dao.CompRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompService {
    @Autowired
    CompRepository compRepository;

    public List<Comp> listComp() {
        List<Comp> list = this.compRepository.findAll();
        for (Comp comp : list) {
            comp.setPeople(null);
        }
        return list;
    }

    public void deleteById(Long id) {
        this.compRepository.delete(id);
    }

    public void saveComp(Comp comp) {
        this.compRepository.save(comp);
    }

    public Comp findById(Long id) {
        return this.compRepository.findOne(id);
    }
}
