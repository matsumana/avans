package me.geso.sample;

import org.junit.BeforeClass;

import com.google.inject.Guice;
import com.google.inject.Injector;

import me.geso.sample.config.Config;
import me.geso.sample.module.BasicModule;
import me.geso.sample.module.CLIModule;
import me.geso.sample.provider.ConfigProvider;

public class TestBase {
	protected static Config config;

	@BeforeClass
	public static void setupClass() {
		String env = System.getProperty("sample.env");
		if (env == null) {
			System.setProperty("sample.env", "test");
		}
		env = System.getProperty("sample.env");
		if (!(env.equals("test"))) {
			throw new RuntimeException("Do not run test case on non-test environment");
		}

		config = new ConfigProvider().get();
	}

	protected Injector getInjector() {
		return Guice.createInjector(
			new BasicModule(config),
			new CLIModule());
	}
}
