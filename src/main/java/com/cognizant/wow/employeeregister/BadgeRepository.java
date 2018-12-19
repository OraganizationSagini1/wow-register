package com.cognizant.wow.employeeregister;

import org.springframework.data.repository.CrudRepository;

public interface BadgeRepository extends CrudRepository<Badge, Long> {


    Badge findTopByStatusAndAssignedOrderByBadgeId(String Status, String assigned);
}
