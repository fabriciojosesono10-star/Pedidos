package com.tienda.model;

import com.tienda.strategy.ImpuestoStrategy;

public class Pedido {
    private String producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double igv;
    private double total;
    private ImpuestoStrategy impuestoStrategy; 

    // Constructor que recibe la estrategia
    public Pedido(String producto, int cantidad, double precioUnitario, ImpuestoStrategy impuestoStrategy) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.impuestoStrategy = impuestoStrategy;
        calcularTotales();
    }

    private void calcularTotales() {
        subtotal = cantidad * precioUnitario;
        // Uso de la estrategia
        igv = impuestoStrategy.calcular(subtotal);
        total = subtotal + igv;
    }

    public String getProducto() { return producto; }
    public double getSubtotal() { return subtotal; }
    public double getIgv() { return igv; }
    public double getTotal() { return total; }
}