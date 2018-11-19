package cn.kevin.config;

import com.AnimalConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

/**
 * @author yongkang.zhang
 * created at 19/11/2018
 */
public class AnimalConfigurationSelector extends AdviceModeImportSelector<EnableAnimal> {

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        return new String[]{AnimalConfiguration.class.getName()};
    }
}
