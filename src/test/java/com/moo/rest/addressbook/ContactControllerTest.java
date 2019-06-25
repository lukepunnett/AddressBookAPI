package com.moo.rest.addressbook;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.apache.tomcat.jni.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.base.Optional;
import com.google.common.net.MediaType;
import com.moo.rest.addressbook.entities.Contact;
import com.moo.rest.addressbook.entities.ContactRepository;
import com.moo.rest.addressbook.entities.ContactResource;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactResource.class)
public class ContactControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContactResource contactResource;
	
	@Test
	public void retrieveSingleContact() throws Exception {
		when(contactResource.retrieveContact(8002)).thenReturn(
				new Resource<Contact>(new Contact(8002,"Dave","Lister","dave.lister@reddwarf.com"))
				);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/contacts/8002");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{id: 8002, firstName: Dave, secondName: Lister, emailAddress: \"dave.lister@reddwarf.com\"}",false))
				.andReturn();
	}
//		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//		
//	}
	
	@Test
	public void retrieveAllItems_noitems() throws Exception {
		when(contactResource.retrieveAllContacts()).thenReturn(
				Arrays.asList()
				);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/contacts");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[]"))
				.andReturn();
		//JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

}
