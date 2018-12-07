* Source reference: Spring Boot Hello World Example – JSP
  https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/
  
* Initial: create, build, execute .jsp in a SpringBoot app with embedded Tomcat
  1. Eclipse > New > Maven project >
       Simple project= Y >
       groupId= com.mkyong, artifactId= spring-boot-web-jsp, packaging= .war, 
         name == description= Spring Boot Web JSP Example
     <= Auto-generates Eclipse project, pom.xml, Maven directory layout
     
  2. Add readme.txt (this file)
  
  3. Update pom.xml:
     <= Verbatim from Mkyong.com
     - pom.xml: ...
		<artifactId>spring-boot-web-jsp</artifactId>
		<packaging>war</packaging>
		<name>Spring Boot Web JSP Example</name>
		<description>Spring Boot Web JSP Example</description>
		<url>https://www.mkyong.com</url>
		<version>1.0</version>
		...
		<parent>
		  <groupId>org.springframework.boot</groupId>
		  <artifactId>spring-boot-starter-parent</artifactId>
		<version>1.4.2.RELEASE
		...
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		<dependency>
			<groupId>org.eclipse.jdt.core.compiler</groupId>
			<artifactId>ecj</artifactId>
			<version>4.6.1</version>
			<scope>provided</scope>
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>3.3.7</version>
						
     - Eclipse > pom.xml > Build > dependency:tree
       <= Maven: download dependencies, build project: OK: BUILD SUCCESS

  4. Reorganize directories, per mkyong tutorial...
   	 - spring-boot-web-jsp > src > java >
   	   - Add package com.mkyong
   	   - Add classes {SpringBootWebApplication, WelcomeController}
   	   - Add src > webapp > WEB-INF > jsp > 
   	       welcome.jsp
   	   - Add src > main > resources > static > css >
   	       main.css
   	   - Add src > main > resources >
           application.properties

  5. Eclipse > Save All
     Eclipse > Project > Refresh
     Eclipse > Project > Maven > Update Project
     Eclipse > pom.xml > Run As > Maven build > spring-boot:run
     Browser > http://localhost:8080/
     <= Works!  See the .jsp!
     
		        
  4. Eclipse > Project > Properties > Facets >
       <= Verify "Dyamamic Web App" facet already selected   
