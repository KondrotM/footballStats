package uk.ac.glos.ct5025.s1804317.footballStats;

public class Entry {

    private String time;
    private String action;
    private Object data;
    private String output;


    public Entry(String time, String action, Object data, String output) {
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

    public Object getData() {
        return data;
    }

    public String getOutput() {
        return output;
    }
}
