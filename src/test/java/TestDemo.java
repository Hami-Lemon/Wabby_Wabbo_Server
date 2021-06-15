/**
 * <p> 创建时间 2021/6/1 </p>
 *
 * @author Hami Lemon
 * @version v1.0
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class TestDemo {

    @Test
    public void test() {
        //mock list对象
        List mockList = mock(LinkedList.class);
        //打桩，调用get(1)方法时 返回demo
        when(mockList.get(1)).thenReturn("demo");
        //判断结果
        assertThat(mockList.get(1))
                .isEqualTo("demo");
        // 验证是否调用了一次get(1)方法
        verify(mockList).get(1);
        //是否调用了两次
//        verify(mockList,times(2)).get(1);
    }
}
