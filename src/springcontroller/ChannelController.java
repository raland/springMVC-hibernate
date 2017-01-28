package springcontroller;

import model.Channel;
import model.Program;
import org.hibernate.Hibernate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ChannelService;
import service.ProgramService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ChannelController {

    private ChannelService channelService;
    private ProgramService programService;

    @Autowired()
    @Qualifier(value = "channelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Autowired()
    @Qualifier(value = "programService")
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("channel", new Channel());
        Map<String, String> genre = new LinkedHashMap<>();
        Map<String, String> type = new LinkedHashMap<>();
        genre.put("genre1", "genre1");
        genre.put("genre2", "genre2");
        genre.put("genre3", "genre3");
        genre.put("genre4", "genre4");
        type.put("type1", "type1");
        type.put("type2", "type2");
        type.put("type3", "type3");
        type.put("type4", "type4");
        model.addAttribute("genreList", genre);
        model.addAttribute("typeList", type);
        return "index";
    }

    @RequestMapping(value = "/channels", method = RequestMethod.GET)
    public String listChannels(Model model) {
        model.addAttribute("channel", new Channel());
        model.addAttribute("listChannels", this.channelService.listChannels());
        return "channels";
    }

    //For add and update person both
    @RequestMapping(value = "/channel/add", method = RequestMethod.POST)
    public String addChannel(@ModelAttribute("channel") Channel c) {
        this.channelService.addChannel(c);
        return "redirect:/channels";
    }

    @RequestMapping("/remove/{id}")
    public String removeChannel(@PathVariable("id") int id) {
        this.channelService.removeChannel(id);
        return "redirect:/channels";
    }

    @RequestMapping("/channel/{id}")
    @Transactional
    public String editChannel(@PathVariable("id") int id, Model model) {
        Channel channel = this.channelService.getChannelById(id);
        Map<String, String> type = new LinkedHashMap<>();
        List<List<Program>> programs = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            programs.add(channelService.listProgramsByDay(id, i));
            programs.get(programs.size() - 1).forEach(program -> program.convertDate());
            Collections.sort(programs.get(programs.size() - 1));
        }
        Hibernate.initialize(programs);
        type.put("type1", "type1");
        type.put("type2", "type2");
        type.put("type3", "type3");
        type.put("type4", "type4");
        model.addAttribute("typeList", type);
        model.addAttribute("channel", channel);
        model.addAttribute("program", new Program());
        model.addAttribute("sundayList", programs.get(0));
        model.addAttribute("mondayList", programs.get(1));
        model.addAttribute("tuesdayList", programs.get(2));
        model.addAttribute("wednesdayList", programs.get(3));
        model.addAttribute("thursdayList", programs.get(4));
        model.addAttribute("fridayList", programs.get(5));
        model.addAttribute("saturdayList", programs.get(6));
        return "channel";
    }

    @RequestMapping(value = "/program/add", method = RequestMethod.POST)
    @Transactional
    public String addProgram(@ModelAttribute("program") Program program, @ModelAttribute("channel") int id, Model model) {
        Channel channel = this.channelService.getChannelById(id);
        channel.addProgram(program);
        channelService.updateChannel(channel);
        this.programService.addProgram(program);
        return "redirect:/channel/" + id;
    }

    @RequestMapping("/program/remove/{id}")
    @Transactional
    public String removeProgram(@PathVariable("id") int programId) {
        Program program = this.programService.getProgramById(programId);
        Channel channel = program.getChannel();
        int channelId = channel.getChannelId();
        channel.removeProgram(program);
        this.programService.removeProgram(programId);
        return "redirect:/channel/" + channelId;
    }

    @RequestMapping("/program/edit/{id}")
    @Transactional
    public String editProgram(@PathVariable("id") int programId, Model model) {
        Program program = this.programService.getProgramById(programId);
        Channel channel = program.getChannel();
        model.addAttribute("listChannels", this.channelService.listChannels());
        model.addAttribute("channel", channel);
        model.addAttribute("program", program);
        Map<String, String> type = new LinkedHashMap<>();
        type.put("type1", "type1");
        type.put("type2", "type2");
        type.put("type3", "type3");
        type.put("type4", "type4");
        Map<Channel, String> availableChannels = new LinkedHashMap<>();
        this.channelService.listChannels().forEach(channel1 -> availableChannels.put(channel1, channel1.getChannelName()));
        model.addAttribute("channelList", availableChannels);
        model.addAttribute("typeList", type);
        return "editprogram";
    }

    @RequestMapping(value = "/program/update/{id}", method = RequestMethod.POST)
    @Transactional
    public String updateProgram(@PathVariable("id") int programId, @ModelAttribute("program") Program program, @RequestParam(value = "previousId") int previousChannelId, @RequestParam(value = "channels") int newId) {
        System.out.println("previous id " + previousChannelId);
        System.out.println("new id " + newId);
        Channel newChannel = channelService.getChannelById(newId);
        program.setChannel(newChannel);
        this.programService.updateProgram(program);
        return "redirect:/channel/" + previousChannelId;
    }

    @RequestMapping(value = "/programs/search/bytype", method = RequestMethod.POST)
    @ResponseBody
    public String searchPrograms(@RequestParam String type, @RequestParam long startdate, @RequestParam long enddate) {
        System.err.println("activating");
        System.out.println(type + " " + startdate + " - " + enddate);
        List<Program> results = programService.searchByType(type, startdate, enddate);
        System.out.println(results.size());
        //Collections.sort(results);
        /*JSONArray jsonArray = new JSONArray();
        for (Program program : results) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("programName", program.getProgramName());
            jsonObject.put("channelName", program.getChannel().getChannelName());
            jsonObject.put("startTime", program.getStartTime().toString());
            jsonObject.put("channelId", program.getChannel().getChannelId());
            jsonArray.add(jsonObject);
        }


        JSONObject mainObj = new JSONObject();
        mainObj.put("results", jsonArray);
        return mainObj.toJSONString();*/
return "hello";
    }

}