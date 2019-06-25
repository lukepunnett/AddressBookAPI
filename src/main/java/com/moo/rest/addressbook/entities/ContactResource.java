package com.moo.rest.addressbook.entities;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.moo.rest.addressbook.exceptions.ContactNotFoundException;

@RestController
public class ContactResource {

	

		@Autowired
		private ContactRepository contactRepository;


		@GetMapping(path = "/contacts")
		public List<Contact> retrieveAllContacts() {
			return contactRepository.findAll();
		}

		@GetMapping(path = "/contacts/{id}")
		public Resource<Contact> retrieveContact(@PathVariable int id) {
			Optional<Contact> contact = contactRepository.findById(id);

			if (!contact.isPresent())
				throw new ContactNotFoundException("id-" + id);

			Resource<Contact> resource = new Resource<Contact>(contact.get());

			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllContacts());

			resource.add(linkTo.withRel("all-contacts"));

			// HATEOAS

			return resource;
		}

			
		@GetMapping(path = "/contacts/byName/{name}")
		public List<Contact> retrieveContactBySecondName(@PathVariable String name) {
			
			return contactRepository.findBySecondName(name);
		}
//			List<Contact> contact = contactRepository.findBySecondName(name);
//
////			if (!contact.isPresent())
////				throw new UserNotFoundException("id-" + id);
////
////			Resource<Contact> resource = new Resource<Contact>(contact.get());
////
////			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllContacts());
////
////			resource.add(linkTo.withRel("all-contacts"));
////
////			// HATEOAS
//
//			return contact;
//		}
		
		
		@DeleteMapping("/contacts/{id}")
		public void deleteContact(@PathVariable int id) {
			contactRepository.deleteById(id);
		}

		//
		// input - details of user
		// output - CREATED & Return the created URI

		// HATEOAS

		@PostMapping("/contacts")
		public ResponseEntity<Object> createContact(@Valid @RequestBody Contact contact) {
			Contact savedContact = contactRepository.save(contact);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedContact.getId())
					.toUri();

			return ResponseEntity.created(location).build();

		}
		
		@GetMapping("contacts/{id}/addresses")
		public List<Address> retrieveAllAddresses(@PathVariable int id) {
			Optional<Contact> contactOptional = contactRepository.findById(id);
			
			if(!contactOptional.isPresent()) {
				throw new ContactNotFoundException("id-" + id);
			}
			
			return contactOptional.get().getAddresses();
		}


		@PostMapping("/jpa/users/{id}/posts")
		public ResponseEntity<Object> createAddress(@PathVariable int id, @RequestBody Address address) {
			
			Optional<Contact> contactOptional = contactRepository.findById(id);
			
			if(!contactOptional.isPresent()) {
				throw new ContactNotFoundException("id-" + id);
			}

			Contact contact = contactOptional.get();
			
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId())
					.toUri();

			return ResponseEntity.created(location).build();

		}

}
