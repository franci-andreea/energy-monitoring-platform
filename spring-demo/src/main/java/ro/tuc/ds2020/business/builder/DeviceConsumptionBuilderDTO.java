package ro.tuc.ds2020.business.builder;

import ro.tuc.ds2020.business.dto.DeviceConsumptionDTO;
import ro.tuc.ds2020.model.DeviceConsumption;

import java.text.SimpleDateFormat;

public class DeviceConsumptionBuilderDTO
{
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public DeviceConsumptionDTO toDeviceConsumptionDTO(DeviceConsumption deviceConsumption)
    {
        return new DeviceConsumptionDTO(simpleDateFormat.format(deviceConsumption.getDate()), deviceConsumption.getEnergyConsumption());
    }
}
