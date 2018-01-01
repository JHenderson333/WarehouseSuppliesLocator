package com.example.jhend.warehousesupplieslocator.modelTest;


import com.example.jhend.warehousesupplieslocator.model.Cell;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit Tests for Cell
 * @author James D Henderson
 */

public class CellTest {
    @Before
    public void setUp(){

    }
    @Test
    public void testCell(){
        Cell c = new Cell();
        assertNotNull(c);

    }

    @Test
    public void testName(){
        Cell c = new Cell();
        c.setName("C00");
        assertEquals("C00", c.name());
    }


}
