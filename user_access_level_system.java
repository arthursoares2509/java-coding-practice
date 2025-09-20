import java.util.ArrayList;
import java.util.List;

interface AccessLevel {
    String getAccessDescription();
}

class User {
    protected String username;
    protected int id;

    public User(String username, int id) {
        this.username = username;
        this.id = id;
    }

    public String getInfo() {
        return "User: " + username + " (ID: " + id + ")";
    }

    public String login() {
        return username + " logged in.";
    }

    public String logout() {
        return username + " logged out.";
    }
}

class Admin extends User implements AccessLevel {
    private List<String> privileges;

    public Admin(String username, int id) {
        super(username, id);
        privileges = new ArrayList<>();
        privileges.add("READ_ALL");
        privileges.add("WRITE_ALL");
        privileges.add("DELETE_ALL");
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " [Admin]";
    }

    @Override
    public String login() {
        return super.login() + " with ADMIN privileges.";
    }

    @Override
    public String getAccessDescription() {
        return "Admin Privileges: " + String.join(", ", privileges);
    }

    public void addPrivilege(String privilege) {
        privileges.add(privilege);
    }
}

class Guest extends User implements AccessLevel {

    public Guest(String username, int id) {
        super(username, id);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " [Guest]";
    }

    @Override
    public String login() {
        return super.login() + " with limited access.";
    }

    @Override
    public String getAccessDescription() {
        return "Guest can only read public content.";
    }
}

class Moderator extends User implements AccessLevel {
    private List<String> sections;

    public Moderator(String username, int id) {
        super(username, id);
        sections = new ArrayList<>();
        sections.add("Forum");
        sections.add("Comments");
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " [Moderator]";
    }

    @Override
    public String getAccessDescription() {
        return "Moderator sections: " + String.join(", ", sections);
    }

    public void addSection(String section) {
        sections.add(section);
    }
}

public class Main {
    public static void main(String[] args) {
        User genericUser = new User("JohnDoe", 101);
        Admin admin = new Admin("AliceAdmin", 1);
        Guest guest = new Guest("BobGuest", 202);
        Moderator mod = new Moderator("CharlieMod", 303);

        List<User> users = List.of(genericUser, admin, guest, mod);

        System.out.println("=== User Info & Actions Demo ===\n");

        for (User u : users) {
            System.out.println(u.getInfo());
            System.out.println(u.login());

            if (u instanceof AccessLevel) {
                System.out.println("Access Details: " + ((AccessLevel) u).getAccessDescription());
            }

            System.out.println(u.logout());
            System.out.println("-------------------------------");
        }

        System.out.println("\n=== Polymorphism Demonstration ===");
        User polyUser = new Admin("PolyAdmin", 999);
        System.out.println(polyUser.getInfo()); // Calls overridden Admin getInfo
        System.out.println(polyUser.login());   // Calls overridden Admin login
    }
}
