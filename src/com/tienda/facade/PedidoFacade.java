package com.tienda.facade;

import com.tienda.model.Pedido;
import com.tienda.service.ValidadorStock;
import com.tienda.adapter.FacturaService;
import com.tienda.adapter.FacturaAdapter;
import com.tienda.strategy.ImpuestoStrategy; 
import com.tienda.repository.PedidoRepository; 
import java.util.List; // Importación necesaria para el nuevo método

public class PedidoFacade {
    private ValidadorStock validador = new ValidadorStock();
    private FacturaService facturaService = new FacturaAdapter();
    private PedidoRepository repository = new PedidoRepository(); 
    private ImpuestoStrategy impuestoStrategy; 

    public void setImpuestoStrategy(ImpuestoStrategy strategy) {
        this.impuestoStrategy = strategy;
    }

    public void procesarPedido(String producto, int cantidad, double precioUnitario, String cliente) {
        if (impuestoStrategy == null) {
            System.out.println("Error: No se ha seleccionado una estrategia de impuestos.");
            return;
        }
        
        if (!validador.validar(producto, cantidad)) {
            return;
        }

        Pedido pedido = new Pedido(producto, cantidad, precioUnitario, impuestoStrategy);
        
        System.out.println("Pedido registrado: " + producto + " x" + cantidad);

        // Uso del Repository
        repository.guardar(pedido); 
        
        // Uso del Adapter
        facturaService.generarFactura(cliente, pedido.getTotal());

        mostrarComprobante(cliente, pedido);
    }

    // =========================================================
    // === NUEVO MÉTODO PARA CORROBORACIÓN DEL REPOSITORY ===
    // =========================================================
    public List<Pedido> obtenerPedidosGuardados() {
        // Delega la consulta al Repositorio
        return repository.obtenerTodos(); 
    }

    private void mostrarComprobante(String cliente, Pedido pedido) {
        System.out.println("--- Comprobante para " + cliente + " ---");
        System.out.println("Producto: " + pedido.getProducto());
        System.out.println("Subtotal: S/ " + String.format("%.2f", pedido.getSubtotal()));
        System.out.println("IGV: S/ " + String.format("%.2f", pedido.getIgv()));
        System.out.println("Total: S/ " + String.format("%.2f", pedido.getTotal()));
        System.out.println("---------------------------------");
    }
}