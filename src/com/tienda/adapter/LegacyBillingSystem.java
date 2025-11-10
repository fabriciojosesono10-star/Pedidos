package com.tienda.adapter;

public class LegacyBillingSystem {
    public void createInvoice(String clientName, double totalAmount) {
        // Uso de String.format aquí asegura que el mensaje del Adapter también tenga 2 decimales
        System.out.println("[LegacyBilling] Factura generada para " + clientName + " por S/ " + String.format("%.2f", totalAmount));
    }
}