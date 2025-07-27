import { addProduct } from './api.js';

const form = document.getElementById('productForm');
const messageEl = document.getElementById('message');

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  messageEl.textContent = '';

  const product = {
    name: form.name.value.trim(),
    description: form.description.value.trim(),
    price: parseFloat(form.price.value)
  };

  const success = await addProduct(product);

  if (success) {
    messageEl.style.color = 'green';
    messageEl.textContent = 'Produto adicionado com sucesso!';
    form.reset();
  } else {
    messageEl.style.color = 'red';
    messageEl.textContent = 'Erro ao adicionar produto.';
  }
});
