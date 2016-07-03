package com.liuxiang.abtest.cl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.liuxiang.abtest.sdk.solution.SolutionBase;

import lombok.Data;
@Data
public class SolutionClassLoader {
	
	private URLClassLoader urlClassloader;
	
	public SolutionClassLoader(String jarFilePath) throws MalformedURLException {
		URL url = new URL(jarFilePath);
		urlClassloader = new URLClassLoader(new URL[] {url},SolutionClassLoader.class.getClassLoader());
	}
	
	public Class<SolutionBase> loadClass(String solutionClass) throws ClassNotFoundException {
		Class<SolutionBase> clazz = (Class<SolutionBase>) urlClassloader.loadClass(solutionClass);
		return clazz;
	}
}
