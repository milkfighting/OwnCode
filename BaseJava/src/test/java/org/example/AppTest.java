package org.example;

import static org.junit.Assert.assertTrue;

import org.example.object.StaticValue;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * 测试静态变量
     */
    @Test
    public void testStaticValue() {
        StaticValue name = new StaticValue("你好");
        name.printValue();
    }
}
