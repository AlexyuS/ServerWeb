package com.thread;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//import com.src.CustomController;

public class ReflectionUtils {
	
	public static List<Class<?>> loadClasses() {
		try {
			File classpath = new File("C:\\Users\\AlexandruHirzoiu\\git\\WebServer\\WebServer\\Application\\");
		
			Map<File,String> packagesToClass =  new HashMap<File,String>();
			buildMappings(packagesToClass,classpath,"");
		
			//loads all the classes from build mappings
			List<Class<?>> classes = packagesToClass.entrySet().stream().map(e->loadClass(classpath,e.getKey(),e.getValue())).filter(e->e!=null).collect(Collectors.toList());
			
			return classes;
		} catch (Exception e1) {
			return null;
		}
			
			
	}
	
	/**
	 * Builds the mappings containing classes and packages from the base dir location
	 * @param mapping
	 * @param file
	 * @param path
	 */
	private  static void buildMappings(Map<File,String> mapping ,File file,String path){
		for (File f : file.listFiles()) {
			if(f.isFile()) {
				mapping.put(f, path);
			}else {
				buildMappings(mapping, f, path+f.getName()+".");
			}
		}
	}
	
	private static Class<?> loadClass(File classpath,File classFile, String packageName){
		try {
			
			URL url  = classpath.toURI().toURL();
			ClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[] {url});
			
			String className= classFile.getName().split(".class")[0];
			return urlClassLoader.loadClass(packageName+className);

		} catch (Exception e) {
			return null;
		}
	}
}
