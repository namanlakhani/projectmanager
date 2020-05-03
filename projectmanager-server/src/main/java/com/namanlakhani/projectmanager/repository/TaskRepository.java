package com.namanlakhani.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namanlakhani.projectmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
