package com.pioneer.workflow;

import com.pioneer.workflow.bean.Comp;
import com.pioneer.workflow.bean.Person;
import com.pioneer.workflow.dao.CompRepository;
import com.pioneer.workflow.dao.PersonRepository;
import com.pioneer.workflow.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkflowApplication {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompRepository compRepository;

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }


    //初始化模拟数据

    @Bean
    public CommandLineRunner init(final ActivitiService activitiService) {
        return new CommandLineRunner() {
            public void run(String... strings) throws Exception {
                if (personRepository.findAll().size() == 0) {
                    personRepository.save(new Person("wtr"));
                    personRepository.save(new Person("wyf"));
                    personRepository.save(new Person("admin"));
                }

                if (compRepository.findAll().size() == 0) {
                    //新增公司信息
                    Comp comp = new Comp("great company");
                    compRepository.save(comp);

                    Person admin = personRepository.findByPersonName("admin");
                    Person wtr = personRepository.findByPersonName("wtr");
                    admin.setComp(comp);
                    wtr.setComp(comp);

                    personRepository.save(admin);
                    personRepository.save(wtr);
                }
            }
        };
    }


}
