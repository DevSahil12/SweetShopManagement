import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080",
});

// 🔥 Add token to all requests
API.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");

    if (token) {
        config.headers["Authorization"] = `Bearer ${token}`;
    }

    config.headers["Content-Type"] = "application/json";

    return config;
});

export default API;
