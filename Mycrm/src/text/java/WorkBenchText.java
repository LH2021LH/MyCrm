import com.lh.crm.workbench.mapper.ActivityMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WorkBenchText {

    @Test
    public void text(){
        ApplicationContext context = new
                ClassPathXmlApplicationContext("SpringConfig/ApplicationContext.xml");
        ActivityMapper activityMapper = context.getBean("activityMapper", ActivityMapper.class);
        System.out.println(activityMapper);
    }
}
