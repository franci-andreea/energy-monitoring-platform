package ro.tuc.ds2020.model;

import javax.persistence.*;

@Entity
public class Device
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "max_h_energy_consumption", nullable = false)
    private Double maxHourlyEnergyConsumption;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public Device() {}

    public Device(String description, String address, Double maxHourlyEnergyConsumption, User user)
    {
        this.description = description;
        this.address = address;
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
        this.user = user;
    }

    public Integer getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public String getAddress()
    {
        return address;
    }

    public Double getMaxHourlyEnergyConsumption()
    {
        return maxHourlyEnergyConsumption;
    }

    public User getUser()
    {
        return user;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setMaxHourlyEnergyConsumption(Double maxHourlyEnergyConsumption)
    {
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
