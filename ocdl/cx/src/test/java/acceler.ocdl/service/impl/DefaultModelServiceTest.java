package acceler.ocdl.service.impl;

import acceler.ocdl.model.Model;
import acceler.ocdl.model.User;
import acceler.ocdl.service.KubernetesService;
import acceler.ocdl.service.ModelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class DefaultModelServiceTest {

//    @Autowired
//    private ModelService service;

    @Test
    public void copyModels() {

        DefaultModelService service = new DefaultModelService();

        User user1 = new User();
        user1.setProjectId(1L);
        user1.setUserId(3L);

        assertTrue(service.copyModels(user1));
        assertTrue(service.copyModels(user1));

    }

    @Test
    public void pushModel() {

        DefaultModelService service = new DefaultModelService();

        Model model = new Model();
        model.setName("1_3_test1.model");
        model.setProjectId(1L);
        assertTrue(service.pushModel(model,"modelTest"));
    }
}