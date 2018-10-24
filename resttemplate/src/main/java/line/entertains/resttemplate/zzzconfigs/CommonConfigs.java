package line.entertains.resttemplate.zzzconfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class CommonConfigs {
    
    @Autowired
    RequestMappingHandlerAdapter handlerAdapter;
    
    @Bean(name = "string")
    public String string(RequestMappingHandlerAdapter handlerAdapter) {
    	String string = new String("111111");
    	return string;
    }
}
