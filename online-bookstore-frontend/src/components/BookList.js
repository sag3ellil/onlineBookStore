import React, { useState, useEffect } from 'react';
import axios from 'axios';

const BookList = ({ addToCart }) => {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10); // Default page size
  const [totalPages, setTotalPages] = useState(1);
  const [notification, setNotification] = useState(''); // State for notification message
  const [showNotification, setShowNotification] = useState(false);
  useEffect(() => {
    fetchBooks(page, size);
  }, [page, size]);

  const fetchBooks = (page, size) => {
    setLoading(true);
    axios.get(`http://127.0.0.1:8081/api/book?page=${page}&size=${size}`)
      .then((response) => {
        console.log(response);
        setBooks(response.data.data.content);
        setTotalPages(response.data.data.totalPages); // If pagination info is returned from the backend
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  };

  const handleAddToCart = (book) => {
    addToCart(book); // Update cart state in the parent component
  };

  if (loading) {
    return <div>Loading books...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <div className="book-list-container">
     
      <h2>Available Books</h2>
      {showNotification && (
        <div className="notification">
          {notification}
        </div>
      )}
      <div className="book-list">
     
        {books.map((book) => (
          <div className="book-item" key={book.id}>
            <h3>{book.title}</h3>
            <p>Author: {book.author}</p>
            <p>Price: ${book.price}</p>
            <button onClick={() => handleAddToCart(book)}>Add to Cart</button>
          </div>
        ))}
      </div>

      {/* Pagination controls */}
    <div className="pagination">
      <button className="pagination-btn" disabled={page <= 0} onClick={() => setPage(page - 1)}>
      Previous
      </button>
      <span className="pagination-info" >Page {page + 1} of {totalPages}</span>
      <button className="pagination-btn" disabled={page >= totalPages - 1} onClick={() => setPage(page + 1)}>
      Next
      </button>
    </div>

    </div>
  );
};

export default BookList;
