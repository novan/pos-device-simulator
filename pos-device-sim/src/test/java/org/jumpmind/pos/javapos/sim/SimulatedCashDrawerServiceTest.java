package org.jumpmind.pos.javapos.sim;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimulatedCashDrawerServiceTest {
    SimulatedCashDrawerService service;

    @Before
    public void before() {
        service = new SimulatedCashDrawerService();
    }

    @Test
    public void testReset() throws Exception {
        service.openDrawer();
        service.reset();
        Assert.assertFalse(service.getDrawerOpened());
    }

    @Test
    public void testOpenDrawer() throws Exception {
        service.openDrawer();
        Assert.assertTrue(service.getDrawerOpened());
    }

    @Test
    public void testToggleDrawerOpen() throws Exception {
        service.toggleDrawer(true);
        Assert.assertTrue(service.getDrawerOpened());

        service.toggleDrawer(true);
        Assert.assertTrue(service.getDrawerOpened());
    }

    @Test
    public void testToggleDrawerClose() throws Exception {
        service.toggleDrawer(false);
        Assert.assertFalse(service.getDrawerOpened());
    }

}
