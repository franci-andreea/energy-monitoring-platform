package ro.tuc.ds2020.business.dto;

public class AddDeviceDTO
{
    private String description;
    private String address;
    private String maxHourlyEnergyConsumption;
    private String username;

    public String getDescription()
    {
        return description;
    }

    public String getAddress()
    {
        return address;
    }

    public String getMaxHourlyEnergyConsumption()
    {
        return maxHourlyEnergyConsumption;
    }

    public String getUsername()
    {
        return username;
    }
}
