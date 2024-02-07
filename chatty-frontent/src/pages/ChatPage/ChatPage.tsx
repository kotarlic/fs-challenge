import React, { ChangeEvent, useEffect, useRef, useState } from 'react';
import classes from './ChatPage.module.scss';
import Input from 'components/core/Input/Input';
import Button from 'components/core/Button/Button';
import { ReactComponent as SendIcon } from 'assets/svg/send.svg';
import { Message } from 'model/Message';
import { useNavigate } from 'react-router-dom';
import { useSubscription } from 'react-stomp-hooks';
import { getAllMessages, sendMessage } from 'services/MessageService';
import MessageComponent from 'components/Message/MessageComponent';

const ChatPage: React.FC = () => {
  const [messages, setMessages] = useState<Message[]>([]);
  const [newMessage, setNewMessage] = useState<string>('');
  const navigate = useNavigate();
  const messagesContainer = useRef<HTMLDivElement>(null);

  const username = sessionStorage.getItem('username');

  if (!username) navigate('/');

  useSubscription('/api/messages', (message) => {
    setMessages([...messages, JSON.parse(message.body)]);
  });

  const handleOnChange = (event: ChangeEvent<HTMLInputElement>) => {
    setNewMessage(event.currentTarget.value);
  };

  const handleOnKeyUp = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      handleSendMessage();
    }
  };

  const scrollToLastMessage = () => {
    const lastMessage = messagesContainer.current?.lastElementChild;
    lastMessage?.scrollIntoView({ behavior: 'smooth' });
  };

  const handleSendMessage = () => {
    sendMessage({ text: newMessage, createdAt: new Date(), sender: username ?? '' }).then(() => {
      setNewMessage('');
      scrollToLastMessage();
    });
  };

  useEffect(() => {
    getAllMessages().then((res) => {
      setMessages(res.data);
      scrollToLastMessage();
    });
  }, []);

  return (
    <div className={classes['c-chat-page']}>
      <div className={classes['c-chat-page__chat-container']} ref={messagesContainer}>
        {messages.map((m, i) => (
          <MessageComponent key={i} message={m} sentByUser={username === m.sender} />
        ))}
      </div>
      <div className={classes['c-chat-page__user-actions']}>
        <Input
          type='textarea'
          onChange={handleOnChange}
          onKeyUp={handleOnKeyUp}
          value={newMessage}
          variant='large'
        />
        <Button
          label={<SendIcon width={24} height={24} />}
          variant='primary'
          onClick={handleSendMessage}
          size='icon'
        />
      </div>
    </div>
  );
};

export default ChatPage;
