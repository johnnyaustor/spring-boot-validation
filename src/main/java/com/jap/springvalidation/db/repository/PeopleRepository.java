package com.jap.springvalidation.db.repository;

import com.jap.springvalidation.db.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    Boolean existsByEmail(String email);
}
