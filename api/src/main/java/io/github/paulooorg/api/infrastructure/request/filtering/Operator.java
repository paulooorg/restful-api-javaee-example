package io.github.paulooorg.api.infrastructure.request.filtering;

import java.util.Arrays;
import java.util.Optional;

public enum Operator {
	LESS(new Less()), LESS_EQUAL(new LessEqual()), EQUAL(new Equal()), GREATER_EQUAL(new GreaterEqual()), 
	GREATER(new Greater()), NOT_EQUAL(new NotEqual()), BETWEEN(new Between()), IN(new In()), CONTAINS(new Contains());

	private PredicateCreator predicateCreator;

	private Operator(PredicateCreator predicateCreator) {
		this.predicateCreator = predicateCreator;
	}

	public static Operator parse(String operator) {
		Optional<Operator> operatorFound = Arrays.asList(Operator.values()).stream()
				.filter(o -> o.name().replace("_", "").toLowerCase().equals(operator.toLowerCase())).findFirst();
		if (operatorFound.isPresent()) {
			return operatorFound.get();
		}
		//TODO: Exception
		return EQUAL;
	}

	public PredicateCreator getPredicateCreator() {
		return predicateCreator;
	}
}
