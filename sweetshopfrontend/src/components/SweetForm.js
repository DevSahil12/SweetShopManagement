import { useState } from "react";
import API from "../api/api";
import { TextField, Button, Card, CardContent, Typography } from "@mui/material";

export default function SweetForm({ reload, close }) {

    const [sweet, setSweet] = useState({
        name: "",
        category: "",
        price: 0,
        quantity: 0
    });

    const save = async () => {
        try {
            const res = await API.post("/api/sweets", sweet);
            console.log("Saved:", res.data);
            console.log("PROPS RECEIVED BY SweetForm:", { reload, close });
            reload();  // ✔ works only if parent passes it
            close();

        } catch (err) {
            console.error("SAVE ERROR:", err);
        }
    };
    return (
        <Card sx={{ mt: 2, p: 2 }}>
            <CardContent>
                <Typography variant="h6" gutterBottom>
                    Add Sweet
                </Typography>

                <TextField
                    fullWidth
                    margin="normal"
                    label="Sweet Name"
                    value={sweet.name}
                    onChange={e => setSweet({ ...sweet, name: e.target.value })}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Category"
                    value={sweet.category}
                    onChange={e => setSweet({ ...sweet, category: e.target.value })}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Price"
                    type="number"
                    value={sweet.price}
                    onChange={e => setSweet({ ...sweet, price: Number(e.target.value) })}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Quantity"
                    type="number"
                    value={sweet.quantity}
                    onChange={e => setSweet({ ...sweet, quantity: Number(e.target.value) })}
                />

                <Button
                    variant="contained"
                    fullWidth
                    sx={{ mt: 2 }}
                    onClick={save}
                >
                    Save
                </Button>

                <Button
                    fullWidth
                    color="secondary"
                    sx={{ mt: 1 }}
                    onClick={close}
                >
                    Cancel
                </Button>
            </CardContent>
        </Card>
    );
}
