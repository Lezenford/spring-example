package com.lezenford.spring.example.test.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MathService {

    public BigDecimal sum(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }

    public BigDecimal divide(BigDecimal first, BigDecimal second) {
        return first.divide(second, RoundingMode.HALF_UP);
    }
}
