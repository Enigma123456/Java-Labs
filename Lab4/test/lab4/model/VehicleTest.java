package lab4.model;

import lab4.exception.PassengerNotFoundException;
import lab4.exception.VehicleFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    @Test
    void taxiAcceptsAnyPassengerType() {
        Taxi taxi = new Taxi(4);
        taxi.boardPassenger(new OrdinaryPassenger("Іван"));
        taxi.boardPassenger(new Firefighter("Василь"));
        taxi.boardPassenger(new Policeman("Дмитро"));

        assertEquals(3, taxi.getOccupiedSeats());
        assertEquals(4, taxi.getMaxSeats());
    }

    @Test
    void busAcceptsAnyPassengerType() {
        Bus bus = new Bus(10);
        bus.boardPassenger(new OrdinaryPassenger("Іван"));
        bus.boardPassenger(new Firefighter("Василь"));
        bus.boardPassenger(new Policeman("Дмитро"));

        assertEquals(3, bus.getOccupiedSeats());
    }

    @Test
    void fireEngineAcceptsFirefighters() {
        FireEngine engine = new FireEngine(2);
        engine.boardPassenger(new Firefighter("Василь"));

        assertEquals(1, engine.getOccupiedSeats());
        // engine.boardPassenger(new Policeman("X"));  // не скомпілюється
    }

    @Test
    void policeCarAcceptsPolicemen() {
        PoliceCar car = new PoliceCar(2);
        car.boardPassenger(new Policeman("Дмитро"));

        assertEquals(1, car.getOccupiedSeats());
        // car.boardPassenger(new Firefighter("X"));  // не скомпілюється
    }

    @Test
    void boardingWhenFullThrowsException() {
        PoliceCar car = new PoliceCar(1);
        car.boardPassenger(new Policeman("Дмитро"));

        assertThrows(VehicleFullException.class,
                () -> car.boardPassenger(new Policeman("Олексій")));
        assertEquals(1, car.getOccupiedSeats());
    }

    @Test
    void alightAbsentPassengerThrowsException() {
        FireEngine engine = new FireEngine(2);

        assertThrows(PassengerNotFoundException.class,
                () -> engine.alightPassenger(new Firefighter("Андрій")));
    }

    @Test
    void invalidSeatCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Taxi(0));
    }

    @Test
    void alightRemovesPassenger() {
        Bus bus = new Bus(5);
        Human person = new OrdinaryPassenger("Іван");
        bus.boardPassenger(person);

        bus.alightPassenger(person);

        assertEquals(0, bus.getOccupiedSeats());
    }

    @Test
    void roadCountsPeopleInVehiclesWithDifferentPassengerTypes() {
        Taxi taxi = new Taxi(4);
        taxi.boardPassenger(new OrdinaryPassenger("Іван"));
        taxi.boardPassenger(new Policeman("Дмитро"));

        FireEngine engine = new FireEngine(5);
        engine.boardPassenger(new Firefighter("Василь"));
        engine.boardPassenger(new Firefighter("Андрій"));
        engine.boardPassenger(new Firefighter("Петро"));

        Road road = new Road();
        road.addCarToRoad(taxi);
        road.addCarToRoad(engine);
        road.addCarToRoad(new PoliceCar(2)); // порожня

        assertEquals(5, road.getCountOfHumans());
    }

    @Test
    void emptyRoadHasZeroPeople() {
        assertEquals(0, new Road().getCountOfHumans());
    }
}
