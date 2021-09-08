package com.baemin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baemin.interceptor.LoginInterceptor;
import com.baemin.interceptor.UserInterceptor;
import com.baemin.interceptor.searchInterceptor;
import com.baemin.interceptor.storeInterceptor;

@EnableWebSecurity
@Configuration
public class WebConfig implements WebMvcConfigurer {
	//
//	@Autowired
//	SearchInterceptor searchInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
    	
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/*");    // 모든 url에 적용
//                .excludePathPatterns("/test/**/")  // 제외할 url
//                .excludePathPatterns("/users/login"); 
        
        
        registry.addInterceptor(new UserInterceptor())
        		.addPathPatterns("/myPage/*")
        		.excludePathPatterns("/myPage/dibs"); 
        
        registry.addInterceptor(new storeInterceptor())
    			.addPathPatterns("/storeList/*")
    			.addPathPatterns("/storeList/*/*");
        
        registry.addInterceptor(new searchInterceptor())
        .addPathPatterns("/store/search");
        
    }
    
    @Bean
    public LoginInterceptor loginInterceptor() {
    	return new LoginInterceptor();
    }
    

    
}