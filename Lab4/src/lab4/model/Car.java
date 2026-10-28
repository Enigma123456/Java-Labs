package lab4.model;

public abstract class Car<T extends Human> extends Vehicle<T> {
    public Car(int maxSeats) {
        super(maxSeats);
    }
}
