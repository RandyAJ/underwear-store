import { useEffect, useState } from "react";

// Запусти json-server отдельно, например:
// npx json-server --watch db.json --port 3000
// db.json должен содержать { "products": [] }
const API_URL = "http://localhost:8081/api/products";

interface Product {
  id: number;
  name: string;
  price: number;
}

export default function App() {
  const [products, setProducts] = useState<Product[]>([]);
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  // GET — получение списка продуктов
  const fetchProducts = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch(API_URL);
      if (!response.ok) {
        throw new Error("Не удалось получить продукты");
      }
      const data: Product[] = await response.json();
      setProducts(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : "Неизвестная ошибка");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  // POST — добавление нового продукта
  const addProduct = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!name.trim() || !price.trim()) return;

    const newProduct = {
      name: name.trim(),
      price: Number(price),
      quantity: 5,
      sale: 10
    };

    setLoading(true);
    setError(null);
    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newProduct),
      });
      if (!response.ok) {
        throw new Error("Не удалось добавить продукт");
      }
      const created: Product = await response.json();
      setProducts((prev) => [...prev, created]);
      setName("");
      setPrice("");
    } catch (err) {
      setError(err instanceof Error ? err.message : "Неизвестная ошибка");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: "0 auto", fontFamily: "sans-serif" }}>
      <h2>Продукты</h2>

      <form
        onSubmit={addProduct}
        style={{ display: "flex", gap: 8, marginBottom: 16 }}
      >
        <input
          type="text"
          placeholder="Название"
          value={name}
          onChange={(e) => setName(e.target.value)}
          style={{ flex: 1 }}
        />
        <input
          type="number"
          placeholder="Цена"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          style={{ width: 80 }}
        />
        <button type="submit" disabled={loading}>
          Добавить
        </button>
      </form>

      {loading && <p>Загрузка...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      <ul style={{ listStyle: "none", padding: 0 }}>
        {products.map((product) => (
          <li
            key={product.id}
            style={{
              display: "flex",
              justifyContent: "space-between",
              padding: "6px 0",
              borderBottom: "1px solid #eee",
            }}
          >
            <span>{product.name}</span>
            <span>{product.price} ₽</span>
          </li>
        ))}
      </ul>

      <button
        onClick={fetchProducts}
        disabled={loading}
        style={{ marginTop: 8 }}
      >
        Обновить список
      </button>
    </div>
  );
}
