package lab2;

public class JournalRecord {
    private String lastName;
    private String firstName;
    private String birthDate;
    private String phoneNumber;
    private String street;
    private String building;
    private String apartment;

    public JournalRecord(String lastName, String firstName, String birthDate,
                         String phoneNumber, String street, String building, String apartment) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return String.format("Студент: %s %s | Дата народження: %s | Тел: %s | Адреса: вул. %s, буд. %s, кв. %s",
                lastName, firstName, birthDate, phoneNumber, street, building, apartment);
    }
}