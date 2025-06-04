import java.time.LocalDate;

public class RentalTransaction {
    private String transactionId;
    private Car rentedCar;
    private Customer customer;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private double totalCost;

    public RentalTransaction(String transactionId, Car rentedCar, Customer customer, LocalDate rentalStartDate, LocalDate rentalEndDate) {
        this.transactionId = transactionId;
        this.rentedCar = rentedCar;
        this.customer = customer;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.totalCost = calculateTotalCost(); // Calculate cost upon creation
    }

    private double calculateTotalCost() {
        long days = java.time.temporal.ChronoUnit.DAYS.between(rentalStartDate, rentalEndDate);
        return rentedCar.getDailyRentalRate() * days;
    }

    // Getters
    public String getTransactionId() {
        return transactionId;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "RentalTransaction [ID=" + transactionId + ", Car=" + rentedCar.getLicensePlate() + ", Customer=" + customer.getName() +
               ", Start Date=" + rentalStartDate + ", End Date=" + rentalEndDate + ", Total Cost=" + totalCost + "]";
    }
}
