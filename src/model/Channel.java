package model;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raul on 13/01/17.
 * 155250
 */
@Entity
public class Channel {
    @Id
    @GeneratedValue
    private int channelId;
    private String channelName;
    private String channelDescription;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="channel")
    private List<Program> programs = new ArrayList<>();

    private String genre;

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public Channel() {
    }

    public Set<Program> filterByProgram(String programName) {
        return programs.stream().filter(program -> Objects.equals(program.getProgramName(), programName)).collect(Collectors.toSet());
    }

    public Set<Program> filterByDay(int dayOfWeek){
        return programs.stream().filter(program -> program.getDayOfWeek() == dayOfWeek).collect(Collectors.toSet());
    }

    public void addProgram(Program program) {
        program.setChannel(this);
        programs.add(program);
    }

    public void removeProgram(Program program) {
        program.removeChannel();
        programs.remove(program);
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
