# Whats My Version - WMV
Whats My Version is a maven plugin that stores the version information of the artifact inside the jar/war file during packaging. This information can later be retreived easily to be used in anyway you want(like showing to user).

# Usage
The plugin is available through the central maven repository -  
Add the following to your `pom.xml` :
```
   <plugin>
		<groupId>com.github.anirudhvarma12</groupId>
    	<artifactId>wmv-maven-plugin</artifactId>
    	<version>1.0.0</version>
    	<executions>
    		<execution>
    			<goals>
    				<goal>wmv</goal>
			    </goals>
    		</execution>
    	</executions>
    </plugin>
 ```
 
## Retrieving info
The plugin is executed when you run `mvn install`, `mvn package`, `mvn compile`, it creates a `wmv-info.properties` file in the resources folder.
The properties looks like this - 
```
	#Auto generated
	#Wed Dec 30 00:48:15 IST 2015
	version=0.0.2-SNAPSHOT
	date=30-12-2015 12-48-15
```

# LICENSE
The MIT License (MIT), see `License` for details.