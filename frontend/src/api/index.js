import axios from "axios";

// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    // baseURL: process.env.VUE_APP_API_BASE_URL,
    baseURL: "http://localhost/enjoytrip",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { apiInstance };
