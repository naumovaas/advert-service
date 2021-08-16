package ru.tsc.anaumova.advertservice.enumerated;

public enum Status {

    OPEN("Открыто"),
    CLOSED("Закрыто"),
    BOOKED("Забронировано");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
