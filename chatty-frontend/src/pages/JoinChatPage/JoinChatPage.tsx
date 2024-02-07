import React, { ChangeEvent, useState } from 'react';
import classes from './JoinChatPage.module.scss';
import Input from 'components/core/Input/Input';
import Button from 'components/core/Button/Button';
import { ReactComponent as ChatImage } from 'assets/svg/chat-illustration.svg';
import { useNavigate } from 'react-router-dom';
import { getOrCreateUser } from 'services/UserService';

const JoinChatPage: React.FC = () => {
  const [username, setUsername] = useState<string>('');
  const navigate = useNavigate();

  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUsername(event.currentTarget.value);
  };

  const handleJoin = () => {
    if (!username) return;
    getOrCreateUser({ username }).then(() => {
      sessionStorage.setItem('username', username);
      navigate('/chat');
    });
  };

  return (
    <div className={classes['c-join-chat-page']}>
      <div
        className={`${classes['c-join-chat-page__container']} ${classes['c-join-chat-page__container--white']}`}
      >
        <ChatImage width={400} height={300} />
      </div>
      <div className={classes['c-join-chat-page__container']}>
        <h1 className={classes['c-join-chat-page__title']}>Welcome to chatty!</h1>
        <Input
          onChange={handleChange}
          placeholder='Please enter your username'
          type='text'
          variant='medium'
        />
        <Button onClick={handleJoin} label='Join chat' variant='secondary' size='medium' />
      </div>
    </div>
  );
};

export default JoinChatPage;
