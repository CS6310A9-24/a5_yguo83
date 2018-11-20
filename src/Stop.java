public class Stop {
    private int stop_id;
    private String stop_name;
    private int num_riders;
    private double latitude;
    private double longitude;

    public Stop(int stop_id, String stop_name, int num_riders, double latitude, double longitude) {
        this.stop_id = stop_id;
        this.stop_name = stop_name;
        this.num_riders = num_riders;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getStop_id() {
        return stop_id;
    }

    public void setStop_id(int stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public int getNum_riders() {
        return num_riders;
    }

    public void setNum_riders(int num_riders) {
        this.num_riders = num_riders;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
