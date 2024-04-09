
import com.ruoyi.core.service.SelectUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test1 {

    @Autowired
    SelectUser selectuser;
    @Test
    public void test1(){
        System.out.println(selectuser.selectUser(2L));
    }
}
