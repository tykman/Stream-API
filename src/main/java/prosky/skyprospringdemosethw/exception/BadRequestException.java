package prosky.skyprospringdemosethw.exception;


import org.springframework.web.client.HttpStatusCodeException;
import static org.springframework.http.HttpStatus.*;

public class BadRequestException extends HttpStatusCodeException {
    public BadRequestException(String message) {
        super(BAD_REQUEST,message);
    }
}
