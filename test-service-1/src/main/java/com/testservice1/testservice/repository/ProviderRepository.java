package com.testservice1.testservice.repository;

import com.testservice1.testservice.entity.Test_Class_1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Test_Repository extends JpaRepository<Test_Class_1, Long> {

    Test_Class_1 findByClassTest_Class_1_id(long id);
}
