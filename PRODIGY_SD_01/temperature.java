import java.util.Scanner;

public class TemperatureConverter {

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }

    public static void temperatureConversion() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to the Temperature Conversion Program!");
            System.out.println("Choose the original unit of measurement:");
            System.out.println("1. Celsius\n2. Fahrenheit\n3. Kelvin");

            try {
                System.out.print("Enter your choice (1/2/3): ");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    double tempValue;
                    while (true) {
                        System.out.print("Enter the temperature value in Celsius: ");
                        tempValue = scanner.nextDouble();
                        System.out.print("You entered " + tempValue + " \u00b0C. Is this correct? (yes/no): ");
                        String confirm = scanner.next().toLowerCase();
                        if (confirm.equals("yes")) break;
                    }
                    double fahrenheit = celsiusToFahrenheit(tempValue);
                    double kelvin = celsiusToKelvin(tempValue);
                    System.out.printf("\n%.2f \u00b0C is:\n%.2f \u00b0F\n%.2f K\n", tempValue, fahrenheit, kelvin);
                } else if (choice == 2) {
                    System.out.print("Enter the temperature value in Fahrenheit: ");
                    double tempValue = scanner.nextDouble();
                    double celsius = fahrenheitToCelsius(tempValue);
                    double kelvin = fahrenheitToKelvin(tempValue);
                    System.out.printf("\n%.2f \u00b0F is:\n%.2f \u00b0C\n%.2f K\n", tempValue, celsius, kelvin);
                } else if (choice == 3) {
                    System.out.print("Enter the temperature value in Kelvin: ");
                    double tempValue = scanner.nextDouble();
                    double celsius = kelvinToCelsius(tempValue);
                    double fahrenheit = kelvinToFahrenheit(tempValue);
                    System.out.printf("\n%.2f K is:\n%.2f \u00b0C\n%.2f \u00b0F\n", tempValue, celsius, fahrenheit);
                } else {
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numeric values.");
                scanner.next(); // Clear invalid input
            }

            System.out.print("\nDo you want to convert another temperature? (yes/no): ");
            String again = scanner.next().toLowerCase();
            if (!again.equals("yes")) {
                System.out.println("Thank you for using the Temperature Conversion Program!");
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        temperatureConversion();
    }
}
