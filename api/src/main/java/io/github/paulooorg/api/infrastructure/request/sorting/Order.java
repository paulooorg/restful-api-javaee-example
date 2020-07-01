package io.github.paulooorg.api.infrastructure.request.sorting;

import java.util.Arrays;

public enum Order {
	ASC(false, new Asc()), DESC(true, new Desc());
	
	private boolean defaultOrder;

	private OrderCreator orderCreator;
	
	private Order(boolean defaultOrder, OrderCreator orderCreator) {
		this.defaultOrder = defaultOrder;
		this.orderCreator = orderCreator;
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
	
	public OrderCreator getOrderCreator() {
		return orderCreator;
	}
}
