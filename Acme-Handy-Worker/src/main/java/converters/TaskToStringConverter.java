package converters;



import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Task;

@Component
@Transactional
public class TaskToStringConverter implements Converter<Task, String> {

	@Override
	public String convert(final Task task) {
		String result;

		if (task == null)
			result = null;
		else
			result = String.valueOf(task.getId());

		return result;
	}

}
