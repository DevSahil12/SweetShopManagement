import { useState } from "react";
import API from "../api/axios";
import { useNavigate, Link } from "react-router-dom";
import { TextField, Button, Card, CardContent, Typography } from "@mui/material";

export default function Login() {
    const nav = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const login = async () => {
        try {
            const res = await API.post("/api/auth/login", { username, password });

            localStorage.setItem("token", res.data.token);
            nav("/dashboard");
        } catch (err) {
            alert("Invalid login");
        }
    };

    return (
        <div style={styles.center}>
            <Card style={styles.card}>
                <CardContent>
                    <Typography variant="h5" gutterBottom>Login</Typography>

                    <TextField fullWidth margin="normal" label="Username"
                               onChange={(e) => setUsername(e.target.value)} />

                    <TextField fullWidth margin="normal" label="Password" type="password"
                               onChange={(e) => setPassword(e.target.value)} />

                    <Button fullWidth variant="contained" onClick={login} style={{ marginTop: 15 }}>
                        Login
                    </Button>

                    <Typography style={{ marginTop: 10 }}>
                        Don’t have an account? <Link to="/register">Register</Link>
                    </Typography>
                </CardContent>
            </Card>
        </div>
    );
}

const styles = {
    center: {
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        background: "#f5f5f5"
    },
    card: {
        width: 350,
        padding: 20
    }
};
