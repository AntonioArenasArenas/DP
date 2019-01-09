/*
 * StringToTaskConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TaskRepository;
import domain.Task;

@Component
@Transactional
public class StringToTaskConverter implements Converter<String, Task> {

	@Autowired
	TaskRepository	taskRepository;


	@Override
	public Task convert(final String text) {
		Task result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.taskRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
