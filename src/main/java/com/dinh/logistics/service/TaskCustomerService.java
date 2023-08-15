package com.dinh.logistics.service;


import com.dinh.logistics.model.TaskCustomer;
import com.dinh.logistics.model.TaskCustomerRequest;
import com.dinh.logistics.respository.CustomerRepository;
import com.dinh.logistics.respository.TaskCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskCustomerService {

    @Autowired
    private TaskCustomerRepository repository;

    @Autowired
    private CustomerRepository customerRepository;


    public List<TaskCustomer> findTasksByUserTaskAndStatusAndTime(TaskCustomer taskCustomer) throws ParseException {
        String userTask = taskCustomer.getUserTask();
        String taskStatus = taskCustomer.getTaskStatus();
        String timeTask = taskCustomer.getTimeTask();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timeTaskDate = dateFormat.parse(timeTask);

        return repository.findTasksByUserTaskAndStatusAndTime(userTask, taskStatus, timeTaskDate);
    }

    public List<TaskCustomer> getTaskByCustomer(TaskCustomerRequest taskCustomer){
        String userTask = taskCustomer.getUserTask();
        String groupUser = customerRepository.getGroupType(userTask);
        String taskStatus = taskCustomer.getTaskStatus();
        String timeTask = taskCustomer.getTimeTask();
        String statusUser = taskCustomer.getStatusUser();

        if (statusUser.equals("TOI")){
            if (taskCustomer.getTaskStatus().equals("0")) { // chua xong
                return repository.getTaskByCustomer(userTask,groupUser, taskStatus, timeTask);
            }
            if (taskCustomer.getTaskStatus().equals("1")) { // da xong
                return repository.getTaskByCustomer(userTask,groupUser, taskStatus, timeTask);
            }
            if (taskCustomer.getTaskStatus().equals("-1")) { // tat ca task
                return repository.getTaskByCustomer(userTask,groupUser, null, timeTask);
            }
        }
        else if (statusUser.equals("NHOM")){

            if (taskCustomer.getTaskStatus().equals("0")) { // chua xong
                return repository.getTaskByCustomer(null,groupUser, taskStatus, timeTask);
            }
            if (taskCustomer.getTaskStatus().equals("1")) { // da xong
                return repository.getTaskByCustomer(null,groupUser, taskStatus, timeTask);
            }
            if (taskCustomer.getTaskStatus().equals("-1")) { // tat ca task
                return repository.getTaskByCustomer(null,groupUser, null, timeTask);
            }
        }
        else if (statusUser.equals("TATCA")){
            if (taskCustomer.getTaskStatus().equals("0")) { // chua xong
                return repository.getTaskByCustomer(null,null, taskStatus, timeTask);
            }
            if (taskCustomer.getTaskStatus().equals("1")) { // da xong
                return repository.getTaskByCustomer(null,null, taskStatus, timeTask);
            }
            if (taskCustomer.getTaskStatus().equals("-1")) { // tat ca task
                return repository.getTaskByCustomer(null,null, null, timeTask);
            }
        }
        return repository.getTaskByCustomer(userTask,groupUser, taskStatus, timeTask);
    }
}
