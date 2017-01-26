package springcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import service.ChannelService;
import service.ProgramService;

@RestController
public class ProgramController {

    private ProgramService programService;
    private ChannelService channelService;

    @Autowired()
    @Qualifier(value = "programService")
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @Autowired()
    @Qualifier(value = "channelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }


}
