package com.lezenford.spring.example.test.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MathServiceTest {
    private final MathService mathService = new MathService();

    @Test
    public void positiveSumTest() {
        final BigDecimal first = BigDecimal.ONE;
        final BigDecimal second = BigDecimal.TEN;
        final BigDecimal sum = mathService.sum(first, second);
        assertThat(sum).isEqualTo(first.add(second));
    }

    @Test
    public void positiveDivideTest() {
        final BigDecimal first = BigDecimal.ONE;
        final BigDecimal second = BigDecimal.TEN;
        final BigDecimal sum = mathService.divide(first, second);
        assertThat(sum).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void divideNyZeroTest() {
        final BigDecimal first = BigDecimal.ZERO;
        final BigDecimal second = BigDecimal.TEN;
        assertThatThrownBy(() -> mathService.divide(second, first)).isInstanceOf(ArithmeticException.class);
    }
}