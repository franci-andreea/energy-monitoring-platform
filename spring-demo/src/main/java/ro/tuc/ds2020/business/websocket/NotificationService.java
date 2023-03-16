package ro.tuc.ds2020.business.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationService
{
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate)
    {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessage(Integer deviceID)
    {
        simpMessagingTemplate.convertAndSendToUser(
                deviceID.toString(),
                "/notification",
                "Device having id = " + deviceID + " has exceeded its maximum hourly consumption!");
    }
}
