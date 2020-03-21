package Domain;

enum ApartmentType {
    SINGLE("Single"),
    DELUX("Delux"),
    SUIT("Suit"),
    PRESIDENTLUX("PresidentLux");

    private final String text;

    ApartmentType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
