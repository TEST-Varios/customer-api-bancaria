package cl.customer.customerapi.model.enums;

public enum Status {
    ACTIVO(true, "activo"),
    BLOQUEADO(false, "bloqueado");

    private boolean isActive;
    private String description;

    private Status(boolean isActive, String description) {
        this.isActive = isActive;
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getDescription() {
        return description;
    }
}
