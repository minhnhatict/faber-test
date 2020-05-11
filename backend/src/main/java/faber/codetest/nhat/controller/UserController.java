package faber.codetest.nhat.controller;

import faber.codetest.nhat.model.CustomUserPrincipal;
import faber.codetest.nhat.model.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import faber.codetest.nhat.util.AuthenticationUtil;

@RestController
@RequestMapping(value = "/api/public/user")
public class UserController {

    /**
     * get user profile of authenticated user
     *
     * @return
     */
    @GetMapping(value = "/profile")
    public ResponseEntity<ResponseDto> getUserProfile() {
        ResponseDto responseDto = new ResponseDto();

        if (AuthenticationUtil.isAuthenticated()) {
            CustomUserPrincipal customUserPrincipal = AuthenticationUtil.getPrincipal();
            responseDto.setSuccess(Boolean.TRUE);
            responseDto.setData(customUserPrincipal);

            return ResponseEntity.ok(responseDto);
        }
        responseDto.setSuccess(Boolean.FALSE);
        return ResponseEntity.ok(responseDto);
    }
}
