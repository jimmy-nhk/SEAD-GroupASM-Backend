package com.testservice2.testservice2.repository;

import com.testservice2.testservice2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUserId(long id);
}
