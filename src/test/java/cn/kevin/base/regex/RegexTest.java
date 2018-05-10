package cn.kevin.base.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则测试类
 * created by yongkang.zhang
 * added at 2018/2/9
 */
public class RegexTest {

    @Test
    public void test() {
        String insert = "insert into fresh_afs_audit (AUDIT_ID, SERVICE_ID, AUDIT_KEY, AUDIT_NAME, AUDIT_RESULT,AUDIT_REMARK,AUDIT_PIN, AUDIT_TIME,CREATED)\n" +
                "        values ( #{auditId, jdbcType=BIGINT},  #{serviceId,jdbcType=BIGINT}, #{auditKey,jdbcType=VARCHAR}, #{auditName,jdbcType=VARCHAR}, #{auditResult,jdbcType=INTEGER},\n" +
                "        #{auditRemark,jdbcType=VARCHAR}, #{auditPin,jdbcType=VARCHAR},#{auditTime,jdbcType=TIMESTAMP}, #{created,jdbcType=TIMESTAMP})";

        // 打算使用group来进行match
        String patternStr = "(\\(.*\\)).*(values).*(\\(.*\\))";
        Pattern pattern = Pattern.compile(patternStr);
        // 先去掉换行符
        String str = insert.replace("\n", "");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("group 1:" + matcher.group(1));
            System.out.println("group 2:" + matcher.group(2));
            System.out.println("group 3:" + matcher.group(3));
        }
    }
}
