package com.xhh.demo;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 单元测试父类
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/3/14 下午12:14
 * @since 1.6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BaseSpringTest extends TestCase {

}
