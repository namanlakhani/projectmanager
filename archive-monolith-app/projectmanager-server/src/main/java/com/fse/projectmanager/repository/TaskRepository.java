package com.fse.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projectmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
