package faber.codetest.nhat.controller.admin;

import faber.codetest.nhat.controller.AbstractController;
import faber.codetest.nhat.service.AbstractCrudService;
import faber.codetest.nhat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/admin/user")
public class AdminUserController extends AbstractController {

    @Autowired
    private UserService userService;

    @Override
    public AbstractCrudService getCrudService() {
        return userService;
    }

    @Override
    public Collection getDefaultList() {
        return null;
    }

}
