// js/api.js

export async function fetchProducts() {
  try {
    const response = await fetch('http://localhost:8080/product');
    if (!response.ok) {
      throw new Error('Erro ao obter os produtos');
    }
    const products = await response.json();
    return products;
  } catch (error) {
    console.error(error);
    return [];
  }
}

export async function addProduct(product) {
  try {
    const response = await fetch('http://localhost:8080/product', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(product)
    });
    if (!response.ok) {
      throw new Error('Erro ao adicionar produto');
    }
    return true;
  } catch (error) {
    console.error(error);
    return false;
  }
}
