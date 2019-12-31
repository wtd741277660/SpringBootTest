package com.test.atomikos.api;

import com.test.atomikos.model.spring.User;
import com.test.atomikos.model.test.People;
import com.test.atomikos.service.PeopleService;
import com.test.atomikos.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User operation api
 * <p/>
 * Created in 2018.11.09
 * <p/>
 * mybatis 双数据源测试
 *
 * @author Liaozihong
 */
@RestController
public class OperationApi {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * The People service.
     */
    @Autowired
    PeopleService peopleService;


    /**
     * 同时操作两个数据源，并且试一个数据源操作失败，测试回滚.
     *
     * @param peopleName the people name
     * @param userName   the user name
     * @return the api result
     * @throws Exception the exception
     */
    @PostMapping(value = "/insertPeopleAndUser", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加两个表", notes = "测试分布式事务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "peopleName", value = "人名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "用户信息", required = true, dataType = "String")
    })
    public void insertPeopleAndUser(String peopleName, String userName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        user.setAge(22);
        People people = new People();
        people.setName(peopleName);
        people.setAge(20);
        people.setSex("男");
        Boolean isSuccess = peopleService.insertUserAndPeople(user, people);
//        if (isSuccess) {
//            return ApiResult.prepare().success("同时添加两表成功!");
//        }
//        return ApiResult.prepare().error(JSONParseUtils.object2JsonString(people), 500, "添加失败，全部回滚");
    }

    @PostMapping(value = "/insertPeopleAndUserV2", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加两个表V2", notes = "测试分布式事务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "peopleName", value = "人名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "用户信息", required = true, dataType = "String")
    })
    public void insertPeopleAndUserV2(String peopleName, String userName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        user.setAge(22);
        People people = new People();
        people.setName(peopleName);
        people.setAge(20);
        people.setSex("男");
        Boolean isSuccess = peopleService.insertUserAndPeopleV2(user, people);
//        if (isSuccess) {
//            return ApiResult.prepare().success("同时添加两表成功!");
//        }
//        return ApiResult.prepare().error(JSONParseUtils.object2JsonString(people), 500, "添加失败，全部回滚");
    }
}
