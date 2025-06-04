import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        // Add Cars
        agency.addCar(new Car("Toyota", "Camry", "ABC-123", 50.0));
        agency.addCar(new Car("Honda", "Civic", "DEF-456", 40.0));
        agency.addCar(new Car("BMW", "X5", "GHI-789", 100.0));

        // Add Customers
        agency.addCustomer(new Customer("C001", "Alice Smith", "alice@example.com"));
        agency.addCustomer(new Customer("C002", "Bob Johnson", "bob@example.com"));

        System.out.println("\nAvailable Cars:");
        agency.getAvailableCars().forEach(System.out::println);

        // Perform Rentals
        agency.rentCar("C001", "ABC-123", LocalDate.now(), LocalDate.now().plusDays(3));
        agency.rentCar("C002", "DEF-456", LocalDate.now(), LocalDate.now().plusDays(5));
        agency.rentCar("C001", "ABC-123", LocalDate.now(), LocalDate.now().plusDays(2)); // Try to rent an unavailable car

        System.out.println("\nAvailable Cars After Rentals:");
        agency.getAvailableCars().forEach(System.out::println);

        // Return a Car
        agency.returnCar("TXN0001");

        System.out.println("\nAvailable Cars After Return:");
        agency.getAvailableCars().forEach(System.out::println);

        System.out.println("\nAll Transactions:");
        agency.getAllTransactions().forEach(System.out::println);
    }
}
