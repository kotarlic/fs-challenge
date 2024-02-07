import React from 'react';
import ReactDOM from 'react-dom/client';
import 'styles/index.scss';
import App from 'App';
import { BrowserRouter } from 'react-router-dom';
import { StompSessionProvider } from 'react-stomp-hooks';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <StompSessionProvider url={`${process.env.REACT_APP_BACKEND_URL}/chat`}>
        <App />
      </StompSessionProvider>
    </BrowserRouter>
  </React.StrictMode>
);
