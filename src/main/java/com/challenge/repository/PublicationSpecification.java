package com.challenge.repository;

import com.challenge.entity.Publication;
import com.challenge.entity.UserRetailer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;


@AllArgsConstructor
public class PublicationSpecification implements Specification<Publication> {

    private Integer userId;

    @Override
    public Predicate toPredicate(Root<Publication> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Subquery<UserRetailer> subquery = criteriaQuery.subquery(UserRetailer.class);
        Root<UserRetailer> subRoot = subquery.from(UserRetailer.class);
        return criteriaBuilder.in(root.get("retailer")).value(subquery.distinct(true).select(subRoot.get("retailer"))
                .where(criteriaBuilder.equal(subRoot.get("follower").get("id"), userId)));
    }
}
