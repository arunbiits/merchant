/**
 * 
 */
package com.euphoricthought.merchant.util;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * @author bosco
 *
 */
public class FilterUtil {

	public static MappingJacksonValue filterFields(ResponseModel response, String[] fields) {
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("ResponseModelFilter", propertyFilter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(response);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
}
