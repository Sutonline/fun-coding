package cn.kevin.validation;

import cn.kevin.ManApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

/**
 * @author yongkang.zhang
 * created at 09/07/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManApplication.class)
@ActiveProfiles(profiles = {"dev"})
@Slf4j
public class ValidatorTest {

    @Autowired
    private Validator validator;

    @Test
    public void bindTest() {
        PersonForm personForm = new PersonForm();
        personForm.setName("");
        personForm.setAge(-5);

        DataBinder dataBinder = new DataBinder(personForm);
        dataBinder.setValidator(validator);

        dataBinder.validate();

        log.info("验证结果是: {}", dataBinder.getBindingResult());
    }
}
