package learning.spring.boot.rest.webservices.controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import learning.spring.boot.rest.webservices.filtering.model.DynamicFilteringBean;
import learning.spring.boot.rest.webservices.filtering.model.FilteringBean;

@RestController
public class FilteringController {

	@GetMapping(value = "/filtering")
	public FilteringBean retriveFilteringBean() {
		return new FilteringBean("value1", "value2");
	}

	/**
	 * Below implementation is good example of Dynamic Data filtering based on which
	 * what data we need to send to response can be planned.
	 */
	@GetMapping(value = "/filtering-dynamic")
	public MappingJacksonValue retriveDynamicFilteringBean() {
		DynamicFilteringBean dynamicFilteringBean = new DynamicFilteringBean("value1", "value2");

		SimpleBeanPropertyFilter beanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("feild2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicFilteringBeanFilter", beanPropertyFilter);

		MappingJacksonValue jacksonValue = new MappingJacksonValue(dynamicFilteringBean);
		jacksonValue.setFilters(filters);

		return jacksonValue;
	}

}
