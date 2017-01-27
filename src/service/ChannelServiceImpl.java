package service;

import dao.ChannelDAO;
import model.Channel;
import model.Program;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    private ChannelDAO channelDAO;

    public void setChannelDAO(ChannelDAO programDAO) {
        this.channelDAO = programDAO;
    }

    @Override
    @Transactional
    public void addChannel(Channel c) {
        channelDAO.addChannel(c);
    }

    @Override
    @Transactional
    public void updateChannel(Channel c) {
        channelDAO.updateChannel(c);
    }

    @Override
    @Transactional
    public List<Channel> listChannels() {
        return channelDAO.listChannels();
    }

    @Override
    @Transactional
    public Channel getChannelById(int id) {
        return channelDAO.getChannelById(id);
    }

    @Override
    @Transactional
    public void removeChannel(int id) {
        channelDAO.removeChannel(id);
    }

    @Override
    @Transactional
    public List<Program> listChannelPrograms(int id) {
        return channelDAO.listChannelPrograms(id);
    }

    @Override
    @Transactional
    public List<Program> listProgramsByDay(int id, int day) {
        return channelDAO.listProgramsByDay(id, day);
    }
}
