import React from 'react';

const Cart = ({ cart, updateQuantity, removeFromCart, submitOrder }) => {
  return (
    <div>
      <h2>Shopping Cart</h2>
      <ul>
        {cart.length === 0 ? (
          <p>Your cart is empty.</p>
        ) : (
          cart.map(item => (
            <li key={item.id}>
              <h3>{item.title}</h3>
              <p>Price: ${item.price}</p>
              <p>
                Quantity: 
                <input 
                  type="number" 
                  value={item.quantity} 
                  min="1"
                  onChange={(e) => updateQuantity(item.id, parseInt(e.target.value, 10))} 
                />
              </p>
              <button onClick={() => removeFromCart(item.id)}>Remove</button>
            </li>
          ))
        )}
      </ul>
     {/*cart.length > 0 && (
        <button className="pagination-btn" onClick={submitOrder}>Submit Order</button> 
      )*/}
    </div>
  );
};

export default Cart;
