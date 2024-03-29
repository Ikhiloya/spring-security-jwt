package icom.khiloyaimokhai.springsecurityjwt.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Ikhiloya Imokhai on 2019-10-14.
 */
@RestController
@RequestMapping("/api")
public class AdminProtectedRestController {

    @GetMapping("/hiddenmessage")
    public ResponseEntity<HiddenMessage> getAdminProtectedGreeting() {
        return ResponseEntity.ok(new HiddenMessage("this is a hidden message!"));
    }

    private static class HiddenMessage {

        private final String message;

        private HiddenMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}