package com.fse.projectmanager.parenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projectmanager.parenttask.entity.ParentTask;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {

}
