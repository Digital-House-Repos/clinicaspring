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

async function loadOdontologos() {
  const URLOdontologos = '/odontologos';
  const data = await dataOdontologos(URLOdontologos, null, 'GET', null);

  const divId = document.getElementById('odontologo-id');
  const divName = document.getElementById('odontologo-nombre');
  const divLastName = document.getElementById('odontologo-apellido');
  const divMatricula = document.getElementById('odontologo-matricula');
  const divButtons = document.getElementById('odontologo-buttons');

  for (odontologo of data.data) {
    const { odontologoID, nombre, apellido, numeroMatricula } = odontologo;
    const spanId = `<span class="data-list">${odontologoID}</span>`;
    const spanName = `<span class="data-list">${nombre}</span>`;
    const spanLastName = `<span class="data-list">${apellido}</span>`;
    const spanMatricula = `<span class="data-list">${numeroMatricula}</span>`;
    const spanButton = `
    <span class="data-list text-button">
          <a href="./update.html?id=${odontologoID}" class="a-update"><i class="uil uil-edit"></i></a>
          <a href="#" onclick="deleteOdontologo(${odontologoID})" class="a-delete"><i class="uil uil-trash-alt"></i></a>
    </span>`

    divId.innerHTML += spanId;
    divName.innerHTML += spanName;
    divLastName.innerHTML += spanLastName;
    divMatricula.innerHTML += spanMatricula;
    divButtons.innerHTML += spanButton;
  }
}

async function deleteOdontologo(_id) {
  const URLOdontologos = `/odontologos/${_id}`;
  const data = await dataOdontologos(URLOdontologos, null, 'DELETE', null);
  if (data.ok) {
    window.location.reload();
  }
}

loadOdontologos();