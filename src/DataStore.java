import java.util.HashMap;

public class DataStore {
    private HashMap<String, DataEntry> data;

    public DataStore() {
        this.data = new HashMap<>();
    }

    public String set(String key, String value) {
        this.data.put(key, new DataEntry(value));
        return "OK";
    }

    public String get(String key) {
        DataEntry entry = this.data.get(key);
        if (entry == null) {
            return "(nil)";
        }
        else if(entry.isExpired()){
            this.data.remove(key);
            return "(nil)";
        }
        return entry.getValue();
    }

    public int expire(String key, int seconds){
        DataEntry entry = this.data.get(key);
        if (entry == null) {
            return 0;
        }
        else if (entry.isExpired()) {
            this.data.remove(key);
            return 0;
        }
        else{
            long newExpiry = System.currentTimeMillis() + (seconds * 1000L);
            entry.setExpiryTime(newExpiry);
            return 1;
        }
    }

    public long ttl(String key) {
        DataEntry entry = this.data.get(key);

        if (entry == null) {
            return -2;
        }
        else if (entry.isExpired()) {
            this.data.remove(key);
            return -2;
        }
        else if (entry.getExpiryTime() == -1) {
            return -1;
        }

        long ttl = (entry.getExpiryTime() - System.currentTimeMillis()) / 1000;

        return ttl > 0 ? ttl : 0;
    }

    public int del(String key) {
        DataEntry removed = this.data.remove(key);

        if (removed != null && !removed.isExpired()) {
            return 1;
        }
        return 0;
    }
}
