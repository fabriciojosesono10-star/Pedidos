package com.tienda.adapter;

public class FacturaAdapter implements FacturaService {
    private LegacyBillingSystem legacySystem = new LegacyBillingSystem();

    @Override
    public void generarFactura(String cliente, double total) {
        // Adaptación: Llama al método no compatible
        legacySystem.createInvoice(cliente, total);
    }
}