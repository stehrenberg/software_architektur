/**
 * Hochschule Muenchen, Fakultaet 07 fuer Mathematik und Informatik Praktikum
 * Software-Architektur, Sommersemester 2015 OS: Windows 7 Professional SP1 (64
 * Bit); Java-Version: 1.8.0_05 CPU: Intel Core2Duo T7300 @ 2.0 GHz, 4GB RAM
 * Aufgabe 4: Factory Pattern
 */
package edu.hm.iny.patterns.factory;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import edu.hm.cs.rs.arch.pattern.decorator.TextImage;

/**
 * Factory class that stores created TextImages in a cache.
 * The cache's capacity is determined by the Systemproperty "cachecapacity".
 * When the cache's limit is reached, the oldest created TextImage is removed.
 * The cache capacity is p.e. configured by calling "System.setProperty("cachecapacity", "size").
 * @version 2015-05-01
 */
public class CachedTextImageFactory extends TextImageFactory {

	/** The default capacity for the cache. */
	private static final int DEFAULT_CACHE_CAPACITY = 10;
	/** The cache's capacity. */
	private final int cacheCapacity;
	/** Holds the images' tags in the order the corresponding imgs where created in.*/
	private final Deque<String> objectQueue;
	/** Stores the current TextImages with their tags as keys.*/
	private final Map<String, TextImage> cache;
	/** Sub-Factory for actually creating images. */
	private final TextImageFactory factory;

	/**
	 * Ctor.
	 */
	public CachedTextImageFactory() {
		cacheCapacity = determineCacheCapacity();
		objectQueue = new LinkedList<>();
		cache = new HashMap<>();
		factory = new SwitchedTextImageFactory();
	}

	/**
	 * Determines the cache's capacity. This is either specified by a System Property
	 * or, if none is provided, is set to a default value of 10 items.
	 * @return The cache's capacity to be set.
	 */
	private int determineCacheCapacity() {

		final int capacity;
		final String propertyValue = System.getProperty("cachecapacity");
		if(propertyValue == null)
			capacity = DEFAULT_CACHE_CAPACITY;
		else {
			capacity = Integer.parseInt(propertyValue);
			if(capacity < 1)
				throw new IllegalArgumentException("Capacity must be > 1");
		}
		return capacity;
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.factory.TextImageFactory#make(java.lang.String, java.lang.String[])
	 */
	@Override
	public TextImage make(final String typename, final String... args) throws ClassNotFoundException {

		final TextImage image;
		final String imageTag = makeImageTag(typename, args);
		final Set<String> keySet = cache.keySet();

		if(keySet.contains(imageTag)) {
			image = cache.get(imageTag);
		}
		else {
			image = factory.make(typename, args);
			insertElementInCache(imageTag, image);
		}

		return image;
	}

	/**
	 * Creates an identifier for TextImages based on their arguments.
	 * @param typename The desired TextImage type
	 * @param args Arguments for TextImage creation, p.e. their size
	 * @return An identifying tag as String
	 */
	private String makeImageTag(final String typename, final String... args) {

		final StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(typename);

		for(final String arg : args)
			strBuilder.append(arg);

		return strBuilder.toString();
	}

	/* (non-Javadoc)
	 * @see edu.hm.iny.patterns.factory.TextImageFactory#make(java.lang.String, edu.hm.cs.rs.arch.pattern.decorator.TextImage)
	 */
	@Override
	public TextImage make(final String typename, final TextImage textImage) throws ClassNotFoundException {

		final TextImage image;
		final String imageHash = String.valueOf(textImage.hashCode());
		final String imageTag = makeImageTag(typename, imageHash);
		final Set<String> keySet = cache.keySet();

		if(keySet.contains(imageTag)) {
			image = cache.get(imageTag);
		}
		else {
			image = factory.make(typename, textImage);
			insertElementInCache(imageTag, image);
		}

		return image;
	}

	/**
	 * Stores an element in the cache. If the cache is already full, the oldest
	 * TextImage element is deleted.
	 * @param imageTag The image identifier.
	 * @param image The actual image.
	 */
	private void insertElementInCache(final String imageTag, final TextImage image) {

		if(cache.size() == cacheCapacity) {
			// oldest object taken from head
			final String tagOfOldestObject = objectQueue.pop();
			cache.remove(tagOfOldestObject);
		}

		// newest object goes to end of queue
		objectQueue.add(imageTag);
		cache.put(imageTag, image);
	}
}
