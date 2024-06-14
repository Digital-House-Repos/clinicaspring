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
    Swal.fire({
      icon: 'error',
      title: 'Error...',
      text: data ? data.message : 'Unknown error'
    })
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

  const body = {
    nombre,
    apellido,
    dni,
    fechaIngreso,
    domicilio
  };

  Swal.fire({
    title: "¿Quiere actualizar la información?",
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: "Actualizar",
    denyButtonText: `No actualizar`,
    cancelButtonText: `Cancelar`,

  }).then(async (result) => {
    if (result.isConfirmed) {
      const URLPacientes = `/pacientes/${pacienteID}`;
      const data = await dataPacientes(URLPacientes, null, 'PUT', body);

      if (data.ok) {
        Swal.fire("Actualizado!", "", "success").then(() => {
          window.location.href = '../../routes/pacientes/list.html';
        });

      } else {
        Swal.fire({
          icon: 'error',
          title: 'Error...',
          text: data ? data.message : 'Unknown error'
        })
      }

    } else if (result.isDenied) {
      Swal.fire("La información no se actualizó", "", "info").then(() => {
        window.location.href = '../../routes/pacientes/list.html';
      });
    }
  });
}

window.addEventListener('load', loadDataPaciente);

const formupdate = document.getElementById('form-update-paciente');
formupdate.addEventListener('submit', async (e) => {
  e.preventDefault();
  updatePaciente();
});