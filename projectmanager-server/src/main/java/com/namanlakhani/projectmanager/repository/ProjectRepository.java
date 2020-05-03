package com.namanlakhani.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namanlakhani.projectmanager.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
