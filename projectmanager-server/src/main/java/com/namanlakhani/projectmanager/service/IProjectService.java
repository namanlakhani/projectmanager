package com.namanlakhani.projectmanager.service;

import java.util.List;

import com.namanlakhani.projectmanager.dto.ProjectDTO;
import com.namanlakhani.projectmanager.entity.Project;


public interface IProjectService {

    List<Project> getAllProjects();
    Project getProjectById(Long projectId);
    void addProject(Project project);
    void updateProject(Project project);
    void suspendProject(Project project);
    Project getProject(ProjectDTO projectDto);
    ProjectDTO getProjectDTO(Project project);
}
