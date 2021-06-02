package com.challenge.sorting.user;

public class FollowListUserSorterFactory {
    private static final String NAME_DESCENDING = "name_desc";

    public static FollowListUserSorter create(String order) {
        if (NAME_DESCENDING.equals(order)) {
            return new NameDescendingFollowListUserSorter();
        }
        return new NameAscendingFollowListUserSorter();
    }
}
