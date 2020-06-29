package io.github.paulooorg.api.infrastructure.request.sorting;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;

public class Sorting {
	private String field;
	
	private Order order = Order.getDefaultOrder();
	
	public static List<Sorting> create(UriInfo uriInfo) {
		List<Sorting> sortingResult = new ArrayList<>();
		List<String> sorting = uriInfo.getQueryParameters().get("sort");
		if (sorting != null) {
			for (String sort : sorting) {
				String[] fieldAndOrder = sort.split(",");
				String field = fieldAndOrder[0].trim();
				Order order = Order.getDefaultOrder();
				if (fieldAndOrder.length > 1) {
					order = Order.parse(fieldAndOrder[1].trim());
				}
				sortingResult.add(new Sorting(field, order));
			}
		}
		return sortingResult;
	}
	
	public Sorting(String field, Order order) {
		this.field = field;
		this.order = order;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Order getOrder() {
		return order;
	}
}
