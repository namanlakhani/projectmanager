package com.fse.projectmanager.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fse.projectmanager.project.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
