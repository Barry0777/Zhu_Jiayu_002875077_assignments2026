/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.directory;

/**
 *
 * @author xuanliliu
 */

import business.model.people.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private final List<Employee> list = new ArrayList<>();

    // 创建新员工
    public Employee create(String name, int experience){
        Employee e = new Employee(name, experience);
        list.add(e);
        return e;
    }
    // 接受外部已创建的 Employee（比如配置里 new 出来的）
    public Employee add(Employee e){
        list.add(e);
        return e;
    }
    public List<Employee> getAll(){ return list; }
}
