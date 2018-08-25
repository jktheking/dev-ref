package edu.jktheking.processflow.utils;

import java.lang.reflect.Constructor;

public final class ReflectionUtils {

	private ReflectionUtils() {
	}

	public static <T> boolean containsDeclaredConstructor(Class<T> klass, Class<?>... parameterTypes) {
		try {
			klass.getDeclaredConstructor(parameterTypes);
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	public static <T> T newInstance(Class<T> klass, Class<?> p[], Object arg[]) {
		try {
			Constructor<T> constructor = klass.getDeclaredConstructor(p);
			java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Void>() {
				public Void run() {
					constructor.setAccessible(true);
					return null;
				}
			});

			return constructor.newInstance(arg);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T newInstance(Class<T> klass, Class<?> p1, Class<?> p2, Object arg1, Object arg2) {
		try {
			Constructor<T> constructor = klass.getDeclaredConstructor(p1, p2);
			java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Void>() {
				public Void run() {
					constructor.setAccessible(true);
					return null;
				}
			});

			return constructor.newInstance(arg1, arg2);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T newInstance(Class<T> klass, Object... args) {
		try {
			Constructor<T> ctor;

			if (args.length == 0) {
				ctor = klass.getDeclaredConstructor();
			} else {
				Class<?>[] argClses = new Class[args.length];
				for (int i = 0; i < args.length; i++) {
					argClses[i] = args[i].getClass();
				}
				ctor = klass.getDeclaredConstructor(argClses);
			}

			java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Void>() {
				public Void run() {
					ctor.setAccessible(true);
					return null;
				}
			});

			return ctor.newInstance(args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
