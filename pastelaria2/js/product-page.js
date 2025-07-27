import { fetchProducts } from './api.js';

function getQueryParam(key) {
  const params = new URLSearchParams(window.location.search);
  return params.get(key);
}

async function loadProductDetails() {
  const name = getQueryParam('id');
  const detailsEl = document.getElementById('product-details');

  if (!name) {
    detailsEl.textContent = 'Produto não especificado.';
    return;
  }

  const products = await fetchProducts();
  const product = products.find(p => p.name === name);

  if (!product) {
    detailsEl.textContent = 'Produto não encontrado.';
    return;
  }

  detailsEl.innerHTML = `
    <h2>${product.name}</h2>
    <p>${product.description}</p>
    <p><strong>Preço:</strong> €${product.price.toFixed(2)}</p>
  `;
}

loadProductDetails();
