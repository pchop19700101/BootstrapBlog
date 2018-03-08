import cn.porkchop.bootstrapblog.service.LinkService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tests {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring/application*");
        LinkService bean = classPathXmlApplicationContext.getBean(LinkService.class);
        System.out.println(bean.findAllByOrder());
    }
}
