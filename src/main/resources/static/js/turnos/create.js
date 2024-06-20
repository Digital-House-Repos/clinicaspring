async function dataTurno(_url, _token = null, _method = 'GET', _body = null) {

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

async function loadPacientes() {
  const URLPacientes = '/pacientes';
  const data = await dataTurno(URLPacientes, null, 'GET', null);

  const selectPaciente = document.getElementById('paciente');
  if (data.ok && data.data != 'Empty list') {
    for (paciente of data.data) {
      const { pacienteID, nombre, apellido } = paciente;
      const option = `<option value="${pacienteID}">${nombre} ${apellido}</option>`;
      selectPaciente.innerHTML += option;
    }
  } else {
    return false;
  }
}

async function loadOdontologos() {
  const URLOdontologo = '/odontologos';
  const data = await dataTurno(URLOdontologo, null, 'GET', null);

  const selectOdontologo = document.getElementById('odontologo');
  if (data.ok && data.data != "Empty list") {
    for (odontologo of data.data) {
      const { odontologoID, nombre, apellido } = odontologo;
      const option = `<option value="${odontologoID}">${nombre} ${apellido}</option>`;
      selectOdontologo.innerHTML += option;
    }
  } else {
    return false;
  }
}

window.addEventListener('load', async () => {
  comprobar = await loadPacientes();
  comprobar2 = await loadOdontologos();
});

async function createTurno() {
  const idPaciente = document.getElementById('paciente').value;
  const idOdontologo = document.getElementById('odontologo').value;
  const fechaHora = document.getElementById('fechaHora').value;

  const body = {
    odontologo: { odontologoID: idOdontologo },
    paciente: { pacienteID: idPaciente },
    fechaHora: fechaHora
  };

  const URLTurnos = '/turnos';
  const data = await dataTurno(URLTurnos, null, 'POST', body);

  if (data.ok) {
    Swal.fire({
      icon: 'success',
      title: '¡Operación exitosa!',
      text: 'Turno creado exitosamente'
    }).then((result) => {
      window.location.href = '../../routes/turnos/list.html';
    });
  } else {
    Swal.fire({
      icon: 'error',
      title: 'Error...',
      text: data ? data.message : 'Unknown error'
    });
  }
}

const formcreate = document.getElementById('form-post-turno');
formcreate.addEventListener('submit', async (e) => {
  e.preventDefault();
  createTurno();
});