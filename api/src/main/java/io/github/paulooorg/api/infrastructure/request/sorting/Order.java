package io.github.paulooorg.api.infrastructure.request.sorting;

import java.util.Arrays;

public enum Order {
	ASC(false), DESC(true);
	
	private boolean defaultOrder;

	private Order(boolean defaultOrder) {
		this.defaultOrder = defaultOrder;
	}

	public boolean isDefaultOrder() {
		return defaultOrder;
	}
	
	public static Order getDefaultOrder() {
		return Arrays.asList(Order.values()).stream().filter(o -> o.isDefaultOrder()).findFirst().get();
	}
	
	public static Order parse(String order) {
		switch (order.toLowerCase()) {
			case "asc":
				return ASC;
			case "desc":
				return DESC;
			default:
				return Order.getDefaultOrder();
		}
	}
	
	public boolean isASC() {
		return this.equals(ASC);
	}
	
	public boolean isDESC() {
		return this.equals(DESC);
	}
}
