package dao;

import model.Channel;
import model.Program;

import java.util.List;

/**
 * Created by raul on 23/01/17.
 */
public interface ChannelDAO {
    public void addChannel(Channel p);

    public void updateChannel(Channel p);

    public List<Channel> listChannels();

    public Channel getChannelById(int id);

    public void removeChannel(int id);

    List<Program> listChannelPrograms(int id);

    List<Program> listProgramsByDay(int id, int day);
}
