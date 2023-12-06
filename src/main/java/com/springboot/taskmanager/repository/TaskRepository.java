package com.springboot.taskmanager.repository;

import com.springboot.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t FROM Task t WHERE t.user.id = :userId")
	List<Task> findByUser_Id(@Param("userId") Long userId);
	
	Optional<Task> findById(Long taskId);


    List<Task> findByPriority(String priority); // Custom method to find tasks by priority

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.user")
    List<Task> findAllWithUserDetails();
}
