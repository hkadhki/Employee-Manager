package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.employee.model.Employee;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


/**
 * Repository interface for {@link Employee}.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    /**
     * Finds an {@link Employee} by its ID.
     *
     * @param id the ID of the employee to be found
     * @return an {@link Optional} containing the {@link Employee} if found, otherwise empty
     */
    @Query(value = "select * from Employee where id = :id", nativeQuery = true)
    Optional<Employee> findById(@Param("id") Integer id);

    
    /**
     * Finds all {@link Employee} entities whose birth dates fall within the specified range.
     *
     * @param date1 the start date of the range
     * @param date2 the end date of the range
     * @return a {@link List} of {@link Employee} entities born within the specified date range
     */
    @Query(value = "SELECT * FROM employee WHERE birth_date::date BETWEEN :date1 AND :date2", nativeQuery = true)
    List<Employee> findBetweenDate(@Param("date1") Date date1, @Param("date2") Date date2);
}
