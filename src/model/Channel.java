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
    private static final Map<String, String> channelGenres;

    static {
        Map<String, String> genres = new LinkedHashMap<>();
        genres.put("News", "News");
        genres.put("Sport", "Sport");
        genres.put("Generic", "Generic");
        genres.put("Music", "Music");
        genres.put("Science & History", "Science & History");
        genres.put("Movies", "Movies");
        genres.put("Other", "Other");
        channelGenres = Collections.unmodifiableMap(genres);
    }

    @Id
    @GeneratedValue
    private int channelId;
    private String channelName;
    private String channelDescription;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "channel")
    @OrderBy("epochTime DESC")
    private List<Program> programs = new ArrayList<>();
    private String genre;

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public Channel() {
    }

    public static Map<String, String> getChannelGenres() {
        return channelGenres;
    }

    public Set<Program> filterByProgram(String programName) {
        return programs.stream().filter(program -> Objects.equals(program.getProgramName(), programName)).collect(Collectors.toSet());
    }

    public Set<Program> filterByDay(int dayOfWeek) {
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

    @Override
    public String toString() {
        return "Channel{" +
                "channelId=" + channelId +
                ", channelName='" + channelName + '\'' +
                ", channelDescription='" + channelDescription + '\'' +
                ", programs=" + programs +
                ", genre='" + genre + '\'' +
                '}';
    }
}
