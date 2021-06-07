package com.challenge.sorting.user;

import com.challenge.entities.User;

import java.util.List;
import java.util.stream.Stream;

public interface FollowListUserSorter {
    <E extends User> List<E> sort(Stream<E> elements);
}
