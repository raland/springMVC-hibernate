package service;

import model.Channel;
import model.Program;

import java.util.List;

/**
 * Created by raul on 23/01/17.
 */
public interface ChannelService {
    public void addChannel(Channel c);

    public void updateChannel(Channel c);

    public List<Channel> listChannels();

    public Channel getChannelById(int id);

    public void removeChannel(int id);

    List<Program> listChannelPrograms(int id);
}
