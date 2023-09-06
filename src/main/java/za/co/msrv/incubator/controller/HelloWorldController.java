package za.co.msrv.incubator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Hello World Controller")
@RequestMapping("/api/v1/hello-world")
public class HelloWorldController {
    private final String successMessage;

    public HelloWorldController(String successMessage) {
        this.successMessage = successMessage;
    }

    @GetMapping
    @ApiOperation(value = "Says Hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/message")
    @ApiOperation(value = "Sends back the message from the user")
    public ResponseEntity<String> sendMessage(@ApiParam(value = "The message to send to the API")
                                                  @RequestBody String userMessage) {
        return ResponseEntity.ok(userMessage);
    }
}