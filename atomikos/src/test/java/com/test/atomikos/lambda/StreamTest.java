package com.test.atomikos.lambda;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        List<User> userList = new ArrayList<>();

        for(int i = 0;i < 10;i++){
            Supplier<User> s = User::new;
            User user = s.get();
            user.setAge(SecureRandom.getInstance("SHA1PRNG").nextInt(10) + 1);
            user.setName("name" + i);
            userList.add(user);
        }
        System.out.println(userList);

        List<Integer> ageList = userList.stream()
                                    .filter((user) -> user.getAge() > 5)
                                    .sorted(Comparator.comparing(User::getAge))
                                    .map(User::getAge)
                                    .collect(Collectors.toList());
        System.out.println(ageList);
    }

}
