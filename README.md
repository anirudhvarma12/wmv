# Whats My Version - WMV
Whats My Version is a maven plugin that stores the version information of the artifact inside the jar/war file during packaging. This information can later be retreived easily to be used in anyway you want(like showing to user).

# Usage
As of now, the plugin is not available in Maven Central. You will have to add this manually - 
## Manual installation

 - Clone this repo
 - run `mvn install`
 - Add the following to your `pom.xml` :
 ```<plugin>
 	<groupId>com.github.anirudhvarma12</groupId>
	<artifactId>wmv-maven-plugin</artifactId>
	<version>1.0.0-SNAPSHOT</version>
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
` Under construction` 

# LICENSE
The MIT License (MIT), see `License` for details.
