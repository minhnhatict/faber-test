package faber.codetest.nhat.enumeration;

public enum FlightReturnTypeEnum {
    ONE_WAY("one_way"),
    ROUND_TRIP("round_trip");

    private String value;

    FlightReturnTypeEnum(String value) {
        this.value = value;
    }

    /**
     * find UserOriginEnum by value
     *
     * @param value
     * @return
     */
    public static FlightReturnTypeEnum findByValue(String value) {
        if (value.equalsIgnoreCase(ONE_WAY.getValue())) {
            return ONE_WAY;
        }

        if (value.equalsIgnoreCase(ROUND_TRIP.getValue())) {
            return ROUND_TRIP;
        }

        return null;
    }

    public String getValue() {
        return value;
    }
}
