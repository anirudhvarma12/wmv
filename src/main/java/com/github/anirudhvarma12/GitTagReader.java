package com.github.anirudhvarma12;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitTagReader {

	public static String getCurrentTag(String path) throws IOException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repository = builder
				.setGitDir(new File(appendGitFolder(path))).readEnvironment()
				.findGitDir().build();
		Map<String, Ref> tagMap = repository.getTags();
		Set<String> tags = tagMap.keySet();
		return getLastTag(tags);
	}

	private static final String appendGitFolder(String path) {
		return path + File.separator + ".git";
	}

	private static String getLastTag(Set<String> tags) {
		String tag = "N/A";
		if (tags.isEmpty()) {
			return tag;
		}
		String[] tagArray = new String[tags.size()];
		tags.toArray(tagArray);
		return tagArray[tags.size() - 1];
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getCurrentTag("C:\\workspace\\opensource\\wmv\\"));
	}

}
