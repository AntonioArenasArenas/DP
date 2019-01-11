/*
 * StringToProfileConverter.java
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

import repositories.ProfileRepository;
import domain.Profile;

@Component
@Transactional
public class StringToProfileConverter implements Converter<String, Profile> {

	@Autowired
	ProfileRepository	profileRepository;


	@Override
	public Profile convert(final String text) {
		Profile result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.profileRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
