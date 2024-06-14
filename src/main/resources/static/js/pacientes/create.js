async function dataPacientes(_url, _token = null, _method = 'GET', _body = null) {

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

async function createPaciente() {
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const dni = document.getElementById('dni').value;
  const fechaIngreso = document.getElementById('ingreso').value;
  const domicilio = {
    calle: document.getElementById('calle').value,
    numero: document.getElementById('numero').value,
    localidad: document.getElementById('localidad').value,
    provincia: document.getElementById('provincia').value
  };

  const body = {
    nombre,
    apellido,
    dni,
    fechaIngreso,
    domicilio
  };

  const URLPacientes = '/pacientes';
  const data = await dataPacientes(URLPacientes, null, 'POST', body);

  if (data.ok) {
    alert('Paciente creado correctamente');
    window.location.href = '../../routes/pacientes/list.html';
  } else {
    alert('Error al crear paciente' + (data ? data.message : 'Unknown error'));
  }
}

const formcreate = document.getElementById('form-post-paciente');
formcreate.addEventListener('submit', async (e) => {
  e.preventDefault();
  createPaciente();
});