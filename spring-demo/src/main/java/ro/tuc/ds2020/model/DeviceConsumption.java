package ro.tuc.ds2020.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "device_consumption")
public class DeviceConsumption
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Timestamp date;

    @Column(nullable = false)
    private Double energyConsumption;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    public DeviceConsumption() {}

    public DeviceConsumption(Timestamp date, Double energyConsumption, Device device)
    {
        this.date = date;
        this.energyConsumption = energyConsumption;
        this.device = device;
    }

    public Timestamp getDate()
    {
        return date;
    }

    public Double getEnergyConsumption()
    {
        return energyConsumption;
    }

    public Device getDevice()
    {
        return device;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }

    public void setEnergyConsumption(Double energyConsumption)
    {
        this.energyConsumption = energyConsumption;
    }

    public void setDevice(Device device)
    {
        this.device = device;
    }
}
