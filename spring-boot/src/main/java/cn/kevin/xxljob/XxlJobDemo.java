package cn.kevin.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yongkang.zhang
 * created at 17/09/2018
 */
@JobHandler(value = "xxlJobDemo")
@Component
public class XxlJobDemo extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println(s);
        System.out.println(new Date() + ", 我来执行xxl-job了");
        return ReturnT.SUCCESS;
    }
}
