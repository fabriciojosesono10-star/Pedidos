package com.tienda.service;

public class ValidadorStock {
    public boolean validar(String producto, int cantidad) {
        int stockDisponible = 10; // simulamos 10 unidades en stock
        if (cantidad <= 0) {
            System.out.println("Error: cantidad invalida.");
            return false;
        }
        if (cantidad > stockDisponible) {
            System.out.println("Error: stock insuficiente.");
            return false;
        }
        return true;
    }
}