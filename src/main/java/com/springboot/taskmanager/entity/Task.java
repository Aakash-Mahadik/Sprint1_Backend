package com.springboot.taskmanager.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;
    private String description;
    private Date dueDate;
    private String priority;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "task")
    @JsonIgnore
    private List<Comment> comments;
    
    @ManyToOne
    @JoinColumn(name = "user_id") // adjust the column name as needed
    //@JsonIgnore
    private User user;

    
    
 // Constructors, getters, and setters...
	

	public Task(Long taskId, String title, String description, Date dueDate, String priority, String status, Date createdAt,
		Date updatedAt, List<Comment> comments) {
	super();
	this.taskId = taskId;
	this.title = title;
	this.description = description;
	this.dueDate = dueDate;
	this.priority = priority;
	this.status = status;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.comments = comments;
}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public String toString() {
		return "Task [getTaskId()=" + getTaskId() + ", getTitle()=" + getTitle() + ", getDescription()="
				+ getDescription() + ", getDueDate()=" + getDueDate() + ", getPriority()=" + getPriority()
				+ ", getStatus()=" + getStatus() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()="
				+ getUpdatedAt() + ", getComments()=" + getComments() + "]";
	}

    
    
}
