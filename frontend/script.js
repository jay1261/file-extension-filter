const ENV = window.location.hostname === 'localhost' ? 'dev' : 'prod';

const BASE_URL = ENV === 'dev' 
    ? 'http://localhost:8080/api'
    : 'https://jay1261.com/api';

const MAX_CUSTOM = 200;
