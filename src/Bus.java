public class Bus {

    private int bus_id;
    private int route_id;
    private int location;
    private int num_passengers;
    private int passenger_capacity;
    private int speed;

    public Bus(int bus_id, int route_id, int location, int init_passengers, int passenger_capacity, int speed) {
        this.bus_id = bus_id;
        this.route_id = route_id;
        this.location = location;
        this.num_passengers = init_passengers;
        this.passenger_capacity = passenger_capacity;
        this.speed = speed;
    }

    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getNum_passengers() {
        return num_passengers;
    }

    public void setNum_passengers(int init_passengers) {
        this.num_passengers = init_passengers;
    }

    public int getPassenger_capacity() {
        return passenger_capacity;
    }

    public void setPassenger_capacity(int passenger_capacity) {
        this.passenger_capacity = passenger_capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
