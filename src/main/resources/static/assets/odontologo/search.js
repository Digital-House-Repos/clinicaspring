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

async function searchOdontologo() {
  const matricula = document.getElementById('numeroMatricula').value;

  const URLOdontologos = `/odontologos/matricula/${matricula}`;
  const data = await dataOdontologos(URLOdontologos, null, 'GET', null);

  const divId = document.getElementById('odontologo-id');
  const divName = document.getElementById('odontologo-nombre');
  const divLastName = document.getElementById('odontologo-apellido');
  const divMatricula = document.getElementById('odontologo-matricula');

  divId.innerHTML = '';
  divName.innerHTML = '';
  divLastName.innerHTML = '';
  divMatricula.innerHTML = '';

  const { odontologoID, nombre, apellido, numeroMatricula } = data.data;
  
  const spanId = `<span class="data-title"><strong>ID</strong></span><span class="data-list">${odontologoID}</span>`;
  const spanName = `<span class="data-title"><strong>Nombre</strong></span><span class="data-list">${nombre}</span>`;
  const spanLastName = `<span class="data-title"><strong>Apellido</strong></span><span class="data-list">${apellido}</span>`;
  const spanMatricula = `<span class="data-title"><strong>Matricula</strong></span><span class="data-list">${numeroMatricula}</span>`;

  divId.innerHTML += spanId;
  divName.innerHTML += spanName;
  divLastName.innerHTML += spanLastName;
  divMatricula.innerHTML += spanMatricula;
}

const formsearch = document.getElementById('form-search-odontologo');
formsearch.addEventListener('submit', async (e) => {
  e.preventDefault();
  searchOdontologo();
});