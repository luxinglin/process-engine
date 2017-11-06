package com.pioneer.workflow.controller.process;

import com.pioneer.workflow.service.ActivitiService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProcessRestController {

    @Autowired
    private ActivitiService activitiService;

    /**
     * 启动用户所属公司流程
     *
     * @param personId
     * @param compId
     */
    @ApiOperation(value = "启动用户所属公司流程", notes = "启动用户所属公司流程")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "personId", dataType = "long", required = true, value = "员工ID", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "compId", dataType = "long", required = true, value = "公司ID", defaultValue = "")
    })
    @RequestMapping(value = "/process/{personId}/{compId}", method = RequestMethod.GET)
    public void startProcessInstance(@PathVariable Long personId, @PathVariable Long compId) {
        activitiService.startProcess(personId, compId);
    }

    /**
     * 获取指定人员的待办任务
     * @param assignee
     * @return
     */
    @ApiOperation(value = "获取指定人员的待办任务", notes = "获取指定人员的待办任务")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "assignee", dataType = "string", required = true, value = "审核人员", defaultValue = "")
    })
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = activitiService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }


    /**
     * 审核用户所属公司变更
     *
     * @param joinApproved true为通过， false为拒绝
     * @param taskId       任务实例
     * @return
     */
    @ApiOperation(value = "审核用户所属公司变更", notes = "审核用户所属公司变更")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "joinApproved", dataType = "boolean", required = true, value = "审核结果", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "taskId", dataType = "long", required = true, value = "任务ID", defaultValue = "")
    })
    @RequestMapping(value = "/complete/{joinApproved}/{taskId}", method = RequestMethod.GET)
    public String complete(@PathVariable Boolean joinApproved, @PathVariable String taskId) {
        activitiService.completeTasks(joinApproved, taskId);
        return "ok";
    }

    //Task的dto
    static class TaskRepresentation {
        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}