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
    Cell testCell;
    @Before
    public void setUp(){
        testCell = new Cell();
    }
    @Test
    public void testCell(){
        assertNotNull(testCell);

    }

    @Test
    public void testName(){
        Cell c = new Cell();
        c.setName("C00");
        assertEquals("C00", c.name());
    }

    @Test
    public void testValidName(){
        String shortName = "C2";
        String startsWithNumber = "910";

        assertFalse(testCell.setName(shortName));
        assertFalse(testCell.setName(startsWithNumber));
    }


}
