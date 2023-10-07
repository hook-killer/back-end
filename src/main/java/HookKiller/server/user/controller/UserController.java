package HookKiller.server.user.controller;

import HookKiller.server.user.dto.request.LoginRequestDto;
import HookKiller.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping({"", "/"})
    public String home() {
        return "아무나 다 볼 수 있음";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "유저 페이지";
    }

    @GetMapping("/admin/user")
    @ResponseBody
    public String admin() {
        return "어드민 페이지";
    }

    @PostMapping("/login")
    public LoginRequestDto loginControl(@RequestBody LoginRequestDto body) {
        log.info("email : {} password : {}", body.getEmail(), body.getPassword());
        return body;
    }



}
