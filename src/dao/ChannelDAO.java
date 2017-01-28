package dao;

import model.Channel;
import model.Program;

import java.util.List;
import java.util.Map;

/**
 * Created by raul on 23/01/17.
 */
public interface ChannelDAO {
    void addChannel(Channel p);

    void updateChannel(Channel p);

    List<Channel> listChannels();

    Channel getChannelById(int id);

    void removeChannel(int id);

    List<Program> listChannelPrograms(int id);

    List<Program> listProgramsByDay(int id, int day);

    Map<String, String> listGenres();

    Map<Channel, String> getAvailableChannels();
}
