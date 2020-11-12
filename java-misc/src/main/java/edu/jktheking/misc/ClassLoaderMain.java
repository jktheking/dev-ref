package edu.jktheking.misc;

import java.util.ArrayList;


/**
 *{@link https://javarevisited.blogspot.com/2012/12/how-classloader-works-in-java.html}
 * <pre>
 * Java class loaders are used to load classes at runtime. ClassLoader in Java
 * works on three principle: <I>delegation, visibility and uniqueness.</I>
 * 
 * 
 * Delegation principle forward request of class loading to parent class loader
 * and only loads the class, if parent is not able to find or load class.
 * 
 * 
 * Visibility principle allows child class loader to see all the classes loaded
 * by parent ClassLoader, but parent class loader can not see classes loaded by
 * child.
 * 
 *  
 * Uniqueness principle allows to load a class exactly once, which is basically
 * achieved by delegation and ensures that child ClassLoader doesn't reload the
 * class already loaded by parent.
 * 
 *  
 * Correct understanding help resolving NoClassDefFoundError in Java and
 * java.lang.ClassNotFoundException, which are related to class loading.
 * 
 * NoClassDefFoundError : When JVM is not able to find a particular class at
 * runtime which was available at compile time.if we are accessing any static member of a
 *  Class and that class is not available during run-time then JVM will throw NoClassDefFoundError.
 *  e.g. We imported a class 'A' in different class 'B' and  class 'B' got compiled successfully, 
 *  but class A was absent during runtime so class loader of 'B' will throw NoClassDefFoundError.
 *  
 *  ClassNotFoundException comes when explicit loading of the class is involved by providing the name of the class at runtime 
 *  like with method class.forname("className") and if that class is not found in the classpath 
 *  it throws java.lang.ClassNotFoundException.
 * 
 * There are three default class loader used in Java: Bootstrap , Extension and
 * System or Application class loader.
 * 
 * Bootstrap ClassLoader is responsible for loading standard JDK class files
 * from rt.jar and it is parent of all class loaders in Java
 * 
 * Extension ClassLoader delegates class loading request to its parent,
 * Bootstrap and if unsuccessful, loads class form jre/lib/ext directory or any
 * other directory pointed by <code>java.ext.dirs</code> system property. Extension
 * ClassLoader in JVM is implemented by sun.misc.Launcher$ExtClassLoader.
 * 
 * 
 * Third default class loader used by JVM to load Java classes is called System
 * or Application class loader and it is responsible for loading application
 * specific classes from :
 * - CLASSPATH environment variable,
 *  -classpath or -cp option,
 *   Class-Path attribute of Manifest inside JAR file
 *   
 * Application class loader is a child of Extension ClassLoader and its
 * implemented by sun.misc.Launcher$AppClassLoader class. Also, except Bootstrap
 * class loader, which is implemented in native language mostly in C, all Java
 * class loaders are implemented using java.lang.ClassLoader.
 * </pre>
 * 
 * <br>
 * {@link https://www.baeldung.com/java-classloaders}
 * <br>
 * <b>How Do Class Loaders Work?</b>
 * <pre>
 * The java.lang.ClassLoader.loadClass() method is responsible for loading the class definition into runtime. 
 * It tries to load the class based on a fully qualified name. If the class isn't already loaded, 
 * it delegates the request to the parent class loader. This process happens recursively. 
 * Eventually, if the parent class loader doesn’t find the class, 
 * then the child class will call java.net.URLClassLoader.findClass()
 * method to look for classes in the file system itself.
 * If the last child class loader isn't able to load the class either, 
 * it throws java.lang.NoClassDefFoundError or java.lang.ClassNotFoundException.
 * 
 * Custom Class Loaders Use-Cases:
 *  - Helping in modifying the existing bytecode, e.g. weaving agents
 *  
 *  - Creating classes dynamically suited to the user's needs. e.g in JDBC, 
 *    switching between different driver implementations is done through dynamic class loading.
 *     
 *  - Implementing a class versioning mechanism while loading different bytecodes for classes with 
 *    same names and packages. This can be done either through URL class loader (load jars via URLs) or custom class loaders
 * </pre>
 *
 * 
 * <pre>context classloaders
 * https://www.javaworld.com/article/2077344/find-a-way-out-of-the-classloader-maze.html
 *  </pre>
 */

public class ClassLoaderMain {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Package[] pkg = Package.getPackages();
		for(Package p : pkg) {
			System.out.println(p);
			
		}
		printClassLoaders();
	}

	public static void printClassLoaders() throws ClassNotFoundException {

		System.out.println("Classloader for this class:" + ClassLoaderMain.class.getClassLoader());

		System.out.println("Classloader for this.parent():" + ClassLoaderMain.class.getClassLoader().getParent());

		System.out.println("Classloader for this.parent().parent() which is native bootstrap classloader:"
				+ ClassLoaderMain.class.getClassLoader().getParent().getParent());

		System.out.println("Classloader for ArrayList i.e bootstrap classloader:" + ArrayList.class.getClassLoader());
	}
}
