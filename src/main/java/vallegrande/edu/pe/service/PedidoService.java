package vallegrande.edu.pe.service;

import vallegrande.edu.pe.model.TipoCliente;

public class PedidoService {

    private static final double MONTO_MINIMO_DESCUENTO_ADICIONAL = 500.0;
    private static final double DESCUENTO_ADICIONAL = 20.0;

    public double calcularTotal(double precio, int cantidad, String tipoCliente) {
        validarParametros(precio, cantidad);
        
        TipoCliente tipo = TipoCliente.fromString(tipoCliente);
        double total = calcularSubtotal(precio, cantidad);
        total = aplicarDescuentoPorTipoCliente(total, tipo);
        total = aplicarDescuentoAdicional(total);
        
        return total;
    }

    private void validarParametros(double precio, int cantidad) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
    }

    private double calcularSubtotal(double precio, int cantidad) {
        return precio * cantidad;
    }

    private double aplicarDescuentoPorTipoCliente(double total, TipoCliente tipo) {
        return total * (1 - tipo.getDescuento());
    }

    private double aplicarDescuentoAdicional(double total) {
        if (total > MONTO_MINIMO_DESCUENTO_ADICIONAL) {
            return total - DESCUENTO_ADICIONAL;
        }
        return total;
    }
}