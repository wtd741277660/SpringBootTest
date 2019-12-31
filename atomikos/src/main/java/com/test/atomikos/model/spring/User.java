package com.test.atomikos.model.spring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;

    private Integer age;

    private String userName;

}