package io.github.paulooorg.api.infrastructure.request.filtering;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;

public class Filtering {
	private String field;
	
	private Operator operator;
	
	private List<String> values;

	public static List<Filtering> create(UriInfo uriInfo) {
		List<Filtering> filteringResult = new ArrayList<>();
		List<String> filtering = uriInfo.getQueryParameters().get("filter");
		if (filtering != null) {
			for (String filter : filtering) {
				String[] fieldOperatorAndValues = filter.split(",");
				String field = fieldOperatorAndValues[0].trim();
				String operator = fieldOperatorAndValues[1].trim();
				List<String> values = new ArrayList<>();
				for (int i = 2; i < fieldOperatorAndValues.length; i++) {
					values.add(fieldOperatorAndValues[i]);
				}
				filteringResult.add(new Filtering(field, Operator.parse(operator), values));
			}
		}
		return filteringResult;
	}
	
	public Filtering(String field, Operator operator, List<String> values) {
		this.field = field;
		this.operator = operator;
		this.values = values;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
}
