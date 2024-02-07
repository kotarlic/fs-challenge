import React from 'react';
import JoinChatPage from 'pages/JoinChatPage/JoinChatPage';
import { Routes, Route } from 'react-router-dom';
import ChatPage from 'pages/ChatPage/ChatPage';

const App: React.FC = () => {
  return (
    <Routes>
      <Route path='/' element={<JoinChatPage />} />
      <Route path='/chat' element={<ChatPage />} />
    </Routes>
  );
};

export default App;
