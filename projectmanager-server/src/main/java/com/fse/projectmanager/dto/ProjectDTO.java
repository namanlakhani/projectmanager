package com.fse.projectmanager.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDTO {
	
    private Long projectId;
    private String project;
    private Date startDate;
    private Date endDate;
    private int priority;
    private Long managerId;
    private String managerName;
    private Long managerEmployeeId;
    private int noOfTasks;
    private int noOfCompletedTasks;
	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return managerName;
	}
	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	/**
	 * @return the managerEmployeeId
	 */
	public Long getManagerEmployeeId() {
		return managerEmployeeId;
	}
	/**
	 * @param managerEmployeeId the managerEmployeeId to set
	 */
	public void setManagerEmployeeId(Long managerEmployeeId) {
		this.managerEmployeeId = managerEmployeeId;
	}
	/**
	 * @return the noOfTasks
	 */
	public int getNoOfTasks() {
		return noOfTasks;
	}
	/**
	 * @param noOfTasks the noOfTasks to set
	 */
	public void setNoOfTasks(int noOfTasks) {
		this.noOfTasks = noOfTasks;
	}
	/**
	 * @return the noOfCompletedTasks
	 */
	public int getNoOfCompletedTasks() {
		return noOfCompletedTasks;
	}
	/**
	 * @param noOfCompletedTasks the noOfCompletedTasks to set
	 */
	public void setNoOfCompletedTasks(int noOfCompletedTasks) {
		this.noOfCompletedTasks = noOfCompletedTasks;
	}

}
