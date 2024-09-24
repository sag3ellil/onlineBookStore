package org.example.model;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.entity.Book;
import org.example.entity.OrderItem;
import org.example.util.JSON;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.*;


/**
 * OrderRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-09-17T14:37:35.878933200+02:00[Europe/Brussels]")
public class OrderRequest {
  public static final String SERIALIZED_NAME_BOOKS = "books";
  @SerializedName(SERIALIZED_NAME_BOOKS)
  @NotEmpty(message = "should contain a book")
  private List<OrderItem> books;

  public static final String SERIALIZED_NAME_USER_ID = "userId";
  @SerializedName(SERIALIZED_NAME_USER_ID)
  @NotBlank(message = "userId is mandatory")
  private Integer userId;

  public static final String SERIALIZED_NAME_TOTAL_PRICE = "totalPrice";
  @SerializedName(SERIALIZED_NAME_TOTAL_PRICE)
  @NotBlank(message = "totalPrice is mandatory")
  private Double totalPrice;
  public OrderRequest() {
  }

  public OrderRequest books(List<OrderItem> books) {
    this.books = books;
    return this;
  }

  public OrderRequest addBooksItem(OrderItem booksItem) {
    if (this.books == null) {
      this.books = new ArrayList<>();
    }
    this.books.add(booksItem);
    return this;
  }

   /**
   * Get books
   * @return books
  **/
@Nullable
  public List<OrderItem> getBooks() {
    return books;
  }

  public void setBooks(List<OrderItem> books) {
    this.books = books;
  }


  public OrderRequest userId(Integer userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Get userId
   * @return userId
  **/
@Nullable
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


    /**
     * Get userId
     * @return userId
     **/
    @Nullable
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderRequest orderRequest = (OrderRequest) o;
    return Objects.equals(this.books, orderRequest.books) &&
        Objects.equals(this.userId, orderRequest.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(books, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderRequest {\n");
    sb.append("    books: ").append(toIndentedString(books)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("books");
    openapiFields.add("userId");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to OrderRequest
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!OrderRequest.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in OrderRequest is not found in the empty JSON string", OrderRequest.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!OrderRequest.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `OrderRequest` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (jsonObj.get("books") != null && !jsonObj.get("books").isJsonNull()) {
        JsonArray jsonArraybooks = jsonObj.getAsJsonArray("books");
        if (jsonArraybooks != null) {
          // ensure the json data is an array
          if (!jsonObj.get("books").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `books` to be an array in the JSON string but got `%s`", jsonObj.get("books").toString()));
          }

          // validate the optional field `books` (array)
          for (int i = 0; i < jsonArraybooks.size(); i++) {
            Book.validateJsonElement(jsonArraybooks.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!OrderRequest.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'OrderRequest' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<OrderRequest> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(OrderRequest.class));

       return (TypeAdapter<T>) new TypeAdapter<OrderRequest>() {
           @Override
           public void write(JsonWriter out, OrderRequest value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public OrderRequest read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of OrderRequest given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of OrderRequest
  * @throws IOException if the JSON string is invalid with respect to OrderRequest
  */
  public static OrderRequest fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, OrderRequest.class);
  }

 /**
  * Convert an instance of OrderRequest to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

