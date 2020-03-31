package com.test.atomikos.config;

import com.test.atomikos.service.PeopleService;
import com.test.atomikos.service.UserService;
import com.test.atomikos.service.impl.PeopleServiceImpl;
import com.test.atomikos.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration
 * <p/>
 * Created in 2018.11.09
 * <p/>
 *
 * @author Liaozihong
 */
@Configuration
public class SpringConfiguration {
    /**
     * User service user service.
     *
     * @return the user service
     */
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    /**
     * People service people service.
     *
     * @return the people service
     */
    @Bean
    public PeopleService peopleService() {
        return new PeopleServiceImpl();
    }
}

