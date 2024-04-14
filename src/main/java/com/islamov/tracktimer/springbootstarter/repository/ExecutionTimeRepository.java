package com.islamov.tracktimer.springbootstarter.repository;

import com.islamov.tracktimer.springbootstarter.entity.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {

    void deleteAllByNameMethod(String nameMethod);

    @Query("select averageExecutionTime " +
            "from ExecutionTime " +
            "where id = (select MAX(id) from ExecutionTime where nameMethod = :nameMethod)")
    Long getAverageExecutionTime(@Param("nameMethod") String nameMethod);

    @Query("select executionTime " +
            "from ExecutionTime " +
            "where id = (select MAX(id) from ExecutionTime where nameMethod = :nameMethod)")
    Long getLastExecutionTime(@Param("nameMethod") String nameMethod);

    ExecutionTime getExecutionTimeByNameMethod(String nameMethod);
}
