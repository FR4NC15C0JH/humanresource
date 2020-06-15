package com.humanresource.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class HRSerializerConfig{
	
	@Bean
	Jackson2ObjectMapperBuilder objectMapperBuilder() {
	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	    ObjectMapper mapper = new ObjectMapper();
	    
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);
	    mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
	    mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
	    mapper.configure(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

	    mapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
	    mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	    builder.configure(mapper);
	    builder.serializationInclusion(JsonInclude.Include.NON_NULL);
	    builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
	    
	    return builder;
	}
	
}
