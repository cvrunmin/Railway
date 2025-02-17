package com.railwayteam.railways.util;

import com.railwayteam.railways.Railways;
import dev.architectury.injectables.annotations.ExpectPlatform;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Locale;

public class Utils {
	@ExpectPlatform
	public static boolean isModLoaded(String id, @Nullable String fabricId) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static Path configDir() {
		throw new AssertionError();
	}

	@ExpectPlatform
	@Contract // shut
	public static boolean isDevEnv() {
		throw new AssertionError();
	}

	public static boolean isEnvVarTrue(String name) {
		try {
			String result = System.getenv(name);
			return result != null && result.toLowerCase(Locale.ROOT).equals("true");
		} catch (SecurityException e) {
			Railways.LOGGER.warn("Caught a security exception while trying to access environment variable `"+name+"`.");
			return false;
		}
	}
}
