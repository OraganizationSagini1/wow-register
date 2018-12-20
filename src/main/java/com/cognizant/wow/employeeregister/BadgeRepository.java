package com.cognizant.wow.employeeregister;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BadgeRepository extends CrudRepository<Badge, Long> {


    Optional<Badge> findTopByStatusAndAssignedOrderByBadgeId(String Status, String assigned);
}
