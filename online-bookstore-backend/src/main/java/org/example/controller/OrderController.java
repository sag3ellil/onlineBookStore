package org.example.controller;

import com.google.gson.reflect.TypeToken;
import org.example.entity.Order;
import org.example.model.OrderRequest;
import org.example.model.Response;
import org.example.service.OrderService;
import org.example.util.ApiException;
import org.example.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Create a new order containing selected books
     *
     * @param orderRequest List of books to order and user ID (required)
     * @return ApiResponse&lt;Order&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
    <table summary="Response Details" border="1">
    <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
    <tr><td> 201 </td><td> Order successfully created </td><td>  -  </td></tr>
    </table>
     */
    @PostMapping
    public ResponseEntity<Response> placeOrder(@RequestBody @Valid OrderRequest orderRequest) throws ApiException {
        Response response = new Response();
        try {
            response.setCode(0);
            Order order = orderService.placeOrder(orderRequest);
            response.setData(order);
            response.setError("");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e)
        {
            response.setCode(1);
            response.setData(null);
            response.setError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getOrderByOrderId(@PathVariable Integer id)  {
        Response response = new Response();
        try {
            response.setCode(0);
            Order order = orderService.getOrderByOrderId(id);
            response.setData(order);
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
