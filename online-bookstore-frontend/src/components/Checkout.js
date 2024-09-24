import React from 'react';
import axios from 'axios';

const Checkout = ({ cart, resetCart }) => {
  const total = cart.reduce((acc, item) => acc + item.price * item.quantity, 0);

  const handleCheckout = () => {
    const usernameId = localStorage.getItem('usernameId');
  
    // Ensure usernameId is not null or undefined
    if (!usernameId) {
      alert('User ID is missing. Please log in again.');
      return;
    }
  
    // Correct the payload structure to match the backend model
    axios.post('http://127.0.0.1:8081/api/order', {
      books: cart.map(item => ({
        book: { id: item.id }, // Referring to the book entity
        quantity: item.quantity
      })),
      userId: parseInt(usernameId, 10),  // Ensure userId is an Integer
      totalPrice: total  // Ensure totalPrice is a Double
    })
    .then(response => {
      alert('Order submitted successfully!');
      resetCart(); // Clear cart after order submission
    })
    .catch(error => {
      console.error('Failed to submit order', error);
    });
  };
  

  return (
    <div>
      <h2>Checkout</h2>
      <ul>
        {cart.map(item => (
          <li key={item.id}>
            <h3>{item.title}</h3>
            <p>Quantity: {item.quantity}</p>
            <p>Total: ${item.price * item.quantity}</p>
          </li>
        ))}
      </ul>
      <h3>Total Price: ${total}</h3>
      <button onClick={handleCheckout}>Place Order</button>
    </div>
  );
};

export default Checkout;
