import axios from 'axios';

const instance = axios.create({
  baseURL: `${window.location.origin}/todo/`,
});

instance.interceptors.request.use((config) => {
  config.headers.accept = 'application/json';
  config.headers['Content-Type'] = 'application/json; charset=UTF-8';
})
