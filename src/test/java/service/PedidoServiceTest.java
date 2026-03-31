package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vallegrande.edu.pe.service.PedidoService;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoServiceTest {

    private PedidoService service;

    @BeforeEach
    public void setUp() {
        service = new PedidoService();
    }

    @Test
    public void testClienteVIP() {
        double total = service.calcularTotal(100, 2, "VIP");
        assertEquals(160, total, 0.01);
    }

    @Test
    public void testClienteRegular() {
        double total = service.calcularTotal(100, 2, "REGULAR");
        assertEquals(180, total, 0.01);
    }

    @Test
    public void testClienteNormal() {
        double total = service.calcularTotal(100, 2, "NORMAL");
        assertEquals(200, total, 0.01);
    }

    @Test
    public void testSinDescuentoTipoDesconocido() {
        double total = service.calcularTotal(100, 2, "OTRO");
        assertEquals(200, total, 0.01);
    }

    @Test
    public void testDescuentoAdicionalClienteRegular() {
        double total = service.calcularTotal(100, 6, "REGULAR");
        assertEquals(520, total, 0.01);
    }

    @Test
    public void testDescuentoAdicionalClienteVIP() {
        double total = service.calcularTotal(100, 7, "VIP");
        assertEquals(540, total, 0.01);
    }

    @Test
    public void testSinDescuentoAdicionalMontoJusto500() {
        double total = service.calcularTotal(250, 2, "NORMAL");
        assertEquals(500, total, 0.01);
    }

    @Test
    public void testConDescuentoAdicionalMonto501() {
        double total = service.calcularTotal(250.5, 2, "NORMAL");
        assertEquals(481, total, 0.01);
    }

    @Test
    public void testTipoClienteNull() {
        double total = service.calcularTotal(100, 2, null);
        assertEquals(200, total, 0.01);
    }

    @Test
    public void testTipoClienteMinusculas() {
        double total = service.calcularTotal(100, 2, "vip");
        assertEquals(160, total, 0.01);
    }

    @Test
    public void testPrecioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularTotal(-100, 2, "VIP");
        });
    }

    @Test
    public void testCantidadCeroLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularTotal(100, 0, "VIP");
        });
    }

    @Test
    public void testCantidadNegativaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularTotal(100, -5, "VIP");
        });
    }

    @Test
    public void testPrecioDecimalClienteVIP() {
        double total = service.calcularTotal(99.99, 3, "VIP");
        assertEquals(239.976, total, 0.01);
    }

    @Test
    public void testCantidadGrandeClienteRegular() {
        double total = service.calcularTotal(50, 15, "REGULAR");
        assertEquals(655, total, 0.01);
    }
}