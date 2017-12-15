/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ghassan
 */
public class ItemsAvailableTest {
    
    public ItemsAvailableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class ItemsAvailable.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ItemsAvailable instance = null;
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class ItemsAvailable.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        ItemsAvailable instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemName method, of class ItemsAvailable.
     */
    @Test
    public void testGetItemName() {
        System.out.println("getItemName");
        ItemsAvailable instance = null;
        String expResult = "";
        String result = instance.getItemName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setItemName method, of class ItemsAvailable.
     */
    @Test
    public void testSetItemName() {
        System.out.println("setItemName");
        String itemName = "";
        ItemsAvailable instance = null;
        instance.setItemName(itemName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class ItemsAvailable.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        ItemsAvailable instance = null;
        int expResult = 0;
        int result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class ItemsAvailable.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        int type = 0;
        ItemsAvailable instance = null;
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrix method, of class ItemsAvailable.
     */
    @Test
    public void testGetPrix() {
        System.out.println("getPrix");
        ItemsAvailable instance = null;
        double expResult = 0.0;
        double result = instance.getPrix();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrix method, of class ItemsAvailable.
     */
    @Test
    public void testSetPrix() {
        System.out.println("setPrix");
        double prix = 0.0;
        ItemsAvailable instance = null;
        instance.setPrix(prix);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class ItemsAvailable.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ItemsAvailable instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ItemsAvailable.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        ItemsAvailable instance = null;
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ItemsAvailable.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ItemsAvailable instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
