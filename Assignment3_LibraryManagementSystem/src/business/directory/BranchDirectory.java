/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.directory;

/**
 *
 * @author Zhu jiayu
 */
import business.model.Branch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BranchDirectory {
    private final List<Branch> list = new ArrayList<>();

    public Branch create(String name) {
        Branch b = new Branch(name);
        list.add(b);
        return b;
    }
    public List<Branch> getAll() { return list; }
    public Branch findById(int id) {
        return list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    // 作业要求唯一允许删除的对象：Branch
    public boolean deleteById(int id) {
        Iterator<Branch> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) { it.remove(); return true; }
        }
        return false;
    }
}
