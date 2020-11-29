package learning.spring.boot.rest.webservices.filtering.model;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(value = "dynamicFilteringBeanFilter")
public class DynamicFilteringBean {

	private String feild1;
	private String feild2;

	public DynamicFilteringBean(String feild1, String feild2) {
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
