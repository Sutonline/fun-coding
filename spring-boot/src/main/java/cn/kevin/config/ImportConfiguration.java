package cn.kevin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author yongkang.zhang
 * created at 19/11/2018
 */
@Configuration
public class ImportConfiguration implements ImportAware {

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {

    }
}
