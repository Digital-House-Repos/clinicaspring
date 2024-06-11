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
  const busqueda = document.getElementById('nombreapellido').value;

  const URLOdontologos = `/odontologos/busqueda/${busqueda}`;
  const data = await dataOdontologos(URLOdontologos, null, 'GET', null);

  const divId = document.getElementById('odontologo-id');
  const divName = document.getElementById('odontologo-nombre');
  const divLastName = document.getElementById('odontologo-apellido');
  const divMatricula = document.getElementById('odontologo-matricula');
  const divButtons = document.getElementById('odontologo-buttons');

  divId.innerHTML = '';
  divName.innerHTML = '';
  divLastName.innerHTML = '';
  divMatricula.innerHTML = '';
  divButtons.innerHTML = '';

  divId.innerHTML += `<span class="data-list title"><strong>ID</strong></span>`;
  divName.innerHTML += `<span class="data-list title"><strong>Nombre</strong></span>`;
  divLastName.innerHTML += `<span class="data-list title"><strong>Apellido</strong></span>`;
  divMatricula.innerHTML += `<span class="data-list title"><strong>Matricula</strong></span>`;
  divButtons.innerHTML += `<span class="data-list title"><strong>Acciones</strong></span>`;

  if (data.data != "Empty list") {
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
}

const inputSearch = document.getElementById('nombreapellido');
inputSearch.addEventListener('keyup', async (e) => {
  searchOdontologo();
});