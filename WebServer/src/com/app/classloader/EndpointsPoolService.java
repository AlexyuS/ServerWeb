package com.app.classloader;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.src.CustomController;
import com.src.CustomGET;
import com.thread.ReflectionUtils;

public class EndpointsPoolService {
	private static EndpointsPoolService classPoolSingleton;
	private static Object lock = new Object();
	private static Map<String, Method> endpoints;

	private static Log log = LogFactory.getLog(EndpointsPoolService.class.getName());

	private EndpointsPoolService() {

	}

	public static EndpointsPoolService getInstance() {
		if (classPoolSingleton != null) {
			return classPoolSingleton;
		} else {
			synchronized (lock) {
				if (classPoolSingleton == null) {
					classPoolSingleton = new EndpointsPoolService();
					return classPoolSingleton;
				} else {
					return classPoolSingleton;
				}
			}
		}
	}

	private Map<String, Method> buildEndpointMappings() {

		List<Class<?>> classes = ReflectionUtils.loadClasses().stream()
				.filter(e -> e.isAnnotationPresent(CustomController.class)).collect(Collectors.toList());

		// mapping all the classes to methods
		Map<String, List<Method>> annotatedMethods = classes.stream()
				.collect(Collectors.toMap(f -> getPathFromClassEndpoint(f), e -> filterMethodsForAnnotation(e)));

		Map<String, Method> endpoints = extractEndpoints(annotatedMethods);

		log.info("Endpoints registered succesfully" + endpoints.toString());

		return endpoints;

	}

	private Map<String, Method> extractEndpoints(Map<String, List<Method>> annotatedMethods) {
		BiConsumer<Map<String, Method>, Entry<String, List<Method>>> acumulator = (x, y) -> {
			Map<String, Method> endpoitToMethodMapper = y.getValue().stream()
					.collect(Collectors.toMap(e -> y.getKey() + "/" + getMethodEndpoint(e), Function.identity()));
			x.putAll(endpoitToMethodMapper);
		};
		BiConsumer<Map<String, Method>, Map<String, Method>> combiner = (x, y) -> {
			x.putAll(y);
		};

		Map<String, Method> endpoints = annotatedMethods.entrySet().stream().collect(HashMap::new, acumulator,
				combiner);

		return endpoints;
	}

	private List<Method> filterMethodsForAnnotation(Class<?> clazz) {
		return Arrays.asList(clazz.getMethods()).stream().filter(e -> e.isAnnotationPresent(CustomGET.class))
				.collect(Collectors.toList());
	}

	private String getPathFromClassEndpoint(Class<?> clazz) {
		return clazz.getAnnotation(CustomController.class).basePath();
	}

	private String getMethodEndpoint(Method method) {
		return method.getAnnotation(CustomGET.class).endpoint();
	}

	public synchronized Map<String, Method> getEndpoints() {
		if (endpoints == null) {
			endpoints = buildEndpointMappings();
			return endpoints;
		} else {
			return endpoints;
		}
	}

}
