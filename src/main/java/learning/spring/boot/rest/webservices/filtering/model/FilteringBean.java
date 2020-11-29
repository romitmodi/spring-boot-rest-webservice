package learning.spring.boot.rest.webservices.filtering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"feild2"})
public class FilteringBean {

	@JsonIgnore
	private String feild1;
	private String feild2;

	public FilteringBean(String feild1, String feild2) {
		super();
		this.feild1 = feild1;
		this.feild2 = feild2;
	}

	public String getFeild1() {
		return feild1;
	}

	public String getFeild2() {
		return feild2;
	}

}
