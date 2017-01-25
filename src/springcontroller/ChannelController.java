package springcontroller;

import model.Channel;
import model.Program;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ChannelService;
import service.ProgramService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChannelController {

    @Autowired()
    @Qualifier(value = "channelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    private ChannelService channelService;

    @Autowired()
    @Qualifier(value = "programService")
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    private ProgramService programService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("channel", new Channel());
        Map<String,String> genre = new LinkedHashMap<>();
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
        Map<String,String> type = new LinkedHashMap<>();
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
    public String addProgram(@ModelAttribute("program")Program program, @ModelAttribute("channel") int id, Model model) {
        Channel channel = this.channelService.getChannelById(id);
        channel.addProgram(program);
        channelService.updateChannel(channel);
        this.programService.addProgram(program);
        return "redirect:/channel/" + id;
    }

}