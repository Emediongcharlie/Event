package org.eventticket.eventticketapp;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.eventticket.eventticketapp.adminServices.AdminService;
import org.eventticket.eventticketapp.data.repository.AdminRepository;
import org.eventticket.eventticketapp.dtos.request.CreateLoginRequest;
import org.eventticket.eventticketapp.dtos.request.CreateLogoutRequest;
import org.eventticket.eventticketapp.dtos.request.CreateRegistrationRequest;
import org.eventticket.eventticketapp.dtos.response.LoginResponse;
import org.eventticket.eventticketapp.dtos.response.LogoutResponse;
import org.eventticket.eventticketapp.dtos.response.RegistrationResponse;
import org.eventticket.eventticketapp.exception.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventTicketAppApplicationTests {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	AdminService adminService;
	@BeforeEach
	public void setUp() {adminRepository.deleteAll();}

	@Test
	void contextLoads() {
	}

	@Test
	public void testThatUserCanRegister(){
		CreateRegistrationRequest createRegistrationRequest=new CreateRegistrationRequest();
		createRegistrationRequest.setPassword("12345");
		createRegistrationRequest.setUsername("admin");
		RegistrationResponse response = adminService.register(createRegistrationRequest);
		assertThat(response).isNotNull();
		assertThat(response.getMessage().contains("successfully registered"));

		assertThat(adminService.getAllUsers().size());
	}

	@Test
	public void testThatOneUserCanNotRegisterTwice(){
		CreateRegistrationRequest createRegistrationRequest=new CreateRegistrationRequest();
		createRegistrationRequest.setPassword("12345");
		createRegistrationRequest.setUsername("admin");
		adminService.register(createRegistrationRequest);

		assertThat(adminService.getAllUsers().size()).isEqualTo(1);

		CreateRegistrationRequest createSecondRegistrationRequest=new CreateRegistrationRequest();
		createSecondRegistrationRequest.setPassword("12345");
		createSecondRegistrationRequest.setUsername("admin");

//		adminService.register(createSecondRegistrationRequest);
		assertThrows(UserAlreadyExistException.class, ()->adminService.register(createSecondRegistrationRequest).getMessage());
//		assertThat(response).isNotNull();


	}



	@Test
	public void testThatAdminCanLogin(){
		CreateRegistrationRequest createRegistrationRequest=new CreateRegistrationRequest();
		createRegistrationRequest.setPassword("12345");
		createRegistrationRequest.setUsername("admin");
		RegistrationResponse response = adminService.register(createRegistrationRequest);
		assertThat(response).isNotNull();
		assertThat(response.getMessage().contains("successfully registered"));

		assertThat(adminService.getAllUsers().size());

		CreateLoginRequest createLoginRequest=new CreateLoginRequest();
		createLoginRequest.setUsername("admin");
		createLoginRequest.setPassword("12345");
		LoginResponse loginResponse = adminService.login(createLoginRequest);
		assertThat(loginResponse).isNotNull();

	}
	@Test
	public void testThatAdminCanLogout(){
		CreateRegistrationRequest createRegistrationRequest=new CreateRegistrationRequest();
		createRegistrationRequest.setPassword("12345");
		createRegistrationRequest.setUsername("admin");
		RegistrationResponse response = adminService.register(createRegistrationRequest);
		assertThat(response).isNotNull();
		assertThat(response.getMessage().contains("successfully registered"));

		assertThat(adminService.getAllUsers().size());

		CreateLoginRequest createLoginRequest=new CreateLoginRequest();
		createLoginRequest.setUsername("admin");
		createLoginRequest.setPassword("12345");
		LoginResponse loginResponse = adminService.login(createLoginRequest);
		assertThat(loginResponse).isNotNull();

		CreateLogoutRequest logoutRequest = new CreateLogoutRequest();
		logoutRequest.setUsername("admin");
		LogoutResponse logoutResponse = adminService.logout(logoutRequest);
		assertThat(logoutResponse).isNotNull();
		assertThat(logoutResponse.getMessage().contains("successfully logged out"));



	}
}
