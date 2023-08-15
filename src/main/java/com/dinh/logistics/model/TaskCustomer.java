package com.dinh.logistics.model;

import javax.persistence.*;

@Entity
@Table(name = "taskcustomer")
public class TaskCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_task")
    private String userTask;

    @Column(name = "group_user")
    private String groupUser;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_status")
    private String taskStatus; //Chưa xong là 0, đã xong là 1

    @Column(name = "time_task")
    private String timeTask;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserTask() {
        return userTask;
    }

    public void setUserTask(String userTask) {
        this.userTask = userTask;
    }

    public String getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(String groupUser) {
        this.groupUser = groupUser;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(String timeTask) {
        this.timeTask = timeTask;
    }
}
