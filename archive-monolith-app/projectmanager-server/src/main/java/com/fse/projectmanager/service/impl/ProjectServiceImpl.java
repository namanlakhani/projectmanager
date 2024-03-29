package com.fse.projectmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanager.dto.ProjectDTO;
import com.fse.projectmanager.entity.Project;
import com.fse.projectmanager.repository.ProjectRepository;
import com.fse.projectmanager.service.IProjectService;
import com.fse.projectmanager.service.IUserService;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private IUserService userService;

	@Override
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(Long projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		return project.orElse(null);
	}

	@Override
	public void addProject(Project project) {
		projectRepository.save(project);
	}

	@Override
	public void updateProject(Project project) {
		projectRepository.save(project);

	}

	@Override
	public void suspendProject(Project project) {
		projectRepository.save(project);

	}

	@Override
	public Project getProject(ProjectDTO projectDto) {
		Project project;
		Optional<Project> optionalProject;
		if (projectDto.getProjectId() != null
				&& (optionalProject = projectRepository.findById(projectDto.getProjectId())).isPresent()) {
			project = optionalProject.get();
		} else {
			project = new Project();
		}
		project.setProjectId(projectDto.getProjectId());
		project.setProject(projectDto.getProject());
		project.setStartDate(projectDto.getStartDate());
		project.setEndDate(projectDto.getEndDate());
		project.setPriority(projectDto.getPriority());
		project.setUser(userService.getUserById(projectDto.getManagerId()));

		return project;
	}

	@Override
	public ProjectDTO getProjectDTO(Project project) {
		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setProjectId(project.getProjectId());
		projectDto.setProject(project.getProject());
		projectDto.setStartDate(project.getStartDate());
		projectDto.setEndDate(project.getEndDate());
		projectDto.setPriority(project.getPriority());
		projectDto.setManagerId(project.getUser().getUserId());
		projectDto.setManagerName(project.getUser().getFirstName().concat(",").concat(project.getUser().getLastName()));
		projectDto.setManagerEmployeeId(project.getUser().getEmployeeId());
		projectDto.setNoOfTasks(project.getTasks().size());
		projectDto.setNoOfCompletedTasks((int) project.getTasks().stream().filter(task -> !task.isStatus()).count());
		return projectDto;
	}

}
