package com.test.atomikos;

import com.test.atomikos.model.spring.User;
import com.test.atomikos.model.test.People;
import com.test.atomikos.service.PeopleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
class AtomikosApplicationTests {

    @Autowired
    PeopleService peopleService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUserName("userName");
        user.setAge(22);
        People people = new People();
        people.setName("peopleName");
        people.setAge(20);
        people.setSex("男");
        Boolean isSuccess = peopleService.insertUserAndPeopleV2(user, people);
    }

}
