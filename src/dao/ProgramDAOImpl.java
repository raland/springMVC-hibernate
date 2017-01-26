package dao;

import model.Program;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
