package ro.tuc.ds2020.business.dto;

public class RabbitMQMessageDTO
{
    private Integer device_id;
    private long timestamp;
    private double measurement_value;

    public Integer getDevice_id()
    {
        return device_id;
    }

    public void setDevice_id(Integer device_id)
    {
        this.device_id = device_id;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public double getMeasurement_value()
    {
        return measurement_value;
    }

    public void setMeasurement_value(double measurement_value)
    {
        this.measurement_value = measurement_value;
    }

    @Override
    public String toString()
    {
        return "RabbitMQMessageDTO{" +
                "device_id=" + device_id +
                ", timestamp=" + timestamp +
                ", measurement_value=" + measurement_value +
                '}';
    }
}
