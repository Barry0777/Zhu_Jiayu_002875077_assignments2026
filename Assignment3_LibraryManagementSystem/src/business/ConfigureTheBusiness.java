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
//@author Zhu jiayu
/** 统一初始化系统 & 种子数据 */
public class ConfigureTheBusiness {
    
    

    /** 供 UI 调用：LibrarySystem sys = ConfigureTheBusiness.init(); */
    public static LibrarySystem init() {
        LibrarySystem sys = new LibrarySystem();

        // 1) 账号（用于登录验证）
        UserAccountDirectory ua = sys.getUserAccountDirectory();
        ua.create("admin",   "admin123", new SystemAdminRole());
        ua.create("manager", "mgr123",   new BranchManagerRole());
        
        // 5 个客户账号（作业要求）
        ua.create("alice", "123", new CustomerRole());
        ua.create("bob",   "123", new CustomerRole());
        ua.create("carl",  "123", new CustomerRole());
        ua.create("dina",  "123", new CustomerRole());
        ua.create("eric",  "123", new CustomerRole());

        // 若你已经有 CustomerDirectory，则同步创建 Customer 对象（否则先注释掉）
        try {
            sys.getCustomerDirectory().create("alice", "Alice");
            sys.getCustomerDirectory().create("bob",   "Bob");
            sys.getCustomerDirectory().create("carl",  "Carl");
            sys.getCustomerDirectory().create("dina",  "Dina");
            sys.getCustomerDirectory().create("eric",  "Eric");
        } catch (Throwable ignore) {
            // 没有 CustomerDirectory 就先略过，不影响运行
        }

        // 2) 分馆 & 库 & 经理
        BranchDirectory bd = sys.getBranchDirectory();
        LibraryDirectory ld = sys.getLibraryDirectory();
        

        Branch b1 = bd.create("Downtown");
        Branch b2 = bd.create("Uptown");

        Library l1 = ld.create("B1-101");
        Library l2 = ld.create("B2-201");
        
        
        // 2）ConfigureTheBusiness 里给 manager 指定 library
        UserAccount mgrAccount = ua.create("manager", "mgr123", new BranchManagerRole());
        mgrAccount.setLibraryId(l1.getId());   // 例如管 Downtown 对应的库

        Employee m1 = new Employee("Alice Manager", 5);
        Employee m2 = new Employee("Bob Manager", 7);
        sys.getEmployeeDirectory().add(m1);
        sys.getEmployeeDirectory().add(m2);

        b1.setLibrary(l1); b1.setManager(m1); l1.setManager(m1);
        b2.setLibrary(l2); b2.setManager(m2); l2.setManager(m2);

        // 3) 作者 & 图书（统一通过 Directory 创建；每本书归属一个 Library）
        var aRowling = sys.getAuthorDirectory().create("J.K. Rowling");
        var aMartin  = sys.getAuthorDirectory().create("George R.R. Martin");
        var aOrwell  = sys.getAuthorDirectory().create("George Orwell");

        sys.getBookDirectory().create("Harry Potter and the Sorcerer's Stone",
                LocalDate.now(), 320, "EN", aRowling, l1);
        sys.getBookDirectory().create("A Game of Thrones",
                LocalDate.now(), 690, "EN", aMartin, l2);
        sys.getBookDirectory().create("1984",
                LocalDate.now(), 328, "EN", aOrwell, l1);
        sys.getBookDirectory().create("Animal Farm",
                LocalDate.now(), 112, "EN", aOrwell, l2);

        return sys;
    }
}
