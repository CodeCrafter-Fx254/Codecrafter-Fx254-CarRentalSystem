import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class RentalAgency {
    private List<Car> cars;
    private List<Customer> customers;
    private List<RentalTransaction> transactions;
    private int transactionCounter; // For unique transaction IDs

    public RentalAgency() {
        this.cars = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.transactionCounter = 0;
    }

    // Car Management
    public void addCar(Car car) {
        cars.add(car);
        System.out.println("Car added: " + car.getLicensePlate());
    }

    public Car getCar(String licensePlate) {
        for (Car car : cars) {
            if (car.getLicensePlate().equals(licensePlate)) {
                return car;
            }
        }
        return null; // Car not found
    }

    public List<Car> getAvailableCars() {
        return cars.stream()
                   .filter(Car::isAvailable)
                   .collect(Collectors.toList());
    }

    // Customer Management
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added: " + customer.getName());
    }

    public Customer getCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    // Rental Transactions
    public RentalTransaction rentCar(String customerId, String licensePlate, LocalDate startDate, LocalDate endDate) {
        Customer customer = getCustomer(customerId);
        Car car = getCar(licensePlate);

        if (customer == null) {
            System.out.println("Error: Customer with ID " + customerId + " not found.");
            return null;
        }
        if (car == null) {
            System.out.println("Error: Car with license plate " + licensePlate + " not found.");
            return null;
        }
        if (!car.isAvailable()) {
            System.out.println("Error: Car " + licensePlate + " is not available for rental.");
            return null;
        }
        if (startDate.isAfter(endDate)) {
            System.out.println("Error: Start date cannot be after end date.");
            return null;
        }

        car.setAvailable(false); // Mark car as rented
        transactionCounter++;
        String transactionId = "TXN" + String.format("%04d", transactionCounter);
        RentalTransaction transaction = new RentalTransaction(transactionId, car, customer, startDate, endDate);
        transactions.add(transaction);
        System.out.println("Car rented successfully: " + transaction);
        return transaction;
    }

    public boolean returnCar(String transactionId) {
        for (RentalTransaction transaction : transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                Car car = transaction.getRentedCar();
                car.setAvailable(true); // Mark car as available
                System.out.println("Car returned for transaction ID: " + transactionId);
                return true;
            }
        }
        System.out.println("Error: Transaction ID " + transactionId + " not found.");
        return false;
    }

    public List<RentalTransaction> getAllTransactions() {
        return new ArrayList<>(transactions); // Return a copy to prevent external modification
    }
}
