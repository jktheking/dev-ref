package edu.jktheking.misc;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {
	
public static void main(String[] args) {
	
	Path path = Paths.get(URI.create("file://./HashCode.java")).toAbsolutePath();
	System.out.println(path.toString());
}

}
