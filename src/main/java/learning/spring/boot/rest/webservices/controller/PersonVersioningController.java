package learning.spring.boot.rest.webservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import learning.spring.boot.rest.webservices.versioning.v1.Person;
import learning.spring.boot.rest.webservices.versioning.v2.Name;

@RestController
public class PersonVersioningController {

	@GetMapping(value = "v1/person")
	public Person getPersonV1() {
		return new Person("Romit Modi");
	}

	@GetMapping(value = "v2/person")
	public learning.spring.boot.rest.webservices.versioning.v2.Person getPersionV2() {
		return new learning.spring.boot.rest.webservices.versioning.v2.Person(new Name("Romit", "Modi"));
	}
	
	@GetMapping(value = "person/param",params = {"version=1"})
	public Person getParamV1() {
		return new Person("Romit Modi");
	}

	@GetMapping(value = "person/param",params = {"version=2"})
	public learning.spring.boot.rest.webservices.versioning.v2.Person getParamV2() {
		return new learning.spring.boot.rest.webservices.versioning.v2.Person(new Name("Romit", "Modi"));
	}
	
	@GetMapping(value = "person/param",headers = {"X-API-VERSION=1"})
	public Person getHeaderV1() {
		return new Person("Romit Modi");
	}

	@GetMapping(value = "person/param",headers = {"X-API-VERSION=2"})
	public learning.spring.boot.rest.webservices.versioning.v2.Person getHeaderV2() {
		return new learning.spring.boot.rest.webservices.versioning.v2.Person(new Name("Romit", "Modi"));
	}
	
	@GetMapping(value = "person/param",produces = {"application/v1+json"})
	public Person getProducesV1() {
		return new Person("Romit Modi");
	}

	@GetMapping(value = "person/param",produces = {"application/v2+json"})
	public learning.spring.boot.rest.webservices.versioning.v2.Person getProducesV2() {
		return new learning.spring.boot.rest.webservices.versioning.v2.Person(new Name("Romit", "Modi"));
	}
}
