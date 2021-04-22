package com.lei.test;

import com.lei.dao.imp.bookDaoimp;
import org.junit.Test;

/**
 * User:雷志刚
 * Date:2020/11/23
 * Time:16:47
 */

public class batchTest {
    @Test
    public void testBatchBooks(){
        Object[][] objects = new Object[3][];
         objects[0]=new Object[]{200,200,9};
         objects[1]=new Object[]{200,200,10};
         objects[2]=new Object[]{200,200,11};
        new bookDaoimp().batchUpdateBooks(objects);
    }
}
