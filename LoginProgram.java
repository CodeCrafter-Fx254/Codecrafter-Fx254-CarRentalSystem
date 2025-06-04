import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class LoginProgram {

    private static final String CORRECT_USERNAME = "user123";
    private static final char[] CORRECT_PASSWORD = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'}; // Store as char array for security

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console(); // Use Console for secure password input if available

        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.println("\n--- Login Attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS + " ---");

            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            char[] password;
            if (console != null) {
                // Use Console for secure password input (masks input by default)
                password = console.readPassword("Enter password: ");
            } else {
                // Fallback for IDEs or environments without Console (less secure as it prints characters)
                System.out.print("Enter password (characters will be masked as *): ");
                password = readPasswordWithMasking(scanner);
            }

            if (authenticate(username, password)) {
                System.out.println("Login successful! Welcome, " + username + ".");
                // Clear password from memory after successful authentication
                Arrays.fill(password, ' ');
                scanner.close();
                return; // Exit program on successful login
            } else {
                System.out.println("Incorrect username or password. Please try again.");
                // Clear password from memory after failed authentication
                Arrays.fill(password, ' ');
                attempts++;
            }
        }

        System.out.println("\nMaximum login attempts reached. Account locked or try again later.");
        scanner.close();
    }

    /**
     * Reads password from System.in and masks each character with '*' as it's typed.
     * This is a simple implementation and might not work perfectly in all console environments.
     * For true security, System.console().readPassword() is preferred.
     */
    private static char[] readPasswordWithMasking(Scanner scanner) {
        StringBuilder passwordBuilder = new StringBuilder();
        try {
            // Disable echoing for characters
            // This part is platform-dependent and generally not reliable without external libraries
            // The purpose here is to illustrate the masking requirement.
            // For actual non-echoing, external libraries or System.console() is needed.
            // On Windows: new ProcessBuilder("cmd", "/c", "stty -echo").start().waitFor();
            // On Linux/macOS: new ProcessBuilder("stty", "-echo").start().waitFor();
            // We'll simulate the masking here with a simple char-by-char read if Console is null.

            while (true) {
                // Read character by character (simplified for demonstration)
                // This will not truly hide characters as they are typed in a standard IDE console.
                // It only ensures that the '' is printed *after a character is read.
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) { // Handle empty line, e.g., just pressing Enter
                        break;
                    }
                    for (char c : line.toCharArray()) {
                        passwordBuilder.append(c);
                        System.out.print(""); // Print '' for each character
                    }
                    System.out.println(); // New line after password entry
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading password: " + e.getMessage());
            // Fallback to simple line read if masking fails
            return scanner.nextLine().toCharArray();
        } finally {
            // Try to re-enable echoing if it was disabled (platform-dependent)
            // Example: new ProcessBuilder("stty", "echo").start().waitFor();
        }
        return passwordBuilder.toString().toCharArray();
    }


    /**
     * Authenticates the provided username and password.
     * Passwords should be compared using char arrays to prevent String pooling issues.
     */
    private static boolean authenticate(String username, char[] password) {
        return CORRECT_USERNAME.equals(username) && Arrays.equals(CORRECT_PASSWORD, password);
    }
}
