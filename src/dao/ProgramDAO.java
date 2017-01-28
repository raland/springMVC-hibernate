package dao;

import model.Program;

import java.util.List;

public interface ProgramDAO {

    public void addProgram(Program p);

    public void updateProgram(Program p);

    public List<Program> listPrograms();

    public Program getProgramById(int id);

    public void removeProgram(int id);

    public List<Program> searchByName(String name);

    public List<Program> searchByType(String type, long startDate, long endDate);
}