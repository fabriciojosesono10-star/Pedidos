package com.tienda.repository;

import com.tienda.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void guardar(Pedido pedido) {
        pedidos.add(pedido);
        // >>> ESTA LÍNEA ES LA ÚNICA RESPONSABLE DE LA SALIDA FALTANTE <<<
        System.out.println("[Repository] Pedido guardado: #" + pedidos.size()); 
    }

    public List<Pedido> obtenerTodos() {
        return new ArrayList<>(pedidos);
    }
}