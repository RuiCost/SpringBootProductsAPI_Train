// js/main.js
import { fetchProducts } from './api.js';

async function displayProducts() {
  const ul = document.getElementById('productList');
  ul.innerHTML = '<li>A carregar produtos...</li>';

  const products = await fetchProducts();

  if (products.length === 0) {
    ul.innerHTML = '<li>Nenhum produto encontrado.</li>';
    return;
  }

  ul.innerHTML = '';

  products.forEach(product => {
    const li = document.createElement('li');

    // Criar elementos com DOM manualmente
    const div = document.createElement('div');
    div.classList.add('product-item');

    const content = document.createElement('div');
    
    content.innerHTML = `
      <a href="product.html?id=${encodeURIComponent(product.name)}"><strong>${product.name}</strong></a><br>
      ${product.description}<br>
      <span class="price">€${product.price.toFixed(2)}</span>
    `;

    const button = document.createElement('button');
    button.textContent = '🗑️';
    button.title = 'Eliminar';
    button.addEventListener('click', () => {
      deleteProduct(product.name, product.id); // exemplo
    });

    div.appendChild(content);
    div.appendChild(button);
    li.appendChild(div);
    ul.appendChild(li);
  });
}

// Exemplo de função para apagar produto (a implementar)
async function deleteProduct(name,id) {
  if (!confirm(`Tens a certeza que queres eliminar o produto "${name}"?`)) return;

  try {
    const res = await fetch(`http://localhost:8080/product/${id}`, {
      method: 'DELETE'
    });

    if (res.ok) {
      alert('Produto eliminado com sucesso.');
      displayProducts(); // refrescar a lista
    } else {
      alert('Erro ao eliminar produto.');
    }
  } catch (err) {
    console.error(err);
    alert('Erro ao comunicar com o servidor.');
  }
}

window.addEventListener('DOMContentLoaded', () => {
  displayProducts();
  setInterval(displayProducts, 10000);
});
