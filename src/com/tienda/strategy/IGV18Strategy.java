package com.tienda.strategy;

public class IGV18Strategy implements ImpuestoStrategy {
    @Override
    public double calcular(double subtotal) {
        return subtotal * 0.18;
    }
}