package com.namanlakhani.projectmanager.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task {
	
	@Id
	@Column(name="task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;
	
	@Column(name="task")
	private String task;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="status")
	private boolean status;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "parent_id")
	private ParentTask parentTask;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")	
	private User user;
	
	/**
	 * @return the taskId
	 */
	public Long getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
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
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the parentTask
	 */
	public ParentTask getParentTask() {
		return parentTask;
	}
	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}
	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
