package com.challenge.sorting.user;

import com.challenge.entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameAscendingFollowListUserSorter implements FollowListUserSorter {
    public <Element extends User> List<Element> sort(Stream<Element> elements) {
        return elements.sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
    }
}
