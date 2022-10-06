package com.codecool.dungeoncrawl.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseModelTest {

    @Test
    void testGetIdNull() {
        assertThrows(NullPointerException.class, ()-> {
            BaseModel baseModel = new BaseModel();
            baseModel.getId();
        });
    }

    @Test
    void testSetId() {
        BaseModel baseModel = new BaseModel();
        baseModel.setId(99);
        assertEquals(99, baseModel.getId());
    }

    @Test
    void testToString() {
        BaseModel baseModel = new BaseModel();
        assertEquals("", baseModel.toString());
    }
}