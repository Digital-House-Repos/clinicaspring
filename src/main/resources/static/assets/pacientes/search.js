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

async function searchPaciente() {
  const searchDni = document.getElementById('dni').value;

  const URLPacientes = `/pacientes/dni/${searchDni}`;
  const data = await dataPacientes(URLPacientes, null, 'GET', null);

  const divId = document.getElementById('paciente-id');
  const divName = document.getElementById('paciente-nombre');
  const divLastName = document.getElementById('paciente-apellido');
  const divDni = document.getElementById('paciente-dni');
  const divFechaIngreso = document.getElementById('paciente-fechaingreso');
  const divDomicilio = document.getElementById('paciente-domicilio');


  divId.innerHTML = '';
  divName.innerHTML = '';
  divLastName.innerHTML = '';
  divDni.innerHTML = '';
  divFechaIngreso.innerHTML = '';
  divDomicilio.innerHTML = '';

  const { pacienteID, nombre, apellido, dni, fechaIngreso, domicilio } = data.data;

  const spanId = `<span class="data-list">${pacienteID}</span>`;
  const spanName = `<span class="data-list">${nombre}</span>`;
  const spanLastName = `<span class="data-list">${apellido}</span>`;
  const spanDNI = `<span class="data-list">${dni}</span>`;
  const spanFechaIngreso = `<span class="data-list">${fechaIngreso}</span>`;
  const spanDomicilio = `<span class="data-list">
        ${domicilio.calle} ${domicilio.numero} - ${domicilio.localidad} ${domicilio.provincia}</span>`;

  divId.innerHTML += spanId;
  divName.innerHTML += spanName;
  divLastName.innerHTML += spanLastName;
  divDni.innerHTML += spanDNI;
  divFechaIngreso.innerHTML += spanFechaIngreso;
  divDomicilio.innerHTML += spanDomicilio;
}

const formsearch = document.getElementById('form-search-paciente');
formsearch.addEventListener('submit', async (e) => {
  e.preventDefault();
  searchPaciente();
});