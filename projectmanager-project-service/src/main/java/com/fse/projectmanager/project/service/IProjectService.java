package com.fse.projectmanager.project.service;

import java.util.List;

import com.fse.projectmanager.project.dto.ProjectDTO;
import com.fse.projectmanager.project.entity.Project;
import com.fse.projectmanager.project.entity.User;


public interface IProjectService {

    List<Project> getAllProjects();
    Project getProjectById(Long projectId);
    void addProject(Project project);
    void updateProject(Project project);
    void suspendProject(Project project);
    Project getProject(ProjectDTO projectDto,User user);
    ProjectDTO getProjectDTO(Project project);
}
