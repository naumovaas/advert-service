package ru.tsc.anaumova.advertservice.enumerated;

public enum Status {

    OPEN,
    CLOSED,
    BOOKED;

    public static boolean checkStatus(final String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (final Status status : values()) {
            if (status.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

}
