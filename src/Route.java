import java.util.ArrayList;
import java.util.List;

public class Route {

    private int route_id;
    private int route_number;
    private String route_name;
    private List<Integer> stop_ids;

    public Route(int route_id, int route_number, String route_name) {
        this.route_id = route_id;
        this.route_number = route_number;
        this.route_name = route_name;
        this.stop_ids = new ArrayList<Integer>();
    }

    public void extend(int stop_id) {
        stop_ids.add(stop_id);
    }

    public int getRouteLength() {
        return this.stop_ids.size();
    }

    public int getStopIdAt(int location) {
        return this.stop_ids.get(location);
    }
}
