async function dataOdontologos(_url, _token = null, _method = 'GET', _body = null) {

  const options = {
    method: _method,
    headers: { "Content-Type": "application/json" }
  };

  if (_token) { options.headers["Authorization"] = "Bearer " + _token; }
  if (_body) { options.body = JSON.stringify(_body); }

  try {
    const response = await fetch(_url, options);
    const data = await response.json();
    return data;
  } catch (error) {
    console.log(error);
  }
}

async function createOdontologo() {
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const numeroMatricula = document.getElementById('numeroMatricula').value;

  const URLOdontologos = '/odontologos';
  const data = await dataOdontologos(URLOdontologos, null, 'POST', {
    nombre,
    apellido,
    numeroMatricula
  });

  if (data.ok) {
    alert('Odontólogo creado correctamente');
  } else {
    alert('Error al crear odontólogo' + data.message);
  }
}

const formcreate = document.getElementById('form-post-odontologo');
formcreate.addEventListener('submit', async (e) => {
  e.preventDefault();
  createOdontologo();
});