import { useState } from "react";
import API from "../api/axios";
import { useNavigate, Link } from "react-router-dom";
import { TextField, Button, Card, CardContent, Typography } from "@mui/material";

export default function Register() {
    const nav = useNavigate();

    const [form, setForm] = useState({ username: "", password: "", role: "USER" });

    const register = async () => {
        try {
            await API.post("/api/auth/register", form);
            alert("Registration successful!");
            nav("/login");
        } catch (err) {
            alert("Registration failed");
        }
    };

    return (
        <div style={styles.center}>
            <Card style={styles.card}>
                <CardContent>
                    <Typography variant="h5" gutterBottom>Register</Typography>

                    <TextField fullWidth margin="normal" label="Username"
                               onChange={(e) => setForm({ ...form, username: e.target.value })} />

                    <TextField fullWidth margin="normal" label="Password" type="password"
                               onChange={(e) => setForm({ ...form, password: e.target.value })} />

                    <Button fullWidth variant="contained" onClick={register} style={{ marginTop: 15 }}>
                        Register
                    </Button>

                    <Typography style={{ marginTop: 10 }}>
                        Already have an account? <Link to="/login">Login</Link>
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
