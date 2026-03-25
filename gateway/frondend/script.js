// Gestión de comentarios y productos en localStorage

// ---------- Comentarios (sin email) ----------
function obtenerComentarios() {
    const raw = localStorage.getItem('comentarios');
    return raw ? JSON.parse(raw) : [];
}

function guardarComentarios(list) {
    localStorage.setItem('comentarios', JSON.stringify(list));
}

function cargarComentarios() {
    const comentarios = obtenerComentarios();
    const lista = document.getElementById('listaComentarios');
    if (!lista) return;
    lista.innerHTML = '';

    comentarios.forEach((c, i) => {
        const li = document.createElement('li');
        li.textContent = `${c.text} (${c.date})`;
        lista.appendChild(li);
    });
}

function crearComentario() {
    const input = document.getElementById('comentario');
    const text = input.value && input.value.trim();
    if (!text) { alert('Escribe un comentario'); return; }

    const comentarios = obtenerComentarios();
    comentarios.push({ text, date: new Date().toLocaleString() });
    guardarComentarios(comentarios);

    input.value = '';
    cargarComentarios();
}

// ---------- Productos ----------
function obtenerProductos() {
    const raw = localStorage.getItem('productos');
    return raw ? JSON.parse(raw) : [];
}

function guardarProductos(list) {
    localStorage.setItem('productos', JSON.stringify(list));
}

function renderProductos(list) {
    const lista = document.getElementById('listaProductos');
    if (!lista) return;
    lista.innerHTML = '';

    if (!list.length) {
        const no = document.createElement('li');
        no.textContent = 'No hay productos';
        lista.appendChild(no);
        return;
    }

    list.forEach(p => {
        const li = document.createElement('li');
        li.innerHTML = `<strong>${escapeHtml(p.name)}</strong> - $${Number(p.price).toFixed(2)}<br><em>${escapeHtml(p.description||'')}</em>`;
        lista.appendChild(li);
    });
}

function cargarProductos() {
    const productos = obtenerProductos();
    renderProductos(productos);
}

function crearProducto() {
    const nombreEl = document.getElementById('productoNombre');
    const precioEl = document.getElementById('productoPrecio');
    const descEl = document.getElementById('productoDescripcion');
    const name = nombreEl.value && nombreEl.value.trim();
    const price = parseFloat(precioEl.value);
    const description = descEl ? descEl.value.trim() : '';

    if (!name || Number.isNaN(price)) { alert('Nombre y precio válidos'); return; }

    const productos = obtenerProductos();
    productos.push({ name, price, description });
    guardarProductos(productos);

    nombreEl.value = '';
    precioEl.value = '';
    if (descEl) descEl.value = '';
    cargarProductos();
}

function buscarProducto() {
    const q = document.getElementById('productoBuscarNombre').value;
    if (!q) { alert('Escribe un nombre para buscar'); return; }

    const productos = obtenerProductos();
    const found = productos.filter(p => p.name.toLowerCase().includes(q.toLowerCase()));
    renderProductos(found);
}

function actualizarProducto() {
    const q = document.getElementById('productoBuscarNombre').value && document.getElementById('productoBuscarNombre').value.trim();
    const nuevoPrecio = parseFloat(document.getElementById('productoPrecio').value);
    const nuevaDescripcion = document.getElementById('productoDescripcion') ? document.getElementById('productoDescripcion').value.trim() : null;
    if (!q || Number.isNaN(nuevoPrecio)) { alert('Nombre y nuevo precio válidos'); return; }

    const productos = obtenerProductos();
    let updated = 0;
    productos.forEach(p => {
        if (p.name.toLowerCase() === q.toLowerCase()) { p.price = nuevoPrecio; updated++; }
    });

    // si se pasó una nueva descripción, actualizarla también
    if (nuevaDescripcion !== null && nuevaDescripcion !== '') {
        productos.forEach(p => { if (p.name.toLowerCase() === q.toLowerCase()) p.description = nuevaDescripcion; });
    }

    if (updated === 0) { alert('No se encontró ningún producto con ese nombre exacto'); return; }

    guardarProductos(productos);
    cargarProductos();
    alert(`Actualizados ${updated} producto(s)`);
}

function eliminarProducto() {
    const q = document.getElementById('productoBuscarNombre').value && document.getElementById('productoBuscarNombre').value.trim();
    if (!q) { alert('Escribe el nombre para eliminar'); return; }

    const productos = obtenerProductos();
    const filtrados = productos.filter(p => p.name.toLowerCase() !== q.toLowerCase());
    const removed = productos.length - filtrados.length;

    if (removed === 0) { alert('No se encontró producto con ese nombre'); return; }

    guardarProductos(filtrados);
    cargarProductos();
    alert(`Eliminados ${removed} producto(s)`);
}

// ---------- UI helpers: mostrar/ocultar secciones ----------
function toggleSection(key) {
    const prod = document.getElementById('productosSection');
    const com = document.getElementById('comentariosSection');
    if (key === 'productos') {
        const isHidden = prod.classList.toggle('hidden');
        const btn = document.getElementById('btnShowProducts');
        btn.textContent = isHidden ? 'Mostrar Productos' : 'Ocultar Productos';
        if (!isHidden) cargarProductos();
    }
    if (key === 'comentarios') {
        const isHidden = com.classList.toggle('hidden');
        const btn = document.getElementById('btnShowComentarios');
        btn.textContent = isHidden ? 'Mostrar Comentarios' : 'Ocultar Comentarios';
        if (!isHidden) cargarComentarios();
    }
}

// pequeño escape para evitar XSS en render
function escapeHtml(unsafe) {
    return String(unsafe)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/\'/g, '&#039;');
}

// cargar datos iniciales al abrir la página
document.addEventListener('DOMContentLoaded', () => {
    cargarComentarios();
    cargarProductos();
});