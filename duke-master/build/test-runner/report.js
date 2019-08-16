$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("facebook.feature");
formatter.feature({
  "line": 2,
  "name": "log into facebook page and check out couple of updates and confirm it is today\u0027s update with date format conformation",
  "description": "",
  "id": "log-into-facebook-page-and-check-out-couple-of-updates-and-confirm-it-is-today\u0027s-update-with-date-format-conformation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 1792550937,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 5,
      "value": "#Background:"
    },
    {
      "line": 6,
      "value": "#Given user is on facebook page"
    },
    {
      "line": 8,
      "value": "#Scenario: login functionality of social networking platform."
    },
    {
      "line": 9,
      "value": "#Given user navigate to face book page loginPage"
    },
    {
      "line": 10,
      "value": "#When user enters userName"
    },
    {
      "line": 11,
      "value": "#And user enters passWord"
    },
    {
      "line": 12,
      "value": "#And user clicks on login"
    },
    {
      "line": 13,
      "value": "#Then user should be abble to see the successful login"
    }
  ],
  "line": 19,
  "name": "after failed attemps number of times. the user should be able to login again",
  "description": "",
  "id": "log-into-facebook-page-and-check-out-couple-of-updates-and-confirm-it-is-today\u0027s-update-with-date-format-conformation;after-failed-attemps-number-of-times.-the-user-should-be-able-to-login-again",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 18,
      "name": "@myfacebook"
    }
  ]
});
formatter.step({
  "line": 20,
  "name": "the user enters set of usernames and password",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ],
      "line": 21
    },
    {
      "cells": [
        "smiddleton393@gmail.com",
        "shaungulja"
      ],
      "line": 22
    },
    {
      "cells": [
        "sthorson120@gmail.com",
        "uyghur32U"
      ],
      "line": 23
    }
  ],
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 401850566,
  "error_message": "java.lang.NullPointerException\n\tat com.karsun.kic.tan.duke.driver.WebDriverFactory.cleanSharedInstance(WebDriverFactory.java:252)\n\tat com.karsun.kic.tan.duke.ExecutionContext.tearDownScenario(ExecutionContext.java:249)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:37)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:13)\n\tat cucumber.runtime.Utils.invoke(Utils.java:31)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:223)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:211)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:205)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:91)\n\tat com.karsun.kic.tan.duke.runner.DataInjectedFeatureRunner.runChild(DataInjectedFeatureRunner.java:77)\n\tat com.karsun.kic.tan.duke.runner.DataInjectedFeatureRunner.runChild(DataInjectedFeatureRunner.java:26)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat com.karsun.kic.tan.duke.runner.DataInjectedFeatureRunner.run(DataInjectedFeatureRunner.java:84)\n\tat com.karsun.kic.tan.duke.cukes.MergedDataInjectedCucumber.runChild(MergedDataInjectedCucumber.java:140)\n\tat com.karsun.kic.tan.duke.cukes.MergedDataInjectedCucumber.runChild(MergedDataInjectedCucumber.java:34)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat com.karsun.kic.tan.duke.cukes.MergedDataInjectedCucumber.run(MergedDataInjectedCucumber.java:145)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)\n",
  "status": "failed"
});
});