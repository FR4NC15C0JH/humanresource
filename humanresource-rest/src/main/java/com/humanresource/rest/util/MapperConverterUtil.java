package com.humanresource.rest.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class MapperConverterUtil {

	private MapperConverterUtil() {
	}
	
private static DozerBeanMapper mapper = new DozerBeanMapper();
	
	
	/**
	 * Copy the state from source to the target's state
	 * @param source {@literal <S>} class as base object
	 * @param target {@literal <T>} class as destination object
	 */
	public static <S,T> void copy(S source, T target){
		if (source != null) {
			mapper.map(source, target);
		}
	}

	/**
	 * Copy the state from source to the target's state
	 * @param source {@literal <S>} class as base object
	 * @param targetType {@literal <T>} class type as destination object
	 * @return a instance of {@literal <T>} containing a copy of source state.
	 */
	public static <S,T> T copy(S source, Class<T> targetType){
		T copy = null;
		if (source != null) {
			copy = mapper.map(source, targetType);
		}
		return copy;
	}
	
	/**
	 * Copy the state from source to the target's state
	 * @param source {@literal <S>} class as base object
	 * @param targetType {@literal <T>} class type as destination object
	 * @return a list of {@literal <T>} containing a copy of source state.
	 */
	public static <S, T> List<T> copy(List<S> source, Class<T> targetType) {
		List<T> copy = null;
		
		if (source != null) {
			copy = new ArrayList<T>();
			for (S s : source) {
				copy.add(copy(s, targetType));
			}
		}
	    return copy;
	}
	
}
