package com.challenge.sorting.user;

import com.challenge.entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameAscendingFollowListUserSorter implements FollowListUserSorter {
    public List<User> sort(Stream<User> users) {
        return users.sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
    }
}
