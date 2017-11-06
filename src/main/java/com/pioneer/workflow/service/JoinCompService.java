package com.pioneer.workflow.service;

import com.pioneer.workflow.bean.Comp;
import com.pioneer.workflow.bean.Person;
import com.pioneer.workflow.dao.CompRepository;
import com.pioneer.workflow.dao.PersonRepository;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JoinCompService {
    protected static Logger logger = LoggerFactory.getLogger(JoinCompService.class);
    @Autowired
    PersonRepository personRepository;

    @Autowired
    private CompRepository compRepository;

    /**
     * 加入公司操作，可从DelegateExecution获取流程中的变量
     *
     * @param execution
     */
    public void joinGroup(DelegateExecution execution) {
        Boolean bool = (Boolean) execution.getVariable("joinApproved");
        if (bool) {
            Long personId = (Long) execution.getVariable("personId");
            Long compId = (Long) execution.getVariable("compId");
            Comp comp = compRepository.findOne(compId);
            Person person = personRepository.findOne(personId);
            person.setComp(comp);
            personRepository.save(person);
            logger.info("加入组织成功");
        } else {
            logger.info("加入组织失败");
        }
    }

    /**
     * TODO 获取符合条件的审批人，演示这里写死，使用应用使用实际代码
     *
     * @param execution 执行人条件设置
     * @return
     */
    public List<String> findUsers(DelegateExecution execution) {
        return Arrays.asList("admin");
    }

}