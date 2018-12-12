* JSP + Static HTML in SpringBoot 2.x application
  ===============================================
1. Create SpringBoot project:
   - Eclipse > New > New Spring Starter Project >
       Service URL= https://start.spring.io
       Name= test7
       Build type= Gradle Buildship 3.0
       Packaging= War
       Group= com.example, Artifact= test7, Version= 1.0
       Package= example.com.test7;
       Dependencies= Web > Web

    NOTES:
    - For JSP, you *must* choose "Packaging= .war"
    - STS auto-generates a skeleton Eclipse/Spring Boot project
     
2. Update skeleton project:
   - Eclipse > Add new >
       File= readme.txt
       Folder= src > main > resources > META-INF > resources > WEB-INF > jsp

    NOTES:
    - Many tutorials for calling .jsp from Spring Boot say to use project folder "src/main/webapp/WEB-INF/jsp".
      There was apparently a *BREAKING CHANGE* between Spring Boot 1.4.2 and 1.5.3.
    - The correct folder for Spring Boot 2.1.1 is "src/main/resources/META-INF/resources/WEB-INF/jsp"

    PROJECT STRUCTURE SO FAR:
.
+---.gradle
+---.settings
+---bin
+---gradle
\---src
    +---main
    |   +---java.com.example.test7
    |   \---resources
    |       +---static
    |       \---templates
    +---test
    \---java.com.example.test7

3. Update build.gradle:
   - build.gradle:
     ------------
buildscript {
	ext {
		springBootVersion = '2.1.1.RELEASE'
    ...
apply plugin: 'java'
apply plugin: 'eclipse-wtp'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'
apply plugin: 'war'
    ...
dependencies {
	compile('org.springframework.boot:spring-boot-starter-web')
    compile('javax.servlet:jstl')
    compile('javax.servlet:javax.servlet-api')
    compile('org.apache.tomcat.embed:tomcat-embed-jasper')
    // compile('org.springframework.boot:spring-boot-starter-thymeleaf')
    compile('org.webjars:bootstrap:4.1.0')
	testImplementation('org.springframework.boot:spring-boot-starter-test')
    ...

    NOTES:
    - The "tomcat-embedded" dependency is automatically included with "spring-boot-starter-web", it doesn't need to be added to build.gradle.
    - By default, Tomcat embedded *CANNOT* compile .jsp: you must explicitly add a dependency for "tomcat-embed-jasper".
    - Run gradle task "dependences" to list dependencies tree for current build.gradle:
        Current versions: Java jdk1.8.0_121, Gradle 4.8.1, spring-boot 2.1.1, spring-core 5.1.3, tomcat-embed-jasper 9.0.13, slf4j-api 1.7.25

4. Update main class:
   - Test7Application.java:
     ---------------------
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
...
@SpringBootApplication
public class Test7Application extends SpringBootServletInitializer {
    ...	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("Test7Application.configure()...");
		return application.sources(Test7Application.class);
	}
	
	public static void main(String[] args) {
		logger.info("Test7Application.main()...");
		SpringApplication.run(Test7Application.class, args);
	}

    NOTES:
    - In this example, the root application extends SpringBootServletInitializer and overrides SpringBootServletInitializer.configure()
      <= This reads application.properties.
    - This is generally the preferred way to configure the Spring MVC views are with application.properties
    - An alternative is to implement a Spring Boot "View Resolver":
      <= "extends WebMvcConfigurerAdapter" (Spring Boot 1.x) 
         ... or ...
         "implements implements WebMvcConfigurer" (Spring Boot 2.x)

5. Update application.properties:
   - application.properties:
     ----------------------
# Look here for jsp URLs:
spring.mvc.view.prefix: /WEB-INF/jsp/
spring.mvc.view.suffix: .jsp

# Custom log level
logging.level.org.springframework.web=TRACE
logging.level.com.example.test7=TRACE
	
    NOTES:
    - This example will look for views (here, .jsps) in /WEB-INF/jsp/, and append the file suffix ".jsp"
    - Log level is set to "TRACE"
        
6. Add controller:
   - src > main > java > Add > Class > Name= Test7Controller >
   - Test7Controller.java:
     --------------------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test7Controller {
	
	private static Logger logger = LoggerFactory.getLogger("test7");
	
	public Test7Controller () {
    	logger.info("Test7Controller constructor...");
	}
	
//    @GetMapping("/")
//    public String getIndex() {
//    	logger.info("Test7Controller.getIndex()...");
//        return "index.html";
//    }

    @GetMapping("/jsp")
    public String getJsp() {
    	logger.info("Test7Controller.getJsp()...");
        return "test";
    }
...

    NOTES:
    - This example adds custom logging
      <= No extra .jars required: "spring-boot-starter-web" includes SLF4J by default.
    - Since we're going to search a static "index.html" for the root URL, we should *NOT* define a mapping for "/"

7. Add static HTML page, .jsp page and Thymeleaf template:
   - src > main > resources > static > index.html     # Static HTML, root page
   - src > main > resources > templates > test.html   # Thymeleaf template (not available)
   - src > main > resources > META-INF > resources > WEB-INF > jsp > test.jsp  # Test JSP page
   - src > main > resources > META-INF > resources > WEB-INF > favicon.ico     # Icon file
   
8. Build and run project:
   - Eclipse > test7 > Gradle > Refresh Gradle Project
     <= This downloads dependencies

   - Eclipse > test7 > Run As > Spring Boot App
     <= This starts embedded Tomcat server

   - http://localhost:8080 => OK:             Static HTML
     http://localhost:8080/index.html => OK:  Static HTML
     http://localhost:8080/jsp => OK:         JSP Time: Tue Dec 11 12:38:04 PST 2018
     http://localhost:8080/thymeleaf =>       There was an unexpected error (type=Not Found, status=404):  /WEB-INF/jsp/test.html.jsp
     <= Must be *EITHER* Thymeleaf *OR* JSP ... but not both!
	