public class Car {
    private String make;
    private String model;
    private String licensePlate;
    private double dailyRentalRate;
    private boolean isAvailable;

    public Car(String make, String model, String licensePlate, double dailyRentalRate) {
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.dailyRentalRate = dailyRentalRate;
        this.isAvailable = true; // Initially available
    }

    // Getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getDailyRentalRate() {
        return dailyRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setters (consider if all should be public, or if changes should be via methods)
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Car [Make=" + make + ", Model=" + model + ", License Plate=" + licensePlate + ", Rate=" + dailyRentalRate + ", Available=" + isAvailable + "]";
    }
}
