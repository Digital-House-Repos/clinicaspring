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

async function loadPacientes() {
  const URLPacientes = '/pacientes';
  const data = await dataPacientes(URLPacientes, null, 'GET', null);

  const divId = document.getElementById('paciente-id');
  const divName = document.getElementById('paciente-nombre');
  const divLastName = document.getElementById('paciente-apellido');
  const divDNI = document.getElementById('paciente-dni');
  const divFechaIngreso = document.getElementById('paciente-fechaingreso');
  const divDomilicio = document.getElementById('paciente-domicilio');
  const divButtons = document.getElementById('paciente-buttons');

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

async function deletePaciente(_id) {
  const URLPacientes = `/pacientes/${_id}`;
  const data = await dataPacientes(URLPacientes, null, 'DELETE', null);
  if (data.ok) {
    window.location.reload();
  }
}

loadPacientes();