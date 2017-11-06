package com.pioneer.workflow.controller.biz;

import com.pioneer.workflow.bean.Person;
import com.pioneer.workflow.service.PersonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PersonRestController {
    @Autowired
    private PersonService personService;

    /**
     * @return List <TUser> 返回类型
     * @Title: list
     * @Description:全部用户查询
     */
    @ApiOperation(value = "全部用户查询", notes = "全部用户查询")
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> list(HttpServletResponse response) {
        List<Person> userList = personService.listPerson();
        return new ResponseEntity<List<Person>>(userList, HttpStatus.OK);
    }

    /**
     * @param id
     * @param response
     * @return ResponseEntity<TUser> 返回类型
     * @Title: getUser
     * @Description: 查询单个用户
     */
    @ApiOperation(value = "查询单个用户", notes = "查询单个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "用户id", defaultValue = "")
    })
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getUser(@PathVariable("id") Long id, HttpServletResponse response) {
        Person user = personService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(user, HttpStatus.OK);
    }

    /**
     * @param user
     * @param ucBuilder
     * @param response
     * @return ResponseEntity<Void> 返回类型
     * @Title: createUser
     * @Description: 新增用户
     */
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "Person")
    })
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Person user, UriComponentsBuilder ucBuilder,
                                           HttpServletResponse response) {
        personService.savePerson(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/person/{id}").buildAndExpand(user.getPersonId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * @param id 用户id
     * @return ResponseEntity<TUser> 返回类型
     * @Title: deleteUser
     * @Description: 删除单个用户
     */
    @ApiOperation(value = "删除单个用户", notes = "删除单个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "用户id", defaultValue = "")
    })
    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteUser(@PathVariable("id") Long id, HttpServletResponse response) {

        personService.deleteById(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
}
