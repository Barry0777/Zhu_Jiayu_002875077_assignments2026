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

public class ConfigureTheBusiness {

    public static LibrarySystem init() {

        LibrarySystem system = new LibrarySystem();

        UserAccountDirectory accountDir = system.getUserAccountDirectory();

        UserAccount adminAccount   = accountDir.create("admin",   "admin123", new SystemAdminRole());
        UserAccount managerAccount = accountDir.create("manager", "mgr123",   new BranchManagerRole());

        accountDir.create("ivy",   "123", new CustomerRole());
        accountDir.create("liam",  "123", new CustomerRole());
        accountDir.create("nora",  "123", new CustomerRole());
        accountDir.create("oscar", "123", new CustomerRole());
        accountDir.create("peter", "123", new CustomerRole());

        try {
            var customerDir = system.getCustomerDirectory();
            customerDir.create("ivy",   "Ivy Sun");
            customerDir.create("liam",  "Liam Guo");
            customerDir.create("nora",  "Nora Zhang");
            customerDir.create("oscar", "Oscar Lin");
            customerDir.create("peter", "Peter Wang");
        } catch (Exception ignore) {
        }

        BranchDirectory branchDir   = system.getBranchDirectory();
        LibraryDirectory libraryDir = system.getLibraryDirectory();

        Branch centralBranch = branchDir.create("Central Branch");
        Branch northBranch   = branchDir.create("North Branch");

        Library centralLibrary = libraryDir.create("C-101");
        Library northLibrary   = libraryDir.create("N-201");

        managerAccount.setLibraryId(centralLibrary.getId());

        Employee managerOne = new Employee("Emma Rivera", 5);
        Employee managerTwo = new Employee("Liam Chen",   7);

        system.getEmployeeDirectory().add(managerOne);
        system.getEmployeeDirectory().add(managerTwo);

        centralBranch.setLibrary(centralLibrary);
        centralBranch.setManager(managerOne);
        centralLibrary.setManager(managerOne);

        northBranch.setLibrary(northLibrary);
        northBranch.setManager(managerTwo);
        northLibrary.setManager(managerTwo);

        var authorOne   = system.getAuthorDirectory().create("Lena Hart");
        var authorTwo   = system.getAuthorDirectory().create("Marco Silva");
        var authorThree = system.getAuthorDirectory().create("Kenji Aoki");

        var bookDir = system.getBookDirectory();
        LocalDate today = LocalDate.now();

        bookDir.create("Skyward Journey", today, 320, "EN", authorOne,   centralLibrary);
        bookDir.create("Winter's Oath",   today, 690, "EN", authorTwo,   northLibrary);
        bookDir.create("Silent Code",     today, 328, "EN", authorThree, centralLibrary);
        bookDir.create("Neon Dreams",     today, 112, "EN", authorThree, northLibrary);

        return system;
    }
}
