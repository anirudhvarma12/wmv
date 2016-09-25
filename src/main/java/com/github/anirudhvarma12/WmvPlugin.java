package com.github.anirudhvarma12;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "generate-version-info", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class WmvPlugin extends AbstractMojo {

	private static final String VERSION_PROVIDER_GIT = "git";
	private static final String VERSION_PROVIDER_MVN = "mvn";

	@Parameter(property = "project.version")
	private String version;

	@Parameter(property = "project.basedir")
	private File directory;

	@Parameter(defaultValue = "version.properties")
	private String fileName;

	@Parameter(defaultValue = PropertyWriter.TYPE_PROPERTIES)
	private String fileType;

	@Parameter(defaultValue = VERSION_PROVIDER_MVN)
	private String versionProvider;

	public void execute() throws MojoExecutionException, MojoFailureException {
		String resolvedVersion = version;
		if (versionProvider.equals(VERSION_PROVIDER_GIT)) {
			getLog().info("Trying to read tag from git");
			try {
				resolvedVersion = GitTagReader.getCurrentTag(directory
						.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
				throw new MojoExecutionException(e.getMessage());
			}
		}
		getLog().info("got project version " + resolvedVersion);
		getLog().info("Creating file " + fileName + " with format " + fileType);
		File f = directory;
		File vInfo = new File(f.getAbsolutePath() + "/src/main/resources/"
				+ fileName);
		try {
			if (fileType.equals(PropertyWriter.TYPE_JSON)
					|| fileType.equals(PropertyWriter.TYPE_PROPERTIES)
					|| fileType.equals(PropertyWriter.TYPE_XML)) {
				PropertyWriter.write(resolvedVersion, fileType, vInfo);
				getLog().info("Stored file - " + vInfo.getAbsolutePath());
			} else {
				throw new MojoExecutionException("fileType should be one of ["
						+ PropertyWriter.TYPE_JSON + ", "
						+ PropertyWriter.TYPE_PROPERTIES + ", "
						+ PropertyWriter.TYPE_XML + "]");
			}
		} catch (Exception e) {
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}
}
