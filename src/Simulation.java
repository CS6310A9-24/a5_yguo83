import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Simulation {

    private PriorityQueue<Event> events;
    private Map<Integer, Bus> buses;
    private Map<Integer, Route> routes;
    private Map<Integer, Stop> stops;

    public Simulation() {
        this.events = new PriorityQueue<>();
        this.buses = new HashMap<>();
        this.routes = new HashMap<>();
        this.stops = new HashMap<>();
    }

    public void add_stop(int stop_id, String stop_name, int num_riders, double latitude, double longitude) {
        stops.put(stop_id, new Stop(stop_id, stop_name, num_riders, latitude, longitude));
    }

    public void add_route(int route_id, int route_number, String route_name) {
        routes.put(route_id, new Route(route_id, route_number, route_name));
    }

    public void extend_route(int route_id, int stop_id) {
        if (!routes.containsKey(route_id)) {
            throw new RuntimeException("Route does not exist"); //TODO add route_id arg to RuntimeException
        }
        routes.get(route_id).extend(stop_id);
    }

    public void add_bus(int bus_id, int route_id, int location, int init_passengers, int passenger_capacity, int speed) {
        buses.put(bus_id, new Bus(bus_id, route_id, location, init_passengers, passenger_capacity, speed));
    }

    public void add_event(int time, String type, int object_id) {
        events.add(new Event(time, type, object_id));
    }

    public boolean containsEvents() {
        return !events.isEmpty();
    }

    /*
    * executes the event with the lowest logical time and removes that event from the event queue
    * precondition: none
    * postcondition:
        1. the system processes the event based on its event type
        2. the system removes the event from the event queue
    * if the event queue is empty, this function immediately returns to the caller
    * this function exists in case client requires support for other types of events
    */
    public void executeNextEvent() {
        if (!containsEvents()) { //keep duplicate check in case someone uses this method improperly
            System.out.println("trying to process an event when there are no more events in the event queue");
            return;
        }
        Event e = events.peek();
        // error handling for events with null attributes
        if (e.getRank() == null || e.getType() == null || e.getObject_id() == null) {
            System.out.println("one or more of event's fields are null");
            System.exit(1);
        }
        // determine the type of the event
        if (e.getType().equals("move_bus")) {
            executeMoveBusEvent(e);
        } else {
            System.out.println("got an event type that the system is not prepared to handle: " + e.getType());
            System.exit(1);
        }
        // remove the event after it has been processed
        events.remove();
    }

    /*
    * determine bus's next stop, calculates travel time to next stop, and updates the system state with a new event
    * the new event represents the arrival of the bus at its next stop
    * precondition:
        1. event fields (rank, type, and object_id) are not null
        2. event type is "move_bus"
    * postcondition:
        1. bus location is incremented
        2. new move_bus event is added to queue with rank equal to the arrival time of the bus at its destination
     */
    private void executeMoveBusEvent(Event event) {
        //increment bus location
        int bus_id = event.getObject_id();
        Bus bus = buses.get(bus_id);
        int current_location = bus.getLocation();
        int route_id = bus.getRoute_id();
        Route route = routes.get(route_id);
        int route_len = route.getRouteLength();
        int next_location = (current_location + 1) % route_len;
        bus.setLocation(next_location);

        //calculate travel distance
        int current_stop_id = route.getStopIdAt(current_location);
        Stop current_stop = stops.get(current_stop_id);
        double current_stop_latitude = current_stop.getLatitude();
        double current_stop_longitude = current_stop.getLongitude();
        int next_stop_id = route.getStopIdAt(next_location);
        Stop next_stop = stops.get(next_stop_id);
        double next_stop_latitude = next_stop.getLatitude();
        double next_stop_longitude = next_stop.getLongitude();

        //I use Double here instead of double because only the former has a .intValue() method
        Double distance = 70.0 * Math.sqrt(
                Math.pow(next_stop_latitude - current_stop_latitude, 2) +
                Math.pow(next_stop_longitude - current_stop_longitude, 2));

        //calculate travel time
        int travel_time = 1 + (distance.intValue() * 60 / bus.getSpeed());

        int new_event_rank = event.getRank() + travel_time;
        //display output line of text for new event
        System.out.println("t:" + event.getRank() + "b:" + bus.getBus_id() + "->s:" + next_stop_id + "@" + new_event_rank + "//p:0/f:0");

        //create new Event and add to event queue
        Event e = new Event(new_event_rank, "move_bus", bus_id);
        events.add(e);
    }
}
