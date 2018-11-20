public class Event implements Comparable{

    private Integer rank;
    private String type;
    private int object_id;

    public Event(int time, String type, int object_id) {
        this.rank = time;
        this.type = type;
        this.object_id = object_id;
    }

    /*
    * getRank is guaranteed to return a non-null Integer since new Integer() is called from Simulation.java when creating Event objects
    * new Integer() calls Integer.parseInt under the hood, which throws {@code NumberFormatException} if parsed string is null or contains non-digits
     */

    public Integer getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    /**
     * Compare this with another Event o.
     * return -1 if this event's rank is smaller, 0 if equal, 1 if greater than o's rank
     */
    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;
        return this.getRank().compareTo(other.getRank());
    }
}
