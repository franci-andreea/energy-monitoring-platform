package ro.tuc.ds2020.business.dto;

import ro.tuc.ds2020.model.Device;

public class EditDeviceDTO
{
    private String newDescription;
    private String newAddress;

    private Device deviceToEdit;

    public String getNewDescription()
    {
        return newDescription;
    }

    public String getNewAddress()
    {
        return newAddress;
    }

    public Device getDeviceToEdit()
    {
        return deviceToEdit;
    }
}
