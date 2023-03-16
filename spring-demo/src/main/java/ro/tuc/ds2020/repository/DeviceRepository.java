package ro.tuc.ds2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.model.Device;
import ro.tuc.ds2020.model.User;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer>
{
    List<Device> findDevicesByUser(User user);
    Device findDeviceById(Integer id);
}
