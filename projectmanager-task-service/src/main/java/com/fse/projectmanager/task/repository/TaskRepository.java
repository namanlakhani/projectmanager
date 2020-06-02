package com.fse.projectmanager.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projectmanager.task.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
