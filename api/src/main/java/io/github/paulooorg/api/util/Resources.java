package io.github.paulooorg.api.util;

import javax.ws.rs.Path;

public class Resources {
	public static <T> String getPathValue(Class<T> resourceClass) {
		Path path = resourceClass.getAnnotation(Path.class);
		if (path != null) {
			return path.value();
		}
		return "Annotation Path Not Found";
	}
}
