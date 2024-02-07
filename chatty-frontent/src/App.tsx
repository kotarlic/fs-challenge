import React from 'react';
import JoinChatPage from 'pages/JoinChatPage/JoinChatPage';
import { Routes, Route } from 'react-router-dom';

const App: React.FC = () => {
  return (
    <Routes>
      <Route path='/' element={<JoinChatPage />} />
    </Routes>
  );
};

export default App;
