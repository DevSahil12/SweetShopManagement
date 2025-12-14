import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import SweetList from "./SweetList";

export default function Dashboard() {
    const nav = useNavigate();

    const logout = () => {
        localStorage.removeItem("token");
        nav("/login");
    };

    return (
        <div style={{ padding: 20 }}>
            <Button variant="outlined" onClick={logout} style={{ float: "right" }}>
                Logout
            </Button>

            <h1>Sweet Inventory</h1>

            {/* ✔ This is correct */}
            <SweetList />
        </div>
    );
}
