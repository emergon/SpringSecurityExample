package emergon.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Declaration of this Dispatcher Servlet
//This class doesn't need web.xml to declare DispatcherServlet
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class [] configClasses = {MyWebAppConfig.class};
        return configClasses;
    }

    @Override
    protected String[] getServletMappings() {
        String [] urlMappings = {"/"};
        return urlMappings;
    }
    
}
