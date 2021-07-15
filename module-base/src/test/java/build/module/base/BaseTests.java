package build.module.base;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;

@SpringBootTest
class BaseTests {

    @Test
    void contextLoads() {
        System.out.println("test context .. ");
    }

    @Test
    public void testA() {

    }

}
