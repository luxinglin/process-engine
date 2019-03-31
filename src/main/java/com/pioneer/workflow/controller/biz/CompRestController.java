package com.pioneer.workflow.controller.biz;

import com.pioneer.workflow.bean.Comp;
import com.pioneer.workflow.service.CompService;
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
public class CompRestController {
    @Autowired
    private CompService compService;

    /**
     * @return List <TUser> 返回类型
     * @Title: list
     * @Description:全部公司查询
     */
    @ApiOperation(value = "全部公司查询", notes = "全部公司查询")
    @RequestMapping(value = "/comp", method = RequestMethod.GET)
    public ResponseEntity<List<Comp>> list(HttpServletResponse response) {
        List<Comp> userList = compService.listComp();
        return new ResponseEntity<List<Comp>>(userList, HttpStatus.OK);
    }

    /**
     * @param id
     * @param response
     * @return ResponseEntity<TUser> 返回类型
     * @Title: getUser
     * @Description: 查询单个公司
     */
    @ApiOperation(value = "查询单个公司", notes = "查询单个公司")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "公司id", defaultValue = "")
    })
    @RequestMapping(value = "/comp/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comp> getComp(@PathVariable("id") Long id, HttpServletResponse response) {
        Comp comp = compService.findById(id);
        if (comp == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Comp>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comp>(comp, HttpStatus.OK);
    }

    /**
     * @param comp
     * @param ucBuilder
     * @param response
     * @return ResponseEntity<Void> 返回类型
     * @Title: createUser
     * @Description: 新增公司
     */
    @ApiOperation(value = "新增公司", notes = "新增公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comp", value = "公司详细实体", required = true, dataType = "Comp")
    })
    @RequestMapping(value = "/comp", method = RequestMethod.POST)
    public ResponseEntity<Void> createComp(@RequestBody Comp comp, UriComponentsBuilder ucBuilder,
                                           HttpServletResponse response) {
        compService.saveComp(comp);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/comp/{id}").buildAndExpand(comp.getCompId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * @param id 公司id
     * @return ResponseEntity<TUser> 返回类型
     * @Title: deleteUser
     * @Description: 删除单个公司
     */
    @ApiOperation(value = "删除单个公司", notes = "删除单个公司")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "公司id", defaultValue = "")
    })
    @RequestMapping(value = "/comp/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Comp> deleteUser(@PathVariable("id") Long id, HttpServletResponse response) {

        compService.deleteById(id);
        return new ResponseEntity<Comp>(HttpStatus.NO_CONTENT);
    }
}
