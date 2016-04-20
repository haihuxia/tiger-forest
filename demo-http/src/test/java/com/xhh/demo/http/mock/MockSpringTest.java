package com.xhh.demo.http.mock;

import com.xhh.demo.http.BaseSpringTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import static org.mockito.Mockito.*;

/**
 * mock 测试
 *
 * <p>spinrg + mock 测试的例子
 *
 * @author tiger
 * @version 1.0.0 createTime: 15-1-8 下午5:07
 * @since 1.6
 */
public class MockSpringTest extends BaseSpringTest {

    @Autowired
    private Order order;

    @Autowired
    private OrderStart orderStart;

    @InjectMocks
    private OrderCreate orderCreate = mock(OrderCreate.class);

    @Mock
    private OrderHelper orderHelperMock;

    @Before
    public void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(AopTargetUtils.getTarget(orderStart), "orderCreate", orderCreate);
        doReturn(11).when(orderCreate).getAmt();
        doReturn("success").when(orderHelperMock).resolve();
        doCallRealMethod().when(orderCreate).create();
    }

    @Test
    public void create() {
        System.out.println("start mock...");
        order.order();
    }
}
