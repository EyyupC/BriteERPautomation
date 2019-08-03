package com.BriteERP.utilities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeClass
    public void setup(){

    }

    @BeforeMethod
    public void setupApplication(){

    }

    @AfterMethod
    public void tearDown(){

    }

    @AfterClass
    public void logout(){

    }



}
