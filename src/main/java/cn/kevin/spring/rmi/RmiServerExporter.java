package cn.kevin.spring.rmi;

import cn.kevin.spring.service.RmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;

@Configuration
public class RmiServerExporter {

    @Autowired
    private RmiService rmiService;

    @Bean
    public RmiServiceExporter export() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("helloService");
        rmiServiceExporter.setService(rmiService);
        rmiServiceExporter.setServiceInterface(RmiService.class);
        try {
            rmiServiceExporter.afterPropertiesSet();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return rmiServiceExporter;
    }


}
