package com.fse.projectmanager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fse.projectmanager.ProjectmanagerApplication;
import com.fse.projectmanager.dto.TaskDTO;
import com.fse.projectmanager.entity.ParentTask;
import com.fse.projectmanager.entity.Project;
import com.fse.projectmanager.entity.Task;
import com.fse.projectmanager.entity.User;
import com.fse.projectmanager.repository.ParentTaskRepository;
import com.fse.projectmanager.repository.ProjectRepository;
import com.fse.projectmanager.repository.TaskRepository;
import com.fse.projectmanager.repository.UserRepository;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjectmanagerApplication.class)
@ActiveProfiles("test")
public class TaskControllerTest extends TestCase{


	@LocalServerPort
	private Integer port;
    private String baseUrl;
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParentTaskRepository parentTaskRepository;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        baseUrl = "http://localhost:".concat(port.toString()).concat("/projectmanager");
        testRestTemplate = new TestRestTemplate();
    }


    @Test
    public void testTaskAdd() {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Junit TDD Task");
        ParentTask resultParentTask = parentTaskRepository.save(parentTask);

        Project project = new Project();
        project.setProject("FSE S1 Certification Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        TaskDTO TaskDTO = new TaskDTO();
        TaskDTO.setTask("Create RTM");
        TaskDTO.setStatus(true);
        TaskDTO.setPriority(22);
        TaskDTO.setStartDate(new Date());
        TaskDTO.setEndDate(new Date());
        TaskDTO.setParentId(resultParentTask.getParentId());
        TaskDTO.setUserId(resultUser.getUserId());
        TaskDTO.setProjectId(resultProject.getProjectId());

        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/tasks/add"), TaskDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void testTaskAddWithTaskId() {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        /*Save the User & Get Id*/
        User resultUser = userRepository.save(user);

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Junit TDD Task");
        ParentTask resultParentTask = parentTaskRepository.save(parentTask);

        Project project = new Project();
        project.setProject("Mcdonals Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        TaskDTO TaskDTO = new TaskDTO();
        TaskDTO.setTaskId(50L);
        TaskDTO.setTask("Setup AWS Gateway");
        TaskDTO.setStatus(true);
        TaskDTO.setPriority(22);
        TaskDTO.setStartDate(new Date());
        TaskDTO.setEndDate(new Date());
        TaskDTO.setParentId(resultParentTask.getParentId());
        TaskDTO.setUserId(resultUser.getUserId());
        TaskDTO.setProjectId(resultProject.getProjectId());

        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/tasks/add"), TaskDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void testTaskUpdate() {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        Project project = new Project();
        project.setProject("Farmer Union Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Junit TDD Task");
        ParentTask resultParentTask = parentTaskRepository.save(parentTask);

        Task task = new Task();
        task.setTask("Setup Scrum Meeting");
        task.setStatus(true);
        task.setPriority(22);
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setParentTask(resultParentTask);
        task.setUser(resultUser);
        task.setProject(resultProject);
        taskRepository.save(task);

        TaskDTO TaskDTO = new TaskDTO();
        TaskDTO.setTask("Setup Scrum Meeting");
        TaskDTO.setStatus(true);
        TaskDTO.setPriority(28);
        TaskDTO.setStartDate(new Date());
        TaskDTO.setEndDate(new Date());
        TaskDTO.setParentId(resultParentTask.getParentId());
        TaskDTO.setUserId(resultUser.getUserId());
        TaskDTO.setProjectId(resultProject.getProjectId());

        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.concat("/tasks/update"), HttpMethod.PUT, new HttpEntity<>(TaskDTO), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testTaskEnd() {


        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        Project project = new Project();
        project.setProject("FSE S1 Certification Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Junit TDD Task");
        ParentTask resultParentTask = parentTaskRepository.save(parentTask);

        Task task = new Task();
        task.setTask("Setup Scrum Meeting");
        task.setStatus(true);
        task.setPriority(20);
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setParentTask(resultParentTask);
        task.setUser(resultUser);
        task.setProject(resultProject);
        taskRepository.save(task);

        TaskDTO TaskDTO = new TaskDTO();
        TaskDTO.setTask("Setup Scrum Meeting");
        TaskDTO.setStatus(false);
        TaskDTO.setPriority(28);
        TaskDTO.setStartDate(new Date());
        TaskDTO.setEndDate(new Date());
        TaskDTO.setParentId(resultParentTask.getParentId());
        TaskDTO.setUserId(resultUser.getUserId());
        TaskDTO.setProjectId(resultProject.getProjectId());

        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.concat("/tasks/end"), HttpMethod.PUT, new HttpEntity<>(TaskDTO), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testGetAllTask() throws Exception {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        Project project = new Project();
        project.setProject("IIM project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Junit TDD Task");
        ParentTask resultParentTask = parentTaskRepository.save(parentTask);

        Task task = new Task();
        task.setTask("PPT Creation");
        task.setStatus(true);
        task.setPriority(22);
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setParentTask(resultParentTask);
        task.setUser(resultUser);
        task.setProject(resultProject);
        taskRepository.save(task);

        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.concat("/tasks/"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<TaskDTO> TaskDTO = convertJsonToTaskDTO(response.getBody());
        assertThat(TaskDTO.size(), equalTo(1));
    }

    @Test
    public void testGetTaskByProject() throws Exception {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        /*Save the User & Get Id*/
        User resultUser = userRepository.save(user);

        Project project = new Project();
        project.setProject("IIT KGP MS S1");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Junit TDD Task");
        ParentTask resultParentTask = parentTaskRepository.save(parentTask);

        Task task = new Task();
        task.setTask("Drone Setup");
        task.setStatus(true);
        task.setPriority(22);
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setParentTask(resultParentTask);
        task.setUser(resultUser);
        task.setProject(resultProject);
        taskRepository.save(task);

        TaskDTO TaskDTO = new TaskDTO();
        TaskDTO.setProjectId(resultProject.getProjectId());

        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/tasks/projecttask"), TaskDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<TaskDTO> TaskDTOByProject = convertJsonToTaskDTO(response.getBody());
        assertThat(TaskDTOByProject.size(), equalTo(1));
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        baseUrl = null;
        testRestTemplate = null;
        taskRepository.deleteAll();
        projectRepository.deleteAll();
        parentTaskRepository.deleteAll();
        userRepository.deleteAll();
    }

    private List<TaskDTO> convertJsonToTaskDTO(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, TaskDTO.class));
    }
}