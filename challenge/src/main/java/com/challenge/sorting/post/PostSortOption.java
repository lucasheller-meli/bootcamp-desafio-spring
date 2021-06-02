package com.challenge.sorting.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostSortOption {
    DATE_ASCENDING("date_asc"),
    DATE_DESCENDING("date_desc");

    private final String option;
}
