import { useState, useEffect } from "react";
import API from "../api/axios";
import SweetForm from "./SweetForm";

export default function SweetList() {
    const [sweets, setSweets] = useState([]);
    const [showForm, setShowForm] = useState(false);

    const loadData = async () => {
        const res = await API.get("/api/sweets");
        setSweets(res.data);
    };

    useEffect(() => {
        loadData();
    }, []);
    const deleteSweet = async (id) => {
        await API.delete(`/api/sweets/${id}`);
        loadData();
    };

    const purchaseSweet = async (id) => {
        await API.post(`/api/sweets/${id}/purchase`);
        loadData();
    };

    const restockSweet = async (id) => {
        const qty = prompt("Enter quantity:");

        await API.post(`/api/sweets/${id}/restock`, {
            qty: Number(qty)
        });

        loadData();
    };

    return (
        <div style={{ padding: 20 }}>
            <h2>Sweets Inventory</h2>

            {/* Very important: pass the function correctly */}
            {showForm && (
                <SweetForm
                    reload={loadData}   // ✔ FUNCTION
                    close={() => setShowForm(false)} // ✔ FUNCTION
                />
            )}

            <button onClick={() => setShowForm(true)}>Add Sweet</button>

            <ul>
                {sweets.map((s) => (
                    <li key={s.id} style={{ marginBottom: "10px" }}>
                        <b>{s.name}</b> — {s.category} — {s.quantity} pcs — ₹{s.price}

                        <button
                            style={{ marginLeft: "10px" }}
                            onClick={() => purchaseSweet(s.id)}
                        >
                            Purchase
                        </button>

                        <button
                            style={{ marginLeft: "10px" }}
                            onClick={()=>
                                 restockSweet(s.id)
                            }>
                            Restock
                        </button>

                        <button
                            style={{ marginLeft: "10px", color: "red" }}
                            onClick={() => deleteSweet(s.id)}
                        >
                            Delete
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}
