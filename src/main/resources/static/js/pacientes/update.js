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
async function loadDataPaciente() {
  const pacienteID = window.location.search.split('=')[1];
  const URLPacientes = `/pacientes/${pacienteID}`;
  const data = await dataPacientes(URLPacientes);

  if (data.ok && data.data != 'Empty list') {
    document.getElementById('nombre').value = data.data.nombre;
    document.getElementById('apellido').value = data.data.apellido;
    document.getElementById('dni').value = data.data.dni;
    document.getElementById('ingreso').value = data.data.fechaIngreso;
    document.getElementById('calle').value = data.data.domicilio.calle;
    document.getElementById('numero').value = data.data.domicilio.numero;
    document.getElementById('localidad').value = data.data.domicilio.localidad;
    document.getElementById('provincia').value = data.data.domicilio.provincia;
  } else {
    alert('Error al cargar los datos del paciente');
  }
}

async function updatePaciente() {
  const pacienteID = window.location.search.split('=')[1];
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

  const URLPacientes = `/pacientes/${pacienteID}`;
  const data = await dataPacientes(URLPacientes, null, 'PUT', {
    nombre,
    apellido,
    dni,
    fechaIngreso,
    domicilio
  });

  if (data.ok) {
    alert('Paciente actualizado correctamente');
    window.location.href = '../../routes/pacientes/list.html';
  } else {
    alert('Error al actualizar el paciente: ' + (data ? data.message : 'Unknown error'));
  }
}

window.addEventListener('load', loadDataPaciente);

const formupdate = document.getElementById('form-update-paciente');
formupdate.addEventListener('submit', async (e) => {
  e.preventDefault();
  updatePaciente();
});