package com.tasktracker.application.models;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "Tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long task_id;

    @Column(name = "taskTitle")
    @NotNull(message="{NotNull.Task.taskTitle}")
	private String taskTitle;

    @Column(name = "taskDescription")
    @NotNull(message="{NotNull.Task.taskDescription}")
	private String taskDescription;
    
    @Column(name = "eta")
    @NotNull(message="{NotNull.Task.eta}")
    private String eta;
    
    @Column(name = "assigned")
    @NotNull(message="{NotNull.Task.assigned}")
    private String assigned;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")
	private Set<Comment> comments = new HashSet<>();

    @Column(name = "points")
    @NotNull(message="{NotNull.Task.points}")
    private String points;

    @Column(name = "Resolved")
	private boolean resolved;

	public Task() {

	}

	public Task(String taskTitle, String taskDescription, String eta, String assigned, String points , boolean resolved) {
		this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.eta = eta;
        this.assigned = assigned;
        this.points = points;
        this.resolved = resolved;

	}

	public long getId() {
		return task_id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
    }
    

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
    }
    

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean isResolved) {
		this.resolved = isResolved;
	}

	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", title=" + taskTitle + ", desc=" + taskDescription + ", eta=" + eta + ", assigned=" + assigned + ", points=" + points + ", resolved=" + resolved + "]";
	}

}
