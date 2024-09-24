package org.example.controller;

import org.example.model.Response;
import org.example.service.BookService;
import org.example.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;


    /**
     * Retrieve a list of all available books
     *
     * @return List&lt;Book&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 200 </td><td> A list of books </td><td>  -  </td></tr>
    </table>
     */
    @GetMapping
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Response> getBooks( @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        Response response = new Response();
        try {
            response.setCode(0);
            response.setData(bookService.getAllBooks(page,size));
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
