package service;

import dao.ProgramDAO;
import model.Program;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by raul on 22/01/17.
 */
@Service
public class ProgramServiceImpl implements ProgramService {

    private ProgramDAO programDAO;

    public void setProgramDAO(ProgramDAO programDAO) {
        this.programDAO = programDAO;
    }

    @Override
    @Transactional
    public void addProgram(Program p) {
        programDAO.addProgram(p);
    }

    @Override
    @Transactional
    public void updateProgram(Program p) {
        programDAO.updateProgram(p);
    }

    @Override
    @Transactional
    public List<Program> listPrograms() {
        return programDAO.listPrograms();
    }

    @Override
    @Transactional
    public Program getProgramById(int id) {
        return programDAO.getProgramById(id);
    }

    @Override
    @Transactional
    public void removeProgram(int id) {
        programDAO.removeProgram(id);
    }

    @Override
    @Transactional
    public List<Program> searchByName(String name) {
        return programDAO.searchByName(name);
    }

    @Override
    @Transactional
    public List<Program> searchByType(String type, long startDate, long endDate) {
        return programDAO.searchByType(type, startDate, endDate);
    }

    @Override
    public Map<String, String> listProgramTypes() {
        return programDAO.listProgramTypes();
    }
}
