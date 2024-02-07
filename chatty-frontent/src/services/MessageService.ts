import { axios } from 'lib/axios/config';
import { Message } from 'model/Message';

export const getAllMessages = async () => {
  return axios.get('/api/messages');
};

export const sendMessage = async (message: Message) => {
  return axios.post('/api/messages', message);
};
