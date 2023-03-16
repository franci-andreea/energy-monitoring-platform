package ro.tuc.ds2020.business.dto;

import ro.tuc.ds2020.model.Device;

import java.sql.Timestamp;

public class DeviceConsumptionDTO
{
    private String date;
    private Double energyConsumption;

    public DeviceConsumptionDTO(String date, Double energyConsumption)
    {
        this.date = date;
        this.energyConsumption = energyConsumption;
    }

    public String getDate()
    {
        return date;
    }

    public Double getEnergyConsumption()
    {
        return energyConsumption;
    }
}
