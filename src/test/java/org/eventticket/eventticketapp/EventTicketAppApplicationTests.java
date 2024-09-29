package org.eventticket.eventticketapp;


import org.eventticket.eventticketapp.data.model.Admin;

import org.eventticket.eventticketapp.data.model.Event;
import org.eventticket.eventticketapp.data.repository.EventRepository;
import org.eventticket.eventticketapp.EventDTO.request.*;
import org.eventticket.eventticketapp.EventDTO.response.*;
import org.eventticket.eventticketapp.services.AdminServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.eventticket.eventticketapp.adminServices.AdminService;
import org.eventticket.eventticketapp.dtos.request.CreateLoginRequest;
import org.eventticket.eventticketapp.dtos.request.CreateLogoutRequest;
import org.eventticket.eventticketapp.dtos.request.CreateRegistrationRequest;
import org.eventticket.eventticketapp.dtos.response.LoginResponse;
import org.eventticket.eventticketapp.dtos.response.LogoutResponse;
import org.eventticket.eventticketapp.dtos.response.RegistrationResponse;
import org.eventticket.eventticketapp.exception.UserAlreadyExistException;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class EventTicketAppApplicationTests {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	AdminService adminService;
	@BeforeEach
	public void setUp() {adminRepository.deleteAll();}

    @Autowired
    private AdminServices adminServices;
    @Autowired
    private EventRepository eventRepository;

//    @BeforeEach
//    void setUp() {
//        eventRepository.deleteAll();
//    }

    @Test
    public void testAdminCanCreateEvent() {
        BookEventRequest request = new BookEventRequest();
        request.setEventName("tech hangout");
        request.setEventDescription("to get all techie together");
        request.setEventDateAndTime("4/3/2024");
        request.setEventLocation("Lagos");
        request.setPrice("2000");
        BookEventResponse response = adminServices.createEvent(request);
        assertThat(response).isNotNull();
        assertThat(response.getEventName().contains("tech hangout"));
    }

    @Test
    public void testOneEventCannotBeCreatedTwice(){
        BookEventRequest request = new BookEventRequest();
        request.setEventName("techlogy");
        request.setEventDescription("explore the world of tech");
        request.setEventDateAndTime("4/12/2024");
        request.setEventLocation("Lagos");
        request.setPrice("10000");
        BookEventResponse response = adminServices.createEvent(request);
        assertThat(response).isNotNull();
    }

    @Test
    public void testEventCanBeUpdated(){
        BookEventRequest request = new BookEventRequest();
        request.setEventName("tech journey");
        request.setEventDescription("to get all techie together");
        request.setEventDateAndTime("4/3/2024");
        request.setEventLocation("Lagos");
        request.setPrice("2000");
        BookEventResponse response = adminServices.createEvent(request);
        assertThat(response).isNotNull();
        EventUpdateRequest request1 = new EventUpdateRequest();
        request1.setEventName("tech journey");
        request1.setEventDescription("techie together");
        request1.setEventDateAndTime("4/3/2024");
        request1.setEventLocation("Abuja");
        EventUpdateResponse response1 = adminServices.updateEvent(request1, response.getEventName());
        assertThat(response1).isNotNull();
        assertThat(response1.getEventDescription()).contains("techie together");
    }

    @Test
    public void testEventCanBeDeletedByName(){
        RemoveEventRequest request1 = new RemoveEventRequest();
        request1.setEventName("3");
        RemoveEventResponse response1 = adminServices.removeEventByName(request1, "3");
        assertThat(response1.getMessage().contains("successfully removed"));

    }

    @Test
    public void testThatAllEventsCanBeRetrieved(){
        List<Event> response = adminServices.viewEvent();
        assertThat(response).isNotNull();
    }

    @Test
    public void testFindEventByLocation(){
        FindEventByLocationRequest request = new FindEventByLocationRequest();
        request.setEventName("tech");
        request.setLocation("1");
//        List<Event> response = adminServices.findEventByEventLocation(request);
//        assertThat(response).isNotNull();
    }

    @Test
    public void testFindEventByPrice(){
        FindEventByPriceRequest request = new FindEventByPriceRequest();
        request.setPrice("2");
        Optional<Event> response = eventRepository.findAllEventByPrice("2");
        assertThat(response).isNotNull();
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
