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

    public static boolean checkStatus(final String value) {
        if (value == null || value.isEmpty()) return false;
        for (final Status status : values()) {
            if (status.getDisplayName().equals(value)) return true;
        }
        return false;
    }

}
