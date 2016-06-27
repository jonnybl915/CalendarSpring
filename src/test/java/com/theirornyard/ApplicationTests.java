package com.theirornyard;

import com.theirornyard.services.EventRepository;
import com.theirornyard.services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc; //sends "fake" post requests to our routes

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Autowired
	UserRepository users;

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
						.param("username", "Jonny")   //need to match up with the params in the /login route
						.param("password", "mypass")
		);

		Assert.assertTrue(users.count() == 1);
	}
	@Autowired
	EventRepository events;
	@Test
	public void testCreate() throws Exception { //before we can test this we need to make sure login works
		testLogin(); // <-----------<----------<-----------<----<
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-event")
							.param("description", "This is My event")
							.param("time", LocalDateTime.now().toString())
							.sessionAttr("username", "Bro")
		);
		Assert.assertTrue(events.count() ==1);
	}
}
