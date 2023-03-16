package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.business.DeviceService;
import ro.tuc.ds2020.business.UserService;
import ro.tuc.ds2020.business.dto.AddDeviceDTO;
import ro.tuc.ds2020.business.dto.DeviceConsumptionDTO;
import ro.tuc.ds2020.business.dto.EditDeviceDTO;
import ro.tuc.ds2020.model.Device;
import ro.tuc.ds2020.model.DeviceConsumption;
import ro.tuc.ds2020.model.User;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class DeviceController
{
    private final DeviceService deviceService;
    private final UserService userService;

    @Autowired
    public DeviceController(DeviceService deviceService, UserService userService)
    {
        this.deviceService = deviceService;
        this.userService = userService;
    }

    @PostMapping(value = "/add-device")
    public ResponseEntity<Device> add(@RequestBody AddDeviceDTO addDeviceDTO)
    {
        User user = userService.getByUsername(addDeviceDTO.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.add(addDeviceDTO, user));
    }

    @GetMapping(value = "/get-devices")
    public ResponseEntity<List<Device>> getDevices()
    {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.findAll());
    }

    @PostMapping(value = "/get-user-devices")
    public ResponseEntity<List<Device>> getUserDevices(@RequestBody User currentUser)
    {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.filterDevicesByUser(currentUser));
    }

    @PostMapping(value = "/delete-device")
    public void delete(@RequestBody Device deviceToDelete)
    {
        deviceService.delete(deviceToDelete);
        ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping(value = "/edit-device")
    public void edit(@RequestBody EditDeviceDTO editDeviceDTO)
    {
        deviceService.edit(editDeviceDTO);
        ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping(value = "/get-device-consumptions")
    public ResponseEntity<List<DeviceConsumptionDTO>> getDeviceConsumptionDTOs(@RequestBody Device device)
    {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.getDeviceConsumptions(device));
    }
}
