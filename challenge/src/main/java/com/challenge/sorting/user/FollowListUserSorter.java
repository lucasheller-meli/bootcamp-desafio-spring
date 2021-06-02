package com.challenge.sorting.user;

import com.challenge.entities.User;

import java.util.List;
import java.util.stream.Stream;

public interface FollowListUserSorter {
    List<User> sort(Stream<User> users);
}
