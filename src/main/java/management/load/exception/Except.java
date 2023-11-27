//package management.load.exception;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ProblemDetail;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.nio.file.AccessDeniedException;
//
//@RestControllerAdvice
//public class Except {
//
//    @ExceptionHandler(Exception.class)
//    public ProblemDetail handleSecurity(Exception ex){
//        ProblemDetail error=null;
//        if(ex instanceof AccessDeniedException){
//            error=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),ex.getMessage());
//            error.setProperty("Access Denied","U are not suppose to access this method");
//        }
//        return  error;
//
//    }
//}
