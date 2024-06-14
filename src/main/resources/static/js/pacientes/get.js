const URLPacientes = '/pacientes';

async function fetchPacientes(_url, _token = null, _method = 'GET', _body = null) {
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

async function searchPaciente() {
  const busqueda = document.getElementById('nombreapellido').value;
  const data = await fetchPacientes(URLPacientes + `/busqueda/${busqueda}`);
  loadHTML(data);
}

async function loadPacientes() {
  const data = await fetchPacientes(URLPacientes);
  loadHTML(data);
}

async function deletePaciente(id) {
  const data = await fetchPacientes(URLPacientes + `/${id}`, null, 'DELETE', null);
  if (data.ok) {
    window.location.reload();
  } else {
    alert('Error al eliminar paciente: ' + (data ? data.message : 'Unknown error'));
  }
}

const inputSearch = document.getElementById('nombreapellido');
inputSearch.addEventListener('keyup', async (e) => {
  if (inputSearch.value.length == 0) {
    loadPacientes();
  } else {
    searchPaciente();
  }
});

window.onload = async () => {
  loadPacientes();
}

function loadHTML(data) {
  const divId = document.getElementById('paciente-id');
  const divName = document.getElementById('paciente-nombre');
  const divLastName = document.getElementById('paciente-apellido');
  const divDNI = document.getElementById('paciente-dni');
  const divFechaIngreso = document.getElementById('paciente-fechaingreso');
  const divDomilicio = document.getElementById('paciente-domicilio');
  const divButtons = document.getElementById('paciente-buttons');

  divId.innerHTML = `<span class="data-title"><strong>ID</strong></span>`;
  divName.innerHTML = `<span class="data-title"><strong>Nombre</strong></span>`;
  divLastName.innerHTML = `<span class="data-title"><strong>Apellido</strong></span>`;
  divDNI.innerHTML = `<span class="data-title"><strong>DNI</strong></span>`;
  divFechaIngreso.innerHTML = `<span class="data-title"><strong>Fecha de Ingreso</strong></span>`;
  divDomilicio.innerHTML = `<span class="data-title"><strong>Domicilio</strong></span>`;
  divButtons.innerHTML = `<span class="data-title"><strong>Acciones</strong></span>`;

  if (data.ok && data.data != "Empty list") {
    for (paciente of data.data) {
      const { pacienteID, nombre, apellido, dni, fechaIngreso, domicilio } = paciente;
      const spanId = `<span class="data-list">${pacienteID}</span>`;
      const spanName = `<span class="data-list">${nombre}</span>`;
      const spanLastName = `<span class="data-list">${apellido}</span>`;
      const spanDNI = `<span class="data-list">${dni}</span>`;
      const spanFechaIngreso = `<span class="data-list">${fechaIngreso}</span>`;
      const spanDomicilio = `<span class="data-list">
          ${domicilio.calle} ${domicilio.numero} - ${domicilio.localidad} ${domicilio.provincia}</span>`;
      const spanButton = `
          <span class="data-list text-button">
                <a href="./update.html?id=${pacienteID}" class="a-update"><i class="uil uil-edit"></i></a>
                <a href="#" onclick="deletePaciente(${pacienteID})" class="a-delete"><i class="uil uil-trash-alt"></i></a>
          </span>`

      divId.innerHTML += spanId;
      divName.innerHTML += spanName;
      divLastName.innerHTML += spanLastName;
      divDNI.innerHTML += spanDNI;
      divFechaIngreso.innerHTML += spanFechaIngreso;
      divDomilicio.innerHTML += spanDomicilio;
      divButtons.innerHTML += spanButton;
    }
  }
}
