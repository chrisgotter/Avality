
public class Customer implements Comparable<Customer>{
    private final String userId;
    private final String firstName;
    private final String lastName;
    private String version;
    public Customer(String userId, String firstName, String lastName, String version) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.version = version;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getUserId() {
        return userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String toCSV() {
        return userId + "," + firstName + "," + lastName + "," + version;
    }
    @Override
    public int compareTo(Customer o) {
        return userId.compareTo(o.userId);
    }
    
}
