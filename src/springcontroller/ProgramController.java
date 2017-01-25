package springcontroller;

import model.Channel;
import model.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.ChannelService;
import service.ProgramService;

@RestController
public class ProgramController {

    @Autowired()
    @Qualifier(value = "programService")
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    private ProgramService programService;

    @Autowired()
    @Qualifier(value = "channelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    private ChannelService channelService;



}
