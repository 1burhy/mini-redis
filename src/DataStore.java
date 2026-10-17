import java.util.HashMap;

public class DataStore {
    private HashMap<String, String> data;

    public DataStore() {
        this.data = new HashMap<>();
    }

    public String set(String key, String value) {
        this.data.put(key, value);
        return "OK";
    }

    public String get(String key) {
        String value = this.data.get(key);
        if (value == null) {
            return "(nil)";
        }
        return value;
    }

    public int del(String key) {
        if (this.data.remove(key) != null) {
            return 1;
        }

        return 0;
    }

}
