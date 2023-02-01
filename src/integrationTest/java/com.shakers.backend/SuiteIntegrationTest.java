package com.shakers.backend;


import com.shakers.backend.rest.TestPricesController;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
@Suite
@SelectClasses({
        TestPricesController.class
})
public class SuiteIntegrationTest {



}
