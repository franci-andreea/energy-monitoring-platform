package ro.tuc.ds2020.business.builder;

import ro.tuc.ds2020.business.dto.AddDeviceDTO;
import ro.tuc.ds2020.model.Device;
import ro.tuc.ds2020.model.User;

public class DeviceBuilder
{
    private static DeviceBuilder builder;

    private DeviceBuilder() {

    }

    public static DeviceBuilder getInstance()
    {
        if(builder == null)
            builder = new DeviceBuilder();
        return builder;
    }

    public Device toDevice(AddDeviceDTO addDeviceDTO, User user)
    {
        Double maxHourlyEnergyConsumption = Double.parseDouble(addDeviceDTO.getMaxHourlyEnergyConsumption());

        return new Device(addDeviceDTO.getDescription(), addDeviceDTO.getAddress(), maxHourlyEnergyConsumption, user);
    }
}
