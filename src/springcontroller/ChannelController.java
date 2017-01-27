package springcontroller;

import model.Channel;
import model.Program;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ChannelService;
import service.ProgramService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        genre.put("genre1", "genre1");
        genre.put("genre2", "genre2");
        genre.put("genre3", "genre3");
        genre.put("genre4", "genre4");
        model.addAttribute("genreList", genre);
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
        List<Program> programs = this.channelService.listChannelPrograms(id);
        programs.forEach(program -> program.convertDate());
        Hibernate.initialize(programs);
        type.put("type1", "type1");
        type.put("type2", "type2");
        type.put("type3", "type3");
        type.put("type4", "type4");
        model.addAttribute("typeList", type);
        model.addAttribute("channel", channel);
        model.addAttribute("program", new Program());
        model.addAttribute("programList", programs);
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
    public String updateProgram(@PathVariable("id") int programId, @ModelAttribute("program") Program program, @RequestParam(value="previousId")int previousChannelId, @RequestParam(value = "channels") int newId) {
        System.out.println("previous id " + previousChannelId);
        System.out.println("new id " + newId);
        if (previousChannelId != newId) {
            Channel newChannel = channelService.getChannelById(newId);
            program.setChannel(newChannel);
        }
        this.programService.updateProgram(program);
        return "redirect:/channel/" + previousChannelId; // TODO: 1/26/17 move channel
//        // TODO: 1/26/17 change date default set default value
    }

}