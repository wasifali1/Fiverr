/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junitselenium;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author user
 */
public class JUnitSeleniumTestScriptTest {
    
    public JUnitSeleniumTestScriptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        JUnitSeleniumTestScript.openBrowser();
    }
    
    @AfterClass
    public static void tearDownClass() {
         JUnitSeleniumTestScript.closeBrowser();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compareWebpage_FileData method, of class JUnitSeleniumTestScript.
     */
    @Test
    public void testCompareWebpage_FileData() {
        System.out.println("compareWebpage_FileData");
        JUnitSeleniumTestScript instance = new JUnitSeleniumTestScript();
        instance.compareWebpage_FileData();
    }    
}
