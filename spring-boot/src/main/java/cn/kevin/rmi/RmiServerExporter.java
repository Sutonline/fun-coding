package cn.kevin.rmi;

import cn.kevin.service.RmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import javax.annotation.Resource;
import java.rmi.RemoteException;

@Configuration
@SuppressWarnings("Duplicates")
public class RmiServerExporter {

    @Resource(name = "rmiServiceImpl")
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

    @Bean(name = "helloService")
    public RmiProxyFactoryBean invokeClient() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/helloService");
        rmiProxyFactoryBean.setServiceInterface(RmiService.class);
        return rmiProxyFactoryBean;
    }


}
