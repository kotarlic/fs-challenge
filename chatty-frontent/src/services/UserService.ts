import { axios } from 'lib/axios/config';
import { User } from 'model/User';

export const getOrCreateUser = async (user: User) => {
  return await axios.post('/api/users', user);
};
