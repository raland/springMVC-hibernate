package model;


import javax.persistence.*;
import java.util.*;

/**
 * Created by raul on 13/01/17.
 * 155250
 */
@Entity
public class Program implements Comparator<Program>, Comparable<Program> {
    private static final Map<String, String> programTypes;

    static {
        Map<String, String> types = new LinkedHashMap<>();
        types.put("Sport", "Sport");
        types.put("Sitcom", "Sitcom");
        types.put("Documentary", "Documentary");
        types.put("Cartoon", "Cartoon");
        types.put("Drama", "Drama");
        types.put("News", "News");
        types.put("Talk Show", "Talk Show");
        types.put("Music", "Music");
        types.put("Lifestyle", "Lifestyle");
        types.put("Other", "Other");
        programTypes = Collections.unmodifiableMap(types);
    }

    @Id
    @GeneratedValue
    private int programId;
    private String programName;
    private String programType;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Channel channel;
    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    //in minutes
    private int programLength;
    private Long epochTime;


    public Program(String programName, int programLength) {
        this.programName = programName;
        this.programLength = programLength;
    }

    public Program() {
    }

    public static Map<String, String> getProgramTypes() {
        return programTypes;
    }

    public Long getEpochTime() {
        return epochTime;
    }

    public void setEpochTime(Long epochtime) {
        this.epochTime = epochtime;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void removeChannel() {
        channel = null;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public int getProgramLength() {
        return programLength;
    }

    public void setProgramLength(int programLength) {
        this.programLength = programLength;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public void convertDate() {
        if (epochTime != null) {
            startTime = new Date(epochTime);
        }
    }

    @Override
    public int compare(Program p1, Program p2) {
        return (int) (p1.getEpochTime() - p2.getEpochTime());
    }

    @Override
    public int compareTo(Program program) {
        return this.startTime.compareTo(program.getStartTime());
    }
}
