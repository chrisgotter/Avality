import java.util.Map;
import java.util.TreeMap;

public class Company  {
    private final String name;
    private final Map<String, Customer> customers;
    public Company(String name) {
        this.name = name;
        this.customers = new TreeMap<>();
    }
    public Company updateCustomer(String userId, String firstName, String lastName, String version) {
        Customer c = customers.get(userId);
        if (c == null) {
            customers.put(userId, new Customer(userId, firstName, lastName, version));
        } else if(c.getVersion().compareTo(version) < 0) {
            c.setVersion(version);
        }
        return this;
    }
    public String getName() {
        return name;
    }
    public Map<String, Customer> getCustomers() {
        return customers;
    }
    
}
