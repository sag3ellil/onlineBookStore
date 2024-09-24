package org.example.controller;

import org.example.model.Response;
import org.example.entity.User;
import org.example.model.UserRequest;
import org.example.service.UserService;
import org.example.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * User login
     *
     * @param userRequest Login credentials (required)
     * @return User
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> Login successful </td><td>  -  </td></tr>
    </table>
     */
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid UserRequest userRequest) {

        Response response = new Response();
        try {

            // Fetch the user by email
            Optional<User> optuser = userService.findByUserName(userRequest.getUsername());
            if (optuser.isPresent()) {


                User user = optuser.get();

                    String username = userRequest.getUsername();
                    String password = userRequest.getPassword();
                    try {
                        Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(username, password)
                        );
                        // Set authentication context
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        user.setPassword(""); // Clear password before returning user object
                        response.setData(user);
                        response.setError("");
                        response.setCode(0);
                        return ResponseEntity.ok(response);
                    } catch (BadCredentialsException e) {
                        response.setData(null);
                        response.setError("Bad credentials email or password incorrect!");
                        response.setCode(1);
                        LOGGER.log(Level.WARNING, "Bad credentials for user: " + userRequest.getUsername());
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                    }

            } else {
                response.setData(null);
                response.setError("User not found !");
                response.setCode(1);
                LOGGER.log(Level.INFO, "User not found: " + userRequest.getUsername());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setData(null);
            response.setError("Internal server error");
            response.setCode(1);
            LOGGER.log(Level.SEVERE, "Exception during signin", e);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    /**
     * Register a new user
     *
     * @param userRequest User registration data (required)
     * @return User
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 201 </td><td> User registered successfully </td><td>  -  </td></tr>
    </table>
     */
    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid UserRequest userRequest) throws UserService.UsernameAlreadyExistsException {
        Response response = new Response();
        try {
            User u = userService.createUser(userRequest.getUsername(), userRequest.getPassword());

            response.setCode(0);
            response.setData(u);
            response.setError("");
            LOGGER.log(Level.INFO, "User created successfully");
            return ResponseEntity.ok().body(response);
        }
        catch (Exception e)
        {
            response.setCode(1);
            response.setData(null);
            response.setError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @PostMapping("/signout")
    public ResponseEntity<Response> signout(@RequestBody @Valid UserRequest userRequest) {
        Response response = new Response();
        try {
            response.setCode(0);
            response.setData("Signed out successfully");
            response.setError("");
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok(response);

        }catch(Exception e)
        {
            response.setCode(1);
            response.setError(e.getMessage());
            LOGGER.log(Level.INFO, e.getStackTrace().toString());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllUsers() {

        Response response = new Response();
        try {
            response.setCode(0);
            response.setData(userService.getAllUsers());
            response.setError("");
            return ResponseEntity.ok().body(response);
        } catch (Exception e)
        {
            response.setCode(1);
            response.setData(null);
            response.setError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
