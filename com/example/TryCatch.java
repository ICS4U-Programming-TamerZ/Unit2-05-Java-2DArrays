package com.example;

/**
 * Prints Hello world with Style.
 *
 * @author Tamer
 * @version 1.0
 * @since 2024-02-16
 */
import java.util.Scanner;

// Class
public final class TryCatch {

  /** This is a private constructor used to prevent instantiation. */
  private TryCatch() {
    throw new UnsupportedOperationException("Cannot be instantiated.");
  }

  /**
   * This is the main method.
   *
   * @param args Unused
   */
  public static void main(final String[] args) {
    // Declaring Volume Radius Pi Decimal shift and fraction variables.
    double volume, radius, pi = StrictMath.PI;
    double decimalShift = 1000d;
    double fraction = (4d / 3d);
    // Starts Scanner.
    Scanner sc = new Scanner(System.in);

    // Try Catch.
    try {
      // Output to Console.
      System.out.println("This program will help you calculate the volume of a sphere.");
      System.out.println("What is your radius? (U^1) (Units): ");
      // Casts Radius to double.
      radius = sc.nextDouble();
      // Checks if radius is greater than 0.
      if (radius > 0d) {
        // Calculates Volume.
        volume = fraction * (pi) * (StrictMath.pow(radius, 3));
        // Rounds Volume.
        double volumeRounded = (Math.round(volume * decimalShift)) / decimalShift;
        // Output Volume to Console.
        System.out.println("Your Volume is: " + volumeRounded + " (Units Cubed) (U^3)");
      } else {
        // If Number is not positive.
        System.out.println("Must be positive");
      }
      // Any/all exceptions.
    } catch (Exception e) {
      // Output any error message.
      System.out.println("Error " + e.getMessage());
    }
    sc.close();
  }
}
