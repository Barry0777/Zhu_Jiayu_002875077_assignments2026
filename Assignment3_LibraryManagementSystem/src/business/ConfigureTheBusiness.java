package business;

import business.directory.BranchDirectory;
import business.directory.LibraryDirectory;
import business.directory.UserAccountDirectory;
import business.model.Branch;
import business.model.Library;
import business.model.people.Employee;
import business.security.BranchManagerRole;
import business.security.CustomerRole;
import business.security.SystemAdminRole;
import business.security.UserAccount;

import java.time.LocalDate;

/**
 * System bootstrap and seed-data creator.
 * Initializes branches, libraries, employees, authors, books, and user accounts.
 */
public class ConfigureTheBusiness {

    /**
     * Main entry point invoked by LoginFrame.
     * Builds a fully populated LibrarySystem instance.
     */
    
    public static LibrarySystem init() {

        // === Create core system ===
        LibrarySystem system = new LibrarySystem();

        // === Preload User Accounts ===
        UserAccountDirectory ua = system.getUserAccountDirectory();
        
        

        // 1 Admin
        ua.create("sysadmin", "admin123", new SystemAdminRole());

        // 1 Manager (for branch A)
        //ua.create("managerA", "mgrA123", new BranchManagerRole());

        // 5 Customers (Assignment requirement)
        String[][] customerSeed = {
                {"ryan",   "123"},
                {"sophia", "123"},
                {"liam",   "123"},
                {"zoe",    "123"},
                {"noah",   "123"}
        };
        
        

        for (String[] acc : customerSeed) {
            ua.create(acc[0], acc[1], new CustomerRole());
        }

        // Mirror into CustomerDirectory if available
        try {
            for (String[] acc : customerSeed) {
                system.getCustomerDirectory().create(acc[0], capitalize(acc[0]));
            }
        } catch (Exception ignore) {
            // Ignore if directory is absent
        }

        // === Branches + Libraries ===
        BranchDirectory bd = system.getBranchDirectory();
        LibraryDirectory ld = system.getLibraryDirectory();

        Branch brEast  = bd.create("East Side Branch");
        Branch brWest  = bd.create("West End Branch");

        Library libEast = ld.create("E-101");
        Library libWest = ld.create("W-204");

        // Assign managerA account to Library East
        UserAccount mgrA = ua.create("managerA", "mgrA123", new BranchManagerRole());
        mgrA.setLibraryId(libEast.getId());

        // === Employees (Managers) ===
        Employee empEast = new Employee("Evan Turner", 6);
        Employee empWest = new Employee("Mara Collins", 8);

        system.getEmployeeDirectory().add(empEast);
        system.getEmployeeDirectory().add(empWest);

        // Bind Branch ↔ Library ↔ Manager
        brEast.setLibrary(libEast);
        brEast.setManager(empEast);
        libEast.setManager(empEast);

        brWest.setLibrary(libWest);
        brWest.setManager(empWest);
        libWest.setManager(empWest);

        // === Authors ===
        var aHartman   = system.getAuthorDirectory().create("Lydia Hartman");
        var aKeats     = system.getAuthorDirectory().create("Brandon Keats");
        var aSanderson = system.getAuthorDirectory().create("Elio Sanderson");

        // === Books ===
        system.getBookDirectory().create(
                "Shadows of the Dawn",
                LocalDate.now(),
                350,
                "EN",
                aHartman,
                libEast
        );

        system.getBookDirectory().create(
                "Fragments of Tomorrow",
                LocalDate.now(),
                420,
                "EN",
                aKeats,
                libWest
        );

        system.getBookDirectory().create(
                "Silent River",
                LocalDate.now(),
                310,
                "EN",
                aSanderson,
                libEast
        );

        system.getBookDirectory().create(
                "Winter's Gate",
                LocalDate.now(),
                260,
                "EN",
                aSanderson,
                libWest
        );
        // === Default Rental Records (so manager can see rentals immediately) ===
var rentalDir = system.getRentalDirectory();
var books = system.getBookDirectory().getAll();

if (books.size() >= 4) {

    
    var r1 = rentalDir.create(
            books.get(0),      // Shadows of the Dawn
            "ryan",            // 
            LocalDate.now().minusDays(3),
            LocalDate.now().plusDays(11)
    );
    r1.setStatus(business.model.Rental.RentalStatus.BORROWED);


    var r2 = rentalDir.create(
            books.get(2),      // Silent River
            "sophia",
            LocalDate.now().minusDays(10),
            LocalDate.now().minusDays(2)
    );
    r2.setReturnDate(LocalDate.now().minusDays(2));
    r2.setStatus(business.model.Rental.RentalStatus.RETURNED);

    
    rentalDir.create(
            books.get(1),      // Fragments of Tomorrow
            "noah",
            LocalDate.now().minusDays(1),
            LocalDate.now().plusDays(13)
    );

    rentalDir.create(
            books.get(3),      // Winter's Gate
            "liam",
            LocalDate.now().minusDays(20),
            LocalDate.now().minusDays(5)
    ).setStatus(business.model.Rental.RentalStatus.RETURNED);
}

        return system;
    }

    /** Capitalize first character of a string */
    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
