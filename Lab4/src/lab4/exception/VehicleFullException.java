package lab4.exception;

public class VehicleFullException extends RuntimeException {
    public VehicleFullException(String message) {
        super(message);
    }
}
