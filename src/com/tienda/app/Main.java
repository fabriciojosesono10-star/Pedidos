package com.tienda.app;

import com.tienda.facade.PedidoFacade;
import com.tienda.strategy.IGV18Strategy;
import com.tienda.strategy.ExoneradoStrategy;
import com.tienda.strategy.ImpuestoStrategy;
import com.tienda.model.Pedido; // Importación necesaria para la corroboración
import java.util.Scanner;
import java.util.List; // Importación necesaria para la corroboración

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PedidoFacade facade = new PedidoFacade();

        // 1. Selección de Estrategia de Impuestos (Strategy Pattern)
        System.out.println("--- Proceso de Pedido ---");
        System.out.println("Seleccione la estrategia de impuestos:");
        System.out.println("1. IGV (18%)");
        System.out.println("2. Exonerado (0%)");
        System.out.print("Opcion: ");
        int opcionImpuesto = sc.nextInt();
        sc.nextLine(); // Consumir salto de línea

        ImpuestoStrategy selectedStrategy = null;
        switch (opcionImpuesto) {
            case 1:
                selectedStrategy = new IGV18Strategy();
                System.out.println("Estrategia seleccionada: IGV (18%)");
                break;
            case 2:
                selectedStrategy = new ExoneradoStrategy();
                System.out.println("Estrategia seleccionada: Exonerado (0%)");
                break;
            default:
                selectedStrategy = new IGV18Strategy();
                System.out.println("Opcion invalida. Usando IGV (18%) por defecto.");
        }

        facade.setImpuestoStrategy(selectedStrategy);

        // 2. Captura de datos del Pedido
        System.out.print("Ingrese nombre del cliente: ");
        String cliente = sc.nextLine();
        
        System.out.print("Ingrese producto (laptop/teclado/mouse): ");
        String producto = sc.nextLine().toLowerCase().trim(); 
        
        System.out.print("Ingrese cantidad: ");
        int cantidad = sc.nextInt();

        double precioUnitario = obtenerPrecio(producto);

        if (precioUnitario == 0) {
            System.out.println("Producto no registrado en el sistema.");
            return;
        }

        // 3. Procesamiento del Pedido (Uso del Facade)
        facade.procesarPedido(producto, cantidad, precioUnitario, cliente);

        // =========================================================
        // === PASO DE CORROBORACIÓN DEL PATRÓN REPOSITORY (NUEVO) ===
        // =========================================================
        System.out.println("\n--- CORROBORACIÓN: Datos Guardados en el Repository ---");
        
        // 1. Obtiene la lista de pedidos guardados a través del Facade
        List<Pedido> pedidosGuardados = facade.obtenerPedidosGuardados(); 

        if (pedidosGuardados.isEmpty()) {
            System.out.println("No se guardó ningún pedido.");
        } else {
            System.out.println("Total de pedidos guardados: " + pedidosGuardados.size());
            // 2. Imprime el primer pedido guardado, usando String.format para el precio
            Pedido primerPedido = pedidosGuardados.get(0);
            System.out.println("Detalles del primer pedido: " + primerPedido.getProducto() + 
                               " | Subtotal: S/ " + String.format("%.2f", primerPedido.getSubtotal()));
        }
        System.out.println("-----------------------------------------------------");
    }

    private static double obtenerPrecio(String producto) {
        switch (producto) {
            case "laptop": return 4000;
            case "teclado": return 120;
            case "mouse": return 80;
            default: return 0;
        }
    }
}