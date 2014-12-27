package com.xhh.demo.http.practice.mock;

import com.xhh.demo.http.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

/**
 * mock测试
 *
 * <p>spinrg + mock 测试的例子
 *
 * <p>参考：http://www.cnblogs.com/syxchina/p/4150879.html</p>
 *
 * @author tiger
 * @version 1.0.0 createTime: 14/12/27 下午9:03
 * @since 1.6
 */
public class MockTest extends BaseTest {

    @Mock
    private OrderCreate orderCreate;

    @Autowired
    private OrderHelper orderHelper;

    @Mock
    private OrderHelper spyOrderHelper;

    @Before
    public void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(AopTargetUtils.getTarget(orderCreate), "orderHelper", spyOrderHelper);
        doReturn(11).when(orderCreate).getAmt();
        doReturn("success").when(spyOrderHelper).resolve();
        doCallRealMethod().when(orderCreate).create();
    }

    @After
    public void clearMocks() throws Exception {
        ReflectionTestUtils.setField(AopTargetUtils.getTarget(orderCreate), "orderHelper", orderHelper);
    }

    @Test
    public void create() {
        System.out.println("start mock...");
        orderCreate.create();
    }
}
