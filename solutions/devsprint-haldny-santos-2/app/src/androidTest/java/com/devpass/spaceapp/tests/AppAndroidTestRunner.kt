package com.devpass.spaceapp.tests

import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberAndroidJUnitRunner

@CucumberOptions(glue = ["com.devpass.spaceapp.tests"], features = ["features"], tags = ["~@wip"])
class AppAndroidTestRunner : CucumberAndroidJUnitRunner()