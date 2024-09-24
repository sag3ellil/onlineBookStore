import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate, Link } from 'react-router-dom';
import BookList from './components/BookList';
import Cart from './components/Cart';
import Login from './components/Login';
import Register from './components/Register';
import Checkout from './components/Checkout';
import axios from 'axios';

const App = () => {
  const [cart, setCart] = useState([]);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState(null);
  const [notification, setNotification] = useState(''); // State for notification message
  const [showNotification, setShowNotification] = useState(false);

   const addToCart = (book) => {
    setCart(prevCart => {
      const existingItem = prevCart.find(item => item.id === book.id);
      let updatedCart;

      if (existingItem) {
        updatedCart = prevCart.map(item => 
          item.id === book.id ? { ...item, quantity: item.quantity + 1 } : item
        );
      } else {
        updatedCart = [...prevCart, { ...book, quantity: 1 }];
      }
    
     /* // Show notification
      setNotification(`${book.title} has been added to your cart!`);
      setShowNotification(true);

      // Hide notification after 3 seconds
      setTimeout(() => {
        setShowNotification(false);
      }, 3000);*/

      
      return updatedCart;
    });
    alert('book added successfully!');
  };


  const updateQuantity = (bookId, quantity) => {
    setCart(prevCart => 
      prevCart.map(item => 
        item.id === bookId ? { ...item, quantity } : item
      )
    );
  };

  const removeFromCart = (bookId) => {
    setCart(prevCart => prevCart.filter(item => item.id !== bookId));
  };

  const resetCart = () => {
    setCart([]);
  };
  const handleLogin = (username) => {
    setIsAuthenticated(true);
    setUsername(username);
  };

  const handleLogout = () => {
    setIsAuthenticated(false);
    setUsername(null);
  };

  const submitOrder = (totalPrice) => {
    const username = localStorage.getItem('username');
    const usernameId = localStorage.getItem('usernameId');
    
    if (!username) {
      window.location.href = '/login';
      return;
    }

    axios.post('http://127.0.0.1:8081/api/order', {
      userId: usernameId,
      items: cart.map(item => ({ bookId: item.id, quantity: item.quantity })),
      totalPrice: totalPrice
    })
    .then(response => {
      alert('Order submitted successfully!');
      setCart([]); // Clear cart after order submission
    })
    .catch(error => {
      console.error('Failed to submit order', error);
    });
  };
 
    return (
      <Router>
        <div>
          <header>
            <nav>
              <Link to="/">Home</Link>
              {isAuthenticated ? (
                <>
                  <span>Welcome, {username}</span>
                  <button onClick={handleLogout}>Logout</button>
                  <Link to="/cart">Cart</Link>
                  <Link to="/checkout">Checkout</Link>
                </>
              ) : (
                <>
                  <Link to="/login">Login</Link>
                  <Link to="/register">Register</Link>
                  <Link to="/cart">Cart</Link>
                  <Link to="/checkout">Checkout</Link>
                </>
              )}
            </nav>
          </header>
          <main>
            <Routes>
              <Route path="/" element={<BookList addToCart={addToCart} />} />
              <Route path="/cart" element={<Cart cart={cart} removeFromCart={removeFromCart} updateQuantity={updateQuantity} /> 
                  } 
              />
              <Route path="/login" element={<Login handleLogin={handleLogin} />} />
              <Route path="/register" element={<Register />} />
              <Route path="/checkout" 
                element={isAuthenticated ? 
                  <Checkout cart={cart} resetCart ={resetCart} submitOrder={submitOrder} /> 
                  : <Navigate to="/login" />} 
              />
              <Route path="*" element={<Navigate to="/" />} />
            </Routes>
          </main>
        </div>
      </Router>
    );
}

export default App;
