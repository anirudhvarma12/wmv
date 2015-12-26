package com.github.anirudhvarma12;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "wmv", defaultPhase=LifecyclePhase.PROCESS_SOURCES)
public class WmvPlugin extends AbstractMojo {

	@Parameter(property = "project.version")
	private String version;

	@Parameter(property = "project.basedir")
	private File directory;

	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("got project version " + version);
		File f = directory;
		File vInfo = new File(f.getAbsolutePath()
				+ "/src/main/resources/wmv-info.properties");
		try {
			getProperties()
					.store(new FileOutputStream(vInfo), "Auto generated");
			getLog().info("Stored file - " + vInfo.getAbsolutePath());
		} catch (Exception e) {
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}

	private String convertDateToString() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		return formatter.format(date);
	}

	private Properties getProperties() {
		Properties props = new Properties();
		props.put("version", version);
		props.put("date", convertDateToString());
		return props;
	}
}
