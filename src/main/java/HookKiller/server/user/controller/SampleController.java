package HookKiller.server.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class SampleController {
    @GetMapping("/test")
    public String fuckingBong() {
        return "fuckingBong";
    }
}
