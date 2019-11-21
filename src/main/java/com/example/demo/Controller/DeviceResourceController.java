package com.example.demo.Controller;
import com.example.demo.Entity.Device;
import com.example.demo.Service.DeviceResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取设备资源信息
 */
@Slf4j
@Controller
public class DeviceResourceController extends BaseController{

    @Autowired
    private DeviceResourceService deviceResourceService;


    //获取设备资源
    @RequestMapping(path = {"/getDeviceResource"}, method = RequestMethod.GET)
    public String getDeviceResource(Model model) throws InterruptedException {

        List<Device> deviceList = deviceResourceService.getDeviceResource();
        List<List<Double>> devices = new ArrayList<>();
        for (Device d : deviceList) {
            List device = new ArrayList<>();
            //设置设备属性 3：Id，4：status
            if(d.getX() == null || d.getY()==null||d.getKind().equals("Human")){
                continue;
            }
            device.add(d.getX().doubleValue());
            device.add(d.getY().doubleValue());
            device.add(d.getId());
            device.add(d.getStatus());
            device.add(d.getDeviceName());
            System.out.println(d.getDeviceName());
            devices.add(device);
        }
        model.addAttribute("DeviceList", devices);
        return "devices";
    }
}