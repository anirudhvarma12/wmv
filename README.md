# Whats My Version - WMV
Whats My Version is a maven plugin that stores the version information of the artifact inside the jar/war file during packaging. This information can later be retreived easily to be used in anyway you want(like showing to user).

# Usage
The plugin is available through the central maven repository -  
Add the following to your `pom.xml` :
```
   <plugin>
		<groupId>com.github.anirudhvarma12</groupId>
    	<artifactId>wmv-maven-plugin</artifactId>
    	<version>2.1.2</version>
    	<executions>
    		<execution>
    			<goals>
    				<goal>generate-version-info</goal>
			    </goals>
    		</execution>
    	</executions>
    </plugin>
 ```
 
## Custom file name
You can change the output file name from `version.properties` to something that you like:
```
	<execution>
		<goals>
			<goal>generate-version-info</goal>
		</goals>
		<configuration>
			<fileName>myVersion.properties</fileName>
		</configuration>
	</execution>
```

## Custom file type
You can change the output file type from `properties` (default) to `json` or `xml`
```
	<execution>
		<goals>
			<goal>generate-version-info</goal>
		</goals>
		<configuration>
			<fileName>myVersion.properties</fileName>
			<fileType>json</fileType><!-- or xml or properties -->
		</configuration>
	</execution>
```
## Git Support
As of v2.1.1, WMV supports reading tags from Git for version name. To enable Git integration simply add `versionProvider` tag to the `configuration`. Eg:- 
```
<configuration>
	<fileName>myVersion.properties</fileName>
	<fileType>properties</fileType>
	<versionProvider>git</versionProvider>
</configuration>
```

WMV will always read the latest git tag.


## Retrieving info
The plugin is executed when you run `mvn install`, `mvn package`, `mvn compile`, it creates a `version.properties` file in the resources folder.
The properties looks like this - 
```
	#Auto generated
	#Wed Dec 30 00:48:15 IST 2015
	version=0.0.2-SNAPSHOT
	date=30-12-2015 12-48-15
```


XML Structure:

```
<wmv>
	<version>2.1.0-SNAPSHOT</version>
	<date>21-02-2016 23-46-33</date>
</wmv>

```

JSON Structure:

```
{
	"version": "2.1.0-SNAPSHOT",
	"date": "21-02-2016 23-52-49"
}
```

# LICENSE
The MIT License (MIT), see `License` for details.
