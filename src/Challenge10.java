import java.util.ArrayList;
import java.util.List;

public class Challenge10
{
    public static void main(String[] args)
    {
        Car car = new Car(5);
        Truck truck = new Truck(12);

        List<vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(truck);

        System.out.println("car: " + vehicles.get(0).getNumberOfWheels());
        System.out.println("truck: " + vehicles.get(1).getNumberOfWheels());
    }
}

class vehicle
{
    private int numberOfWheels;

    vehicle(int numberOfWheels)
    {
        this.numberOfWheels = numberOfWheels;
    }

    public int getNumberOfWheels()
    {
        return this.numberOfWheels;
    }
}

class Car extends vehicle
{
    Car(int numberOfWheels)
    {
        super(numberOfWheels);
    }
}

class Truck extends vehicle
{
    Truck(int numberOfWheels)
    {
        super(numberOfWheels);
    }
}