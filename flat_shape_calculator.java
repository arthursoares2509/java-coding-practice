import java.util.*;

interface GeometricShape {
    String getName();
    String[] getParameters();
    double calculate(double... params);
}

class Circle implements GeometricShape {
    public String getName() { return "Circle"; }
    public String[] getParameters() { return new String[]{"radius"}; }
    public double calculate(double... p) { return Math.PI * p[0] * p[0]; }
}

class Rectangle implements GeometricShape {
    public String getName() { return "Rectangle"; }
    public String[] getParameters() { return new String[]{"width", "height"}; }
    public double calculate(double... p) { return p[0] * p[1]; }
}

class Square implements GeometricShape {
    public String getName() { return "Square"; }
    public String[] getParameters() { return new String[]{"side"}; }
    public double calculate(double... p) { return p[0] * p[0]; }
}

class Triangle implements GeometricShape {
    public String getName() { return "Triangle"; }
    public String[] getParameters() { return new String[]{"base", "height"}; }
    public double calculate(double... p) { return 0.5 * p[0] * p[1]; }
}

class Parallelogram implements GeometricShape {
    public String getName() { return "Parallelogram"; }
    public String[] getParameters() { return new String[]{"base", "height"}; }
    public double calculate(double... p) { return p[0] * p[1]; }
}

class Trapezoid implements GeometricShape {
    public String getName() { return "Trapezoid"; }
    public String[] getParameters() { return new String[]{"base A", "base B", "height"}; }
    public double calculate(double... p) { return 0.5 * (p[0] + p[1]) * p[2]; }
}

class Ellipse implements GeometricShape {
    public String getName() { return "Ellipse"; }
    public String[] getParameters() { return new String[]{"semi-major axis a", "semi-minor axis b"}; }
    public double calculate(double... p) { return Math.PI * p[0] * p[1]; }
}

class RegularPolygon implements GeometricShape {
    public String getName() { return "Regular Polygon"; }
    public String[] getParameters() { return new String[]{"number of sides", "side length"}; }
    public double calculate(double... p) { return (p[0] * p[1] * p[1]) / (4.0 * Math.tan(Math.PI / p[0])); }
}

class Sector implements GeometricShape {
    public String getName() { return "Sector"; }
    public String[] getParameters() { return new String[]{"radius", "central angle in degrees"}; }
    public double calculate(double... p) { return Math.PI * p[0] * p[0] * (p[1] / 360.0); }
}

class Annulus implements GeometricShape {
    public String getName() { return "Annulus"; }
    public String[] getParameters() { return new String[]{"outer radius", "inner radius"}; }
    public double calculate(double... p) { return Math.PI * (p[0] * p[0] - p[1] * p[1]); }
}

class InputHelper {
    static double readPositiveDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                double v = Double.parseDouble(s);
                if (v > 0) return v;
            } catch (Exception ignored) {}
            System.out.println("Invalid value. Please enter a positive number.");
        }
    }

    static int readMenuChoice(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v >= min && v <= max) return v;
            } catch (Exception ignored) {}
            System.out.println("Invalid option. Choose between " + min + " and " + max + ".");
        }
    }

    static String readYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().toLowerCase();
            if (s.equals("yes") || s.equals("no")) return s;
            System.out.println("Please answer with 'yes' or 'no'.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<GeometricShape> shapes = Arrays.asList(
                new Circle(),
                new Rectangle(),
                new Square(),
                new Triangle(),
                new Parallelogram(),
                new Trapezoid(),
                new Ellipse(),
                new RegularPolygon(),
                new Sector(),
                new Annulus()
        );

        System.out.println("\nArea Calculator\n");

        boolean first = true;
        while (true) {
            if (first) {
                first = false;
            } else {
                String cont = InputHelper.readYesNo(sc, "\nDo you want to calculate another area? (yes/no): ");
                if (!cont.equals("yes")) {
                    System.out.println("\nProgram ended\n");
                    break;
                }
            }

            System.out.println("Choose a shape:");
            for (int i = 0; i < shapes.size(); i++) {
                System.out.println((i + 1) + " - " + shapes.get(i).getName());
            }

            int choice = InputHelper.readMenuChoice(sc, "Option: ", 1, shapes.size());
            GeometricShape shape = shapes.get(choice - 1);

            String[] paramsNames = shape.getParameters();
            double[] values = new double[paramsNames.length];
            for (int i = 0; i < paramsNames.length; i++) {
                values[i] = InputHelper.readPositiveDouble(sc, "Enter " + paramsNames[i] + ": ");
            }

            double area = shape.calculate(values);
            System.out.printf("\nResult\nShape: %s\nArea: %.6f\n", shape.getName(), area);
        }

        sc.close();
    }
}
