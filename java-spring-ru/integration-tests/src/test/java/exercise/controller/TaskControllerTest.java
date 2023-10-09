package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// END
@SpringBootTest
@AutoConfigureMockMvc
// BEGIN
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Faker faker;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Task testTask;

    @BeforeEach
    public void createTask() throws Exception {
        var title = faker.lorem().word();
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getUpdatedAt))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> title)
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task));

        mvc.perform(request).andExpect(status().isCreated());
        testTask = taskRepository.findByTitle(title).get();
    }

    @AfterEach
    public void deleteTask() throws Exception {
        taskRepository.deleteById(1L);
    }

    @Test
    public void testCreate() throws Exception {
        var createdTask = taskRepository.findByTitle(testTask.getTitle());
        assertThat(createdTask).isNotEmpty();
    }

    @Test
    public void testShow() throws Exception {
        var response = mvc.perform(get("/tasks/" + testTask.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void testUpdate() throws Exception {
        var data = new HashMap<>();
        data.put("title", "testTitle");

        var request = put("/tasks/" + testTask.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mvc.perform(request).andExpect(status().isOk());

        var task = taskRepository.findByTitle("testTitle");
        assertThat(task).isNotEmpty();
    }

    @Test
    public void testDelete() throws Exception {
        var request = delete("/tasks/" + testTask.getId());

        mvc.perform(request).andExpect(status().isOk());
        var task = taskRepository.findById(testTask.getId());
        assertThat(task).isEmpty();
    }
}
// END

