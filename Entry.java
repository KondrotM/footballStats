package uk.ac.glos.ct5025.s1804317.footballStats;

public class Entry {

    private String time;
    private String action;
    // String[] as multiple data needs to be sent sometimes
    private String[] data;
    private String output;

    public Entry(String time, String action, String[] data, String output) {
        this.time = time;
        this.action = action;
        this.data = data;
        this.output = output;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    public String[] getData() {
        return data;
    }

    public String getOutput() {
        return output;
    }
}
