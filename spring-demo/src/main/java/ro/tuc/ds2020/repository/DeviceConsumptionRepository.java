package ro.tuc.ds2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.model.Device;
import ro.tuc.ds2020.model.DeviceConsumption;

import java.util.List;

@Repository
public interface DeviceConsumptionRepository extends JpaRepository<DeviceConsumption, Integer>
{
    List<DeviceConsumption> findAllByDevice(Device device);
    void deleteAllByDevice(Device device);
}
