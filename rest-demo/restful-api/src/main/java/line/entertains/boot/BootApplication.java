package line.entertains.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 记录一些比较好的博客：
 * https://www.cnblogs.com/ityouknow/category/914493.html
 * 
 * @author line
 */
@SpringBootApplication
//@ServletComponentScan
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
    
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//       FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//       StartTimeFilter myFilter = new StartTimeFilter();
//       registrationBean.setFilter(myFilter);
//       return registrationBean;
//    }
}
