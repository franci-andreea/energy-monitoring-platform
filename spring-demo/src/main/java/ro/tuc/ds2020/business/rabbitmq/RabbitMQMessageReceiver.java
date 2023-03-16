package ro.tuc.ds2020.business.rabbitmq;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.business.DeviceService;
import ro.tuc.ds2020.business.dto.RabbitMQMessageDTO;
import ro.tuc.ds2020.model.Device;
import ro.tuc.ds2020.model.DeviceConsumption;
import ro.tuc.ds2020.business.websocket.NotificationService;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@Component
public class RabbitMQMessageReceiver
{
    private final DeviceService deviceService;
    private final NotificationService notificationService;

    @Autowired
    public RabbitMQMessageReceiver(DeviceService deviceService, NotificationService notificationService)
    {
        this.deviceService = deviceService;
        this.notificationService = notificationService;
    }

    public void receiveMessage(byte[] receivedMessage)
    {
        String message = new String(receivedMessage, StandardCharsets.UTF_8);
        JSONObject messageJson = (JSONObject) JSONValue.parse(message);
        RabbitMQMessageDTO rabbitMQMessageDTO = new RabbitMQMessageDTO();

        rabbitMQMessageDTO.setDevice_id((Integer) messageJson.get("device_id"));
        rabbitMQMessageDTO.setTimestamp((Long) messageJson.get("timestamp"));
        rabbitMQMessageDTO.setMeasurement_value((Double) messageJson.get("measurement_value"));

        System.out.println(rabbitMQMessageDTO);

        Device device = deviceService.getById(rabbitMQMessageDTO.getDevice_id());
        DeviceConsumption deviceConsumption = new DeviceConsumption(new Timestamp(rabbitMQMessageDTO.getTimestamp()), rabbitMQMessageDTO.getMeasurement_value(), device);

        Double totalDeviceConsumptionsInLastHour = deviceService.sumOfDeviceConsumptionsInLastHour(deviceService.findDeviceConsumptionsInLastHour());

        if(totalDeviceConsumptionsInLastHour > device.getMaxHourlyEnergyConsumption())
        {
            System.out.println("totalDeviceConsumptionsInLastHour = " + totalDeviceConsumptionsInLastHour);
            notificationService.sendMessage(device.getId());
        }

        deviceService.addDeviceConsumption(deviceConsumption);
    }
}
