package dao;

import model.Program;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProgramDAOImpl implements ProgramDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProgram(Program p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void updateProgram(Program p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }

    @Override
    public List<Program> listPrograms() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Program> programList = session.createQuery("from Program ").list();
        for (Program p : programList) {
            // TODO: 22/01/17 something
        }
        return programList;
    }

    @Override
    public Program getProgramById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Program.class, id);
    }

    @Override
    public void removeProgram(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Program p = session.load(Program.class, id);
        if (p != null) {
            session.delete(p);
        }
    }

    @Override
    public List<Program> searchByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Program> criteria = builder.createQuery(Program.class);
        criteria.from(Program.class);
        return null;
    }

    @Override
    public List<Program> searchByType(String type, long startDate, long endDate) {
        return this.listPrograms().stream().filter(program -> program.getStartTime().after(new Date(startDate)) && program.getStartTime().before(new Date(endDate)) && program.getProgramType().equals(type)).collect(Collectors.toList());
    }
}
