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
import com.fse.projectmanager.dto.ProjectDTO;
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
public class ProjectControllerTest extends TestCase {

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
    public void testProjectAdd() {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        ProjectDTO ProjectDTO = new ProjectDTO();
        ProjectDTO.setProject("Junit TDD Project");
        ProjectDTO.setStartDate(new Date());
        ProjectDTO.setEndDate(new Date());
        ProjectDTO.setPriority(25);
        ProjectDTO.setManagerId(resultUser.getUserId());

        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/projects/add"), ProjectDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void testProjectAddWithProjectId() {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        ProjectDTO ProjectDTO = new ProjectDTO();
        ProjectDTO.setProjectId(50L);
        ProjectDTO.setProject("Junit TDD Project");
        ProjectDTO.setStartDate(new Date());
        ProjectDTO.setEndDate(new Date());
        ProjectDTO.setPriority(25);
        ProjectDTO.setManagerId(resultUser.getUserId());

        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/projects/add"), ProjectDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void testProjectUpdate() {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        /*Save the User & Get Id*/
        User resultUser = userRepository.save(user);

        Project project = new Project();
        project.setProject("Junit TDD Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        ProjectDTO ProjectDTO = new ProjectDTO();
        ProjectDTO.setProjectId(resultProject.getProjectId());
        ProjectDTO.setProject("Junit TDD Project");
        ProjectDTO.setStartDate(new Date());
        ProjectDTO.setEndDate(new Date());
        ProjectDTO.setPriority(30);
        ProjectDTO.setManagerId(resultUser.getUserId());

        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.concat("/projects/update"), HttpMethod.PUT, new HttpEntity<>(ProjectDTO), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testProjectSuspend() {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        Project project = new Project();
        project.setProject("Junit TDD Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        ProjectDTO ProjectDTO = new ProjectDTO();
        ProjectDTO.setProject("Junit TDD Project");
        ProjectDTO.setStartDate(new Date());
        ProjectDTO.setEndDate(new Date());
        ProjectDTO.setPriority(30);
        ProjectDTO.setManagerId(resultUser.getUserId());
        ProjectDTO.setProjectId(resultProject.getProjectId());

        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.concat("/projects/suspend"), HttpMethod.PUT, new HttpEntity<>(ProjectDTO), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testProjectGetAll() throws Exception {

        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        Project project = new Project();
        project.setProject("Junit TDD Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(25);
        project.setUser(resultUser);
        Project resultProject = projectRepository.save(project);

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Junit TDD Parent Task");
        ParentTask resultParentTask = parentTaskRepository.save(parentTask);

        Task task = new Task();
        task.setTask("Scrum Meeting");
        task.setStatus(true);
        task.setPriority(22);
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setParentTask(resultParentTask);
        task.setUser(resultUser);
        task.setProject(resultProject);
        taskRepository.save(task);

        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.concat("/projects/"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<ProjectDTO> taskDto = convertJsonToProjectDTO(response.getBody());
        assertThat(taskDto.size(), equalTo(1));
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        baseUrl = null;
        testRestTemplate = null;
        taskRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();
        parentTaskRepository.deleteAll();
    }

    private List<ProjectDTO> convertJsonToProjectDTO(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, ProjectDTO.class));
    }
}
