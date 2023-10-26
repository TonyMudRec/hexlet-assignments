package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository tRepo;

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private TaskMapper mapper;


    @GetMapping("")
    public List<TaskDTO> index() {
        return tRepo.findAll().stream().map(t -> mapper.map(t)).toList();
    }

    @GetMapping("/{id}")
    public TaskDTO show(@PathVariable Long id) {
        var task = tRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task with id %d not exist", id)));
        var taskDTO = mapper.map(task);
        taskDTO.setAssigneeId(task.getAssignee().getId());
        return taskDTO;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody TaskCreateDTO taskCreateDTO) {
        var id = taskCreateDTO.getAssigneeId();
        var user = uRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %d not exist", id)));
        var task = mapper.map(taskCreateDTO);
        user.addTask(task);
        task.setAssignee(user);
        tRepo.save(task);
        return mapper.map(task);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable Long id, @RequestBody TaskUpdateDTO updateDTO) {
        var task = tRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        var user =  uRepo.findById(updateDTO.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        mapper.update(updateDTO, task);
        task.setAssignee(user);
        tRepo.save(task);
        var postDTO = mapper.map(task);
        return postDTO;
    }

    @DeleteMapping("")
    public void delete(@PathVariable Long id) {
        var task = tRepo.findById(id).get();
        var user = task.getAssignee();
        user.removeTask(task);
        tRepo.deleteById(id);
    }
    // END
}
