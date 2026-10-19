public class DataEntry {
    private String value;
    private long expiryTime;

    public DataEntry(String value) {
        this.value = value;
        this.expiryTime = -1; // -1 indicates no expiry
    }

    public String getValue() {
        return value;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public long setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
        return expiryTime;
    }

    public boolean isExpired() {
        if (expiryTime == -1) {
            return false; // No expiry
        }
        else{
            if (System.currentTimeMillis() > expiryTime) {
                return true; // Expired
            }
            else{
                return false; // Not expired
            }
        }
    }
}
