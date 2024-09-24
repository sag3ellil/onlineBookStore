import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    if (username && password) {
      axios.post('http://localhost:8081/api/auth/register', {
        username,
        password,
      })
      .then((response) => {
        console.log(response)
        setSuccess('Registration successful! You can now login.');
        setError(null);
        setUsername('');
        setPassword('');
        setTimeout(() => navigate('/login'), 2000); // Redirect to login after a short delay
      })
      .catch((error) => {
        setError(error.response?.data?.message || 'Registration failed');
      });
    } else {
      setError('Please enter a valid username and password');
    }
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      {error && <p className="error">{error}</p>}
      {success && <p className="success">{success}</p>}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">Register</button>
      </form>
      <p>Already have an account? <a href="/login">Login</a></p>
    </div>
  );
};

export default Register;
