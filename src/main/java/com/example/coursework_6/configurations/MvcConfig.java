//package com.example.coursework_6.configurations;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.resource.VersionResourceResolver;
//
//@Configuration
//@EnableWebMvc
//public class MvcConfig implements WebMvcConfigurer {
//    public MvcConfig() {
//        super();
//    }
//
//    @Override
//    public void addViewControllers(final ViewControllerRegistry registry){
//        registry.addViewController("/example.html");
//    }
//
//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry){
//        registry.addResourceHandler(
//                "/img/**",
//                "/css/**"
//        ).addResourceLocations(
//                "classpath:/static/img/",
//                "classpath:/static/css/"
//        ).resourceChain(true)
//                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"))
//        ;
//    }
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Override
//    public void addInterceptors(final InterceptorRegistry registry){
//        final LocaleChangeInterceptor localeChangeInterceptor =
//                new LocaleChangeInterceptor();
//        registry.addInterceptor(localeChangeInterceptor);
//    }
//}
