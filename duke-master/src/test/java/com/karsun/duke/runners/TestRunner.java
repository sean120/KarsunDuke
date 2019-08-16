package com.karsun.duke.runners;

import org.junit.runner.RunWith;

import com.karsun.kic.tan.duke.annotations.TestDataFiles;
import com.karsun.kic.tan.duke.cukes.MergedDataInjectedCucumber;

import cucumber.api.CucumberOptions;

@RunWith(MergedDataInjectedCucumber.class)
@CucumberOptions(plugin = { "json:build/test-runner.json", "html:build/test-runner" },
		//tags = {"@actiTest001,@actiTest002,@actiTest003"},
		tags = { "@tags1222" },
//		tags = {"@ultimatehqtest"},
//		tags = { "~@wip"},
		features = { "src/test/resources/features" },
		monochrome = true, format = { "pretty" },
		glue = {"com.karsun.kic.tan", "org.openqa", "com.karsun.duke/steps" }, 
		dryRun = false)
@TestDataFiles(files = { "src/test/resources/data/data.json" })
public class TestRunner {
} 


