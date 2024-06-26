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

async function loadTurnos() {
  const URLTurnos = '/turnos';
  const data = await dataTurno(URLTurnos, null, 'GET', null);

  const divId = document.getElementById('turno-id');
  const divPaciente = document.getElementById('turno-paciente');
  const divFechaHora = document.getElementById('turno-fechaHora');
  const divOdontologo = document.getElementById('turno-odontologo');
  const divButtons = document.getElementById('turno-buttons');

  if (data.ok && data.data != 'Empty list') {
    for (turno of data.data) {
      const { turnoID, paciente, odontologo, fechaHora } = turno;
      const spanId = `<span class="data-list">${turnoID}</span>`;
      const spanPaciente = `<span class="data-list">${paciente.apellido} - ${paciente.dni}</span>`;
      const spanFechaHora = `<span class="data-list">${fechaHora}</span>`;
      const spanOdontologo = `<span class="data-list">${odontologo.apellido} - ${odontologo.numeroMatricula}</span>`;
      const spanButton = `
          <span class="data-list text-button">
                <a href="./update.html?id=${turnoID}" class="a-update"><i class="uil uil-edit"></i></a>
                <a href="#" onclick="deleteTurno(${turnoID})" class="a-delete"><i class="uil uil-trash-alt"></i></a>
          </span>`

      divId.innerHTML += spanId;
      divPaciente.innerHTML += spanPaciente;
      divFechaHora.innerHTML += spanFechaHora;
      divOdontologo.innerHTML += spanOdontologo;
      divButtons.innerHTML += spanButton;
    }
  }
}

async function deleteTurno(_id) {
  Swal.fire({
    title: "¿Está seguro?",
    text: "No podrá recuperar este registro.",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Sí, elimínalo!",

  }).then(async (result) => {
    if (result.isConfirmed) {

      const URLTurnos = `/turnos/${_id}`;
      const data = await dataTurno(URLTurnos, null, 'DELETE', null);

      if (data.ok) {
        Swal.fire({
          title: "Eliminado!",
          text: "El registro ha sido eliminado.",
          icon: "success"
        }).then((result) => {
          window.location.reload();
        });

      } else {
        Swal.fire({
          icon: 'error',
          title: 'Error...',
          text: data ? data.message : 'Unknown error'
        });
      }
    }
  });
}

window.onload = async () => {
  loadTurnos();
}