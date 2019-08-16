package com.seanfiles.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/TestFeatures" }, glue = {
		"com.seanfiles.stepdefinition" }, plugin = { "pretty",
				"json:target/cucumber.json" }, tags = {"@Iteration04323" })
//@ACE2_0Iteration03
public class TestRunner {

}
