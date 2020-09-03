package io.github.paulooorg.api.infrastructure.request.filtering;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;

public enum Operator {
	LESS(new Less()), LESS_EQUAL(new LessEqual()), EQUAL(new Equal()), GREATER_EQUAL(new GreaterEqual()), 
	GREATER(new Greater()), NOT_EQUAL(new NotEqual()), BETWEEN(new Between()), IN(new In()), CONTAINS(new Contains());

	private PredicateCreator predicateCreator;

	private Operator(PredicateCreator predicateCreator) {
		this.predicateCreator = predicateCreator;
	}

	public static Operator parse(String operator) {
		Optional<Operator> operatorFound = Arrays.asList(Operator.values()).stream()
				.filter(operatorEnum -> operatorEnum.name().replace("_", "").toLowerCase().equals(operator.toLowerCase())).findFirst();
		if (operatorFound.isPresent()) {
			return operatorFound.get();
		}
		throw ApiExceptions.operatorNotFound(getPossibleOperators());
	}

	private static String getPossibleOperators() {
		List<String> operators = Arrays.asList(Operator.values()).stream().map(operator -> operator.name().replace("_", "").toUpperCase()).collect(Collectors.toList());
		String possibleOperators = operators.stream().reduce("", (accumulator, operator) -> accumulator += operator + ", ");
		return possibleOperators.substring(0, possibleOperators.length() - 2);
	}

	public PredicateCreator getPredicateCreator() {
		return predicateCreator;
	}
}
