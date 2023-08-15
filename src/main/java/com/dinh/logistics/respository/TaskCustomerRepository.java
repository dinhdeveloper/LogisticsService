package com.dinh.logistics.respository;

import com.dinh.logistics.model.TaskCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskCustomerRepository extends JpaRepository<TaskCustomer, Long> {

    //@Query("SELECT e FROM TaskCustomer e WHERE e.userTask = :userTask AND e.taskStatus = :taskStatus AND e.timeTask = CAST(:timeTask AS date)")
    @Query("SELECT e FROM TaskCustomer e " +
            "WHERE e.timeTask = CAST(:timeTask AS date)" +
            "AND (:taskStatus IS NULL OR e.taskStatus = :taskStatus) " +
            "AND (:groupUser IS NULL OR e.groupUser = :groupUser) " +
            "AND  (:userTask IS NULL OR e.userTask = :userTask) ")
    List<TaskCustomer> getTaskByCustomer(
            @Param("userTask") String userTask,
            @Param("groupUser") String groupUser,
            @Param("taskStatus") String taskStatus,
            @Param("timeTask") String timeTask
    );

    @Query("SELECT e FROM TaskCustomer e WHERE e.userTask = :userTask AND e.taskStatus = :taskStatus AND e.timeTask = :timeTask")
    List<TaskCustomer> findTasksByUserTaskAndStatusAndTime(
            @Param("userTask") String userTask,
            @Param("taskStatus") String taskStatus,
            @Param("timeTask") Date timeTask);


}
