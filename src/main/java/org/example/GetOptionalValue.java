package org.example;

import java.util.Optional;
import java.util.stream.Stream;

public class GetOptionalValue {
  public Integer getOptionalValue(int randomNumber) {
    Optional<Integer> optional = generateRandomOptional(randomNumber);
    return optional.orElseThrow();
  }

  public Optional<Integer> generateRandomOptional(int randomNumber) {
    return Stream.of(randomNumber)
            .filter(i -> i % 2 == 1)
            .findFirst();
  }
}