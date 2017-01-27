package dao;

import model.Channel;
import model.Program;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class ChannelDAOImpl implements ChannelDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addChannel(Channel c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(c);
    }

    @Override
    public void updateChannel(Channel c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
    }

    @Override
    public List<Channel> listChannels() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Channel> channelList = session.createQuery("from Channel ").list();
        for (Channel p : channelList) {
            // TODO: 22/01/17 something
        }
        return channelList;
    }

    @Override
    public Channel getChannelById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Channel channel = session.load(Channel.class, id);
        return channel;
    }

    @Override
    public void removeChannel(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Channel c = session.load(Channel.class, id);
        if (null != c) {
            session.delete(c);
        }
    }

    @Override
    @Transactional
    public List<Program> listChannelPrograms(int id) {
        return getChannelById(id).getPrograms();
    }

    @Override
    public List<Program> listProgramsByDay(int id, int day) {
        return new ArrayList<Program>(getChannelById(id).filterByDay(day));
    }
}
