import React, { useState } from 'react';
import classes from './Message.module.scss';
import moment from 'moment';
import { Message } from 'model/Message';
import Wrapper from 'components/layout/Wrapper/Wrapper';

interface MessageProps {
  message: Message;
  sentByUser: boolean;
}

const MessageComponent: React.FC<MessageProps> = ({ message, sentByUser }) => {
  const [showTime, setShowTime] = useState<boolean>(false);

  const handleShowTime = () => {
    setShowTime(!showTime);
  };

  return (
    <div className={`${classes['c-message']} ${sentByUser ? classes['c-message--by-user'] : ''}`}>
      <Wrapper right={sentByUser}>
        <span className={classes['c-message__sender']}>{message.sender}</span>
      </Wrapper>
      <Wrapper right={sentByUser}>
        <span
          className={`${classes['c-message__content']} ${sentByUser ? classes['c-message__content--by-user'] : ''}`}
          onClick={handleShowTime}
        >
          {message.text}
        </span>
      </Wrapper>
      {showTime && (
        <Wrapper right={sentByUser}>
          <span className={classes['c-message__time']}>
            {moment(message.createdAt).format('DD MMM YYYY HH:mm')}
          </span>
        </Wrapper>
      )}
    </div>
  );
};

export default MessageComponent;
