package com.jaehwan.web.academy.config;

import javax.servlet.Filter;

import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml에서 DispatcheServlet 생성한 부분을 대신함
//따라서 여기서 해야 할 기본 적인 설정은
//1. URL 매핑 설정하기
/*<servlet-mapping>
<servlet-name>dispatcher</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>*/
//2. 다음과 같은 설정들 이용하기
//servlet-context.xml, service-context.xml, security-context.xml ... 등등
//
/*<listener>
<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>
      /WEB-INF/spring/service-context.xml
      /WEB-INF/spring/security-context.xml       
</param-value>
</context-param>*/
//3. 필터 설정. 예) 인코딩, 보안....
//4. 웰컴파일 리스트
//5. 기타 등등...
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		//servlet-context.xml설정을 대신하기 위한 자바 클래스를 다음으로 한다.
		//-> class ServletContextConfig{} 에서 하자요
		
		
		return new Class[] {
				ServletContextConfig.class,
				ServiceContextConfig.class,
				SecurityContextConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		
		CharacterEncodingFilter characterEncordingFilter = new CharacterEncodingFilter();
		characterEncordingFilter.setEncoding("UTF-8");
		characterEncordingFilter.setForceEncoding(true);
		
		return new Filter[]{
				characterEncordingFilter
				};
	}
}
