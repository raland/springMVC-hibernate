package dao;

import model.Program;

import java.util.List;
import java.util.Map;

public interface ProgramDAO {

    void addProgram(Program p);

    void updateProgram(Program p);

    List<Program> listPrograms();

    Program getProgramById(int id);

    void removeProgram(int id);

    List<Program> searchByName(String name);

    List<Program> searchByType(String type, long startDate, long endDate);

    Map<String, String> listProgramTypes();
}