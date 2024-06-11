async function getQuantity(_url, _token = null, _method = 'GET', _body = null) {
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

async function loadQuantity(url, element) {
  const data = await getQuantity(url);
  element.textContent = data.data ? data.data : 0;
}

const turnos = document.getElementById('total-turno');
const odontologos = document.getElementById('total-odontologo');
const pacientes = document.getElementById('total-paciente');

loadQuantity('/turnos/cantidad', turnos);
loadQuantity('/odontologos/cantidad', odontologos);
loadQuantity('/pacientes/cantidad', pacientes);
