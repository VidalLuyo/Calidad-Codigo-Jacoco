package vallegrande.edu.pe.model;

public enum TipoCliente {
    VIP(0.20),
    REGULAR(0.10),
    NORMAL(0.0);

    private final double descuento;

    TipoCliente(double descuento) {
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public static TipoCliente fromString(String tipo) {
        if (tipo == null) {
            return NORMAL;
        }
        try {
            return TipoCliente.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NORMAL;
        }
    }
}
