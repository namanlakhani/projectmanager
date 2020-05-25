package com.namanlakhani.projectmanager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.namanlakhani.projectmanager.dto.ParentTaskDTO;
import com.namanlakhani.projectmanager.entity.ParentTask;
import com.namanlakhani.projectmanager.repository.ParentTaskRepository;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjectmanagerApplication.class)
@ActiveProfiles("test")
public class ParentTaskControllerTest extends TestCase {

    @LocalServerPort
    private Integer port;
    private String baseUrl;
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ParentTaskRepository parentTaskRepository;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        baseUrl = "http://localhost:".concat(port.toString()).concat("/projectmanager/");
        testRestTemplate = new TestRestTemplate();
    }


    @Test
    public void testParentTaskGetAll() throws Exception {

        ParentTask parentTask = new ParentTask();
        parentTask.setParentTask("Junit Task for TDD");
        parentTaskRepository.save(parentTask);

        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.concat("parenttasks/"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<ParentTaskDTO> taskDto = convertJsonToParentTaskDTO(response.getBody());
        assertThat(taskDto.size(), equalTo(1));
    }

    @Test
    public void testParentTaskAdd() {
        ParentTaskDTO ParentTaskDTO = new ParentTaskDTO();
        ParentTaskDTO.setParentTask("Junit Task for TDD");
        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("parenttasks/add"), ParentTaskDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void testParentTaskAddWithParentTaskId() {
        ParentTaskDTO ParentTaskDTO = new ParentTaskDTO();
        ParentTaskDTO.setParentId(50L);
        ParentTaskDTO.setParentTask("Junit Task for TDD");
        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("parenttasks/add"), ParentTaskDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        baseUrl = null;
        testRestTemplate = null;
        parentTaskRepository.deleteAll();
    }

    private List<ParentTaskDTO> convertJsonToParentTaskDTO(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, ParentTaskDTO.class));
    }
}