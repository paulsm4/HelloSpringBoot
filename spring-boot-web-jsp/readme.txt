* Source reference: Spring Boot Hello World Example – JSP
  https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/
  
I. Initial (Linux): create, build, execute .jsp in a SpringBoot app with embedded Tomcat

1. Eclipse > New > Maven project >
     Simple project= Y >
     groupId= com.mkyong, artifactId= spring-boot-web-jsp, packaging= .war, 
       name == description= Spring Boot Web JSP Example
   <= Auto-generates Eclipse project, pom.xml, Maven directory layout

   NOTE: 
   The project's JRE System Library *must* be a JDK: configuring a "JRE-only" will fail at runtime.
          
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
   - Eclipse > Project > Properties > Facets >
     <= Verify "Dyamamic Web App" facet already selected   
   - Eclipse > src > main > webapp > 
     <= Use as source folder= Y

5. Eclipse > Save All
   Eclipse > Project > Refresh
   Eclipse > Project > Maven > Update Project
   Eclipse > pom.xml > Run As > Maven build > spring-boot:run
   Browser > http://localhost:8080/
   <= Verify that the .jsp is correctly rendered in a browser window     
   
* Verified (re)build/execute on Windows

===================================================================================================

II. Converted Maven => Gradle

1. Linux:
   - sudo apt-get install gradle
     <= Installed Gradle-3.4.1-7ubuntu
   - cd $PROJ/spring-boot-web-jsp
     gradle init
     <= Auto-generated .gradle/*, gradle/*, settings.gradle, build.grsdle, gradlew, gradlew.bat

2. Edit new Gradle files:
   - settings.gradle: OK as-is
   - build.gradle   
     <= Updated Java source/target 1.5 -> 1.8; updated "dependencies" syntax
     
3. Eclipse > Project > Configure > Add Gradle Nature
   <= OK: got "CONFIGURE SUCCESSFUL", uploaded .jar dependencies
   
4. Compiled and executed:
   - Eclipse > Project > Run As > Spring Boot app
     <= OK: built successfully, ran embedded Tomcat on port 8080
   - http://localhost:8080
     <= Saw HTTP rendered correctly
     
5. Eliminate Maven from project:
   - Delete $PROJ/{target, pom.xml*}
   - Eclipse > Project > Maven > Disable Maven Nature
   - Eclipse > Project > Refresh
     Eclipse > Project > Clean
     Eclipse > build.gradle > Gradle > Refresh Gradle Project
     Eclipse > Project > Run as > Spring Boot App
       <= ERROR: Error: Could not find or load main class com.mkyong.SpringBootWebApplication
     Eclipse > Project > Run As > Run Configurations >
       <= Deleted the current run configuration (which happened to use "Maven libraries"),
          re-created it (3 button clicks) ...
          and the problem was resolved.  The (now Gradle-based) project built and ran successfully.

===================================================================================================

III. Converted from Spring Boot 1.x => Spring Boot 2.x

1. Update build.gradle:
   - buildscript {
       ext {
         springBootVersion = '2.0.6.RELEASE'
       ...
       dependencies {
         classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
       ...
     dependencies {
       compile('org.springframework.boot:spring-boot-starter-web')
       compile('javax.servlet:jstl')
       compile('org.webjars:bootstrap')

   - Eclipse > build.gradle > Gradle > Refresh Gradle Project >
CONFIGURE SUCCESSFUL
Total time: 4.562 secs
     ...
     <= Completely new set of dependent .jars downloaded for updated project...
     ...
     Error: A problem occurred evaluating root project 'spring-boot-web-jsp'.
     > Failed to apply plugin [id 'org.springframework.boot']
     > Spring Boot plugin requires Gradle 4.0 or later. The current version is Gradle 3.4.1
     <= The Gradle "download dependencies" was successful ... but the subsequent Gradle "compile" failed
     
2. Tried updating Eclipse Oxygen/STS 3.x => 4.x
    <= No-go: Oxygen only supports STS 3.x or lower...

   - Installed Eclipse 2018-2019 JEE (nee "Photon"), STS 4.0.2, Gradle Buildship 3.0
     <= STILL NO-GO: same "Spring Boot plugin requires Gradle 4.0 or later..."
     
   - Eclipse > Window > Preferences > Gradle > Gradle distribution > 
       Changed from "Gradle wrapper" => "Specific version: 4.8.1"
     PROBLEM: Eclipse > Marketplace > Install {STS, BuildShip} had *installed* newer Gradle versions ... 
              ... but the project's "gradle wrapper" was stuck on earlier version
     WORKAROUNDS:
     a) Hack the (auto-generated) gradle wrapper file ... OR ...
     b) Specify "Specific version: 4.8.1" in Eclipse GUI project settings
        <= Chose workaround "b)"

3. Compile error:
   Error: org.springframework.boot.web.support does not exist

   - Solution: Changed "import" in SpringBootWebApplication.java:
     https://stackoverflow.com/a/38413739/421195
     
//import org.springframework.boot.web.support.SpringBootServletInitializer;  // OBSOLETE
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}
	...

4. Runtime error:
   - Gradle build/Java compile => OK
     Eclipse > Run As > Spring Boot Application => OK
     http://localhost:8080 => HTTP 404 (not found) error
     
   - Solution: Added "tomcat-embed-jasper" to build.gradle dependencies:
     https://stackoverflow.com/a/53603984/421195
     ...
     dependencies {
       compile('org.springframework.boot:spring-boot-starter-web')
       compile('javax.servlet:jstl')
       compile('javax.servlet:javax.servlet-api')
       compile('org.apache.tomcat.embed:tomcat-embed-jasper')
       compile('org.webjars:bootstrap:4.1.0')
       <= Spring Boot Starter Web includes "tomcat-embedded" by default...
          ... but the default tomcat-embedded doesn't process JSP.
          ... One needs to add *JASPER* in order to perform .jsp
          
        
       



     
       
       
         
     