/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.directory;

/**
 *
 * @author xuanliliu
 */

import business.model.Library;
import java.util.ArrayList;
import java.util.List;

public class LibraryDirectory {
    private final List<Library> list = new ArrayList<>();

    public Library create(String buildingNo) {
        Library lib = new Library(buildingNo);
        list.add(lib);
        return lib;
    }
    public List<Library> getAll() { return list; }
    public Library findById(int id) {
        return list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
