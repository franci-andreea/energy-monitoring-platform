package ro.tuc.ds2020.business;

import org.springframework.stereotype.Service;
import ro.tuc.ds2020.business.builder.DeviceBuilder;
import ro.tuc.ds2020.business.builder.DeviceConsumptionBuilderDTO;
import ro.tuc.ds2020.business.dto.AddDeviceDTO;
import ro.tuc.ds2020.business.dto.DeviceConsumptionDTO;
import ro.tuc.ds2020.business.dto.EditDeviceDTO;
import ro.tuc.ds2020.business.validators.RegisterValidator;
import ro.tuc.ds2020.model.Device;
import ro.tuc.ds2020.model.DeviceConsumption;
import ro.tuc.ds2020.model.User;
import ro.tuc.ds2020.repository.DeviceConsumptionRepository;
import ro.tuc.ds2020.repository.DeviceRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DeviceService
{
    private final DeviceRepository deviceRepository;
    private final DeviceConsumptionRepository deviceConsumptionRepository;

    public DeviceService(DeviceRepository deviceRepository, DeviceConsumptionRepository deviceConsumptionRepository)
    {
        this.deviceRepository = deviceRepository;
        this.deviceConsumptionRepository = deviceConsumptionRepository;
    }

    public Device add(AddDeviceDTO addDeviceDTO, User user)
    {
        RegisterValidator validator = RegisterValidator.getInstance();
        DeviceBuilder builder = DeviceBuilder.getInstance();

        if(validator.checkNotNullField(addDeviceDTO.getDescription()) && validator.checkNotNullField(addDeviceDTO.getAddress())
            && validator.checkHourlyConsumptionField(addDeviceDTO.getMaxHourlyEnergyConsumption()))
        {
            Device newDevice = builder.toDevice(addDeviceDTO, user);

            newDevice = deviceRepository.save(newDevice);

            generateRandomConsumptionData(newDevice);

            return newDevice;
        }

        return null;
    }

    public List<DeviceConsumptionDTO> getDeviceConsumptions(Device device)
    {
        DeviceConsumptionBuilderDTO builder = new DeviceConsumptionBuilderDTO();
        List<DeviceConsumption> deviceConsumptions = deviceConsumptionRepository.findAllByDevice(device);
        List<DeviceConsumptionDTO> deviceConsumptionDTOList = new ArrayList<>();

        for(DeviceConsumption deviceConsumption : deviceConsumptions)
        {
            deviceConsumptionDTOList.add(builder.toDeviceConsumptionDTO(deviceConsumption));
        }

        return deviceConsumptionDTOList;
    }

    private void generateRandomConsumptionData(Device newDevice)
    {
        Device fetchedDevice = deviceRepository.findDeviceById(newDevice.getId());

        //generate random values for the previous 10 days

        long hourInMs = 1000 * 60 * 60;
        long tenDaysInMs = 10 * 24 * hourInMs;

        long now = System.currentTimeMillis();

        Random consumptionValueGenerator = new Random();

        for(long time = now - tenDaysInMs; time < now; time = time + hourInMs)
        {
            Timestamp timestamp = new Timestamp(time);

            Double randomConsumptionValue = consumptionValueGenerator.nextDouble() + fetchedDevice.getMaxHourlyEnergyConsumption();

            DeviceConsumption deviceConsumption = new DeviceConsumption(timestamp, randomConsumptionValue, fetchedDevice);

            deviceConsumptionRepository.save(deviceConsumption);
        }
    }

    public void addDeviceConsumption(DeviceConsumption newDeviceConsumption)
    {
        deviceConsumptionRepository.save(newDeviceConsumption);
    }

    @Transactional
    public void delete(Device deviceToDelete)
    {
        deviceConsumptionRepository.deleteAllByDevice(deviceToDelete);

        deviceRepository.delete(deviceToDelete);
        deviceRepository.flush();
    }

    public void edit(EditDeviceDTO editDeviceDTO)
    {
        RegisterValidator validator = RegisterValidator.getInstance();

        if(validator.checkNotNullField(editDeviceDTO.getNewDescription()))
            editDeviceDTO.getDeviceToEdit().setDescription(editDeviceDTO.getNewDescription());

        if(validator.checkNotNullField(editDeviceDTO.getNewAddress()))
            editDeviceDTO.getDeviceToEdit().setAddress(editDeviceDTO.getNewAddress());

        deviceRepository.save(editDeviceDTO.getDeviceToEdit());
    }

    public Device getById(Integer id)
    {
        return deviceRepository.findDeviceById(id);
    }

    public List<Device> findAll()
    {
        return deviceRepository.findAll();
    }

    public List<Device> filterDevicesByUser(User user)
    {
        return deviceRepository.findDevicesByUser(user);
    }

    public List<DeviceConsumption> findDeviceConsumptionsInLastHour()
    {
        List<DeviceConsumption> all = deviceConsumptionRepository.findAll();

        all.sort((first, second) -> Double.compare(second.getDate().getTime(), first.getDate().getTime()));

        return (all.size() >= 6) ? all.subList(0, 6) : all;
    }

    public Double sumOfDeviceConsumptionsInLastHour(List<DeviceConsumption> deviceConsumptions)
    {

        if(deviceConsumptions == null)
            return 0.0;

        Double sum = 0.0;

        for(DeviceConsumption deviceConsumption : deviceConsumptions)
        {
            sum = sum + deviceConsumption.getEnergyConsumption();
        }

        return sum;
    }

}
