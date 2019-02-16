package cn.porkchop.javapan.listener;

import cn.porkchop.javapan.service.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class ContextListener implements ServletContextListener, ApplicationContextAware {
    private static ApplicationContext applicationContext;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        //博客类型
        servletContext.setAttribute("blogTypes", applicationContext.getBean(ResourceTypeService.class).findAllWithBlogCountByOrder());
        //友情链接
        servletContext.setAttribute("links", applicationContext.getBean(LinkService.class).findAllByOrder());
        //月份
        servletContext.setAttribute("monthes", applicationContext.getBean(ResourceService.class).findReleaseMonthWithCount());
        //广告
        servletContext.setAttribute("advertisements", applicationContext.getBean(AdvertisementService.class).findAllByOrder());
        //背景图片
        servletContext.setAttribute("backgroundImage",applicationContext.getBean(BackgroundImageService.class).findById(1));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;

    }
}
