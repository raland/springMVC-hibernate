package service;

import model.Channel;
import model.Program;

import java.util.List;
import java.util.Map;

/**
 * Created by raul on 23/01/17.
 */
public interface ChannelService {
    void addChannel(Channel c);

    void updateChannel(Channel c);

    List<Channel> listChannels();

    Channel getChannelById(int id);

    void removeChannel(int id);

    List<Program> listChannelPrograms(int id);

    List<Program> listProgramsByDay(int id, int day);

    Map<String, String> listChannelGenres();

    Map<Channel, String> getAvailableChannels();
}
