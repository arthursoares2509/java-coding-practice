import java.util.*;

interface Shape {
    String name();
    double area(Scanner sc);
}

class Circle implements Shape {
    public String name() { return "Circle"; }
    public double area(Scanner sc) {
        double r = Input.readPositiveDouble(sc, "Enter radius: ");
        return Math.PI * r * r;
    }
}

class Rectangle implements Shape {
    public String name() { return "Rectangle"; }
    public double area(Scanner sc) {
        double w = Input.readPositiveDouble(sc, "Enter width: ");
        double h = Input.readPositiveDouble(sc, "Enter height: ");
        return w * h;
    }
}

class Square implements Shape {
    public String name() { return "Square"; }
    public double area(Scanner sc) {
        double a = Input.readPositiveDouble(sc, "Enter side: ");
        return a * a;
    }
}

class Triangle implements Shape {
    public String name() { return "Triangle"; }
    public double area(Scanner sc) {
        double b = Input.readPositiveDouble(sc, "Enter base: ");
        double h = Input.readPositiveDouble(sc, "Enter height: ");
        return 0.5 * b * h;
    }
}

class Parallelogram implements Shape {
    public String name() { return "Parallelogram"; }
    public double area(Scanner sc) {
        double b = Input.readPositiveDouble(sc, "Enter base: ");
        double h = Input.readPositiveDouble(sc, "Enter height: ");
        return b * h;
    }
}

class Trapezoid implements Shape {
    public String name() { return "Trapezoid"; }
    public double area(Scanner sc) {
        double a = Input.readPositiveDouble(sc, "Enter base A: ");
        double b = Input.readPositiveDouble(sc, "Enter base B: ");
        double h = Input.readPositiveDouble(sc, "Enter height: ");
        return 0.5 * (a + b) * h;
    }
}

class Ellipse implements Shape {
    public String name() { return "Ellipse"; }
    public double area(Scanner sc) {
        double a = Input.readPositiveDouble(sc, "Enter semi-major axis a: ");
        double b = Input.readPositiveDouble(sc, "Enter semi-minor axis b: ");
        return Math.PI * a * b;
    }
}

class RegularPolygon implements Shape {
    public String name() { return "Regular Polygon"; }
    public double area(Scanner sc) {
        int n = Input.readIntWithMin(sc, "Enter number of sides (>=3): ", 3);
        double s = Input.readPositiveDouble(sc, "Enter side length: ");
        return (n * s * s) / (4.0 * Math.tan(Math.PI / n));
    }
}

class Sector implements Shape {
    public String name() { return "Sector"; }
    public double area(Scanner sc) {
        double r = Input.readPositiveDouble(sc, "Enter radius: ");
        double deg = Input.readPositiveDouble(sc, "Enter central angle in degrees: ");
        return Math.PI * r * r * (deg / 360.0);
    }
}

class Annulus implements Shape {
    public String name() { return "Annulus"; }
    public double area(Scanner sc) {
        double rOuter = Input.readPositiveDouble(sc, "Enter outer radius: ");
        double rInner = Input.readPositiveDouble(sc, "Enter inner radius: ");
        while (rInner >= rOuter) {
            System.out.println("Inner radius must be smaller than outer radius.");
            rInner = Input.readPositiveDouble(sc, "Enter inner radius: ");
        }
        return Math.PI * (rOuter * rOuter - rInner * rInner);
    }
}

class Input {
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

    static int readIntWithMin(Scanner sc, String prompt, int min) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v >= min) return v;
            } catch (Exception ignored) {}
            System.out.println("Invalid value. Please enter an integer >= " + min + ".");
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

        List<Shape> shapes = Arrays.asList(
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
                String cont = Input.readYesNo(sc, "\nDo you want to calculate another area? (yes/no): ");
                if (!cont.equals("yes")) {
                    System.out.println("\nProgram ended\n");
                    break;
                }
            }

            System.out.println("Choose a shape:");
            for (int i = 0; i < shapes.size(); i++) {
                System.out.println((i + 1) + " - " + shapes.get(i).name());
            }

            int choice = Input.readMenuChoice(sc, "Option: ", 1, shapes.size());
            Shape shape = shapes.get(choice - 1);

            double area = shape.area(sc);
            System.out.printf("\nResult\nShape: %s\nArea: %.6f\n", shape.name(), area);
        }

        sc.close();
    }
}
