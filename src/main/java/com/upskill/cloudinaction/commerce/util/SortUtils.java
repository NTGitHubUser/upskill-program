package com.upskill.cloudinaction.commerce.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Sort;

import java.util.Optional;


public class SortUtils
{
	public static Optional<Sort> buildSortOption(String sortField, String sortOrder)
	{
		if (Strings.isNotBlank(sortField))
		{
			return Sort.Direction.fromOptionalString(sortOrder)
					.map(sort -> Sort.by(sort, sortField))
					.or(() -> Optional.of(Sort.by(Sort.DEFAULT_DIRECTION, sortField)));
		}
		return Optional.empty();
	}
}
