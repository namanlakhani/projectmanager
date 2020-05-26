package com.fse.projectmanager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
import com.fse.projectmanager.dto.UserDTO;
import com.fse.projectmanager.entity.User;
import com.fse.projectmanager.repository.UserRepository;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjectmanagerApplication.class)
@ActiveProfiles("test")
public class UserControllerTest extends TestCase{

	@LocalServerPort
	private Integer port;
    private String baseUrl;
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        baseUrl = "http://localhost:".concat(port.toString()).concat("/projectmanager");
        testRestTemplate = new TestRestTemplate();
    }

    @Test
    public void testUserAdd() {
        UserDTO UserDTO = new UserDTO();
        UserDTO.setActive(true);
        UserDTO.setLastName("Naman");
        UserDTO.setFirstName("Lakhani");
        UserDTO.setEmployeeId(689709L);
        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/users/add"), UserDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void testUserAddWithUserId() {
        UserDTO UserDTO = new UserDTO();
        UserDTO.setUserId(50L);
        UserDTO.setActive(true);
        UserDTO.setLastName("Naman");
        UserDTO.setFirstName("Lakhani");
        UserDTO.setEmployeeId(689709L);
        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/users/add"), UserDTO, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void testUserUpdate() {
        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        UserDTO UserDTO = new UserDTO();
        UserDTO.setUserId(resultUser.getUserId());
        UserDTO.setActive(true);
        UserDTO.setLastName("Rohit");
        UserDTO.setFirstName("Lalwani");
        UserDTO.setEmployeeId(689709L);

        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.concat("/users/update"), HttpMethod.PUT, new HttpEntity<>(UserDTO), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testUserDelete() {
        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        User resultUser = userRepository.save(user);

        UserDTO UserDTO = new UserDTO();
        UserDTO.setUserId(resultUser.getUserId());
        UserDTO.setActive(false);
        UserDTO.setLastName("Naman");
        UserDTO.setFirstName("Lakhani");
        UserDTO.setEmployeeId(689709L);

        ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.concat("/users/delete"), HttpMethod.POST, new HttpEntity<>(UserDTO), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }



    @Test
    public void testUserGetAll() throws Exception {
        User user = new User();
        user.setUserActive(true);
        user.setLastName("Naman");
        user.setFirstName("Lakhani");
        user.setEmployeeId(689709L);
        userRepository.save(user);

        User inactiveUser = new User();
        inactiveUser.setUserActive(false);
        inactiveUser.setLastName("Rahul");
        inactiveUser.setFirstName("Sharma");
        inactiveUser.setEmployeeId(889922L);
        userRepository.save(inactiveUser);

        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.concat("/users/"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        List<UserDTO> taskDto = convertJsonToUserDTO(response.getBody());
        assertThat(taskDto.size(), equalTo(2));
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        baseUrl = null;
        testRestTemplate = null;
        userRepository.deleteAll();
    }

    private List<UserDTO> convertJsonToUserDTO(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, UserDTO.class));
    }
}