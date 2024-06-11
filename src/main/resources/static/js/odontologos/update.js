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
async function loadDataOdontologo() {
  const odontologoID = window.location.search.split('=')[1];
  const URLOdontologos = `/odontologos/${odontologoID}`;
  const data = await dataOdontologos(URLOdontologos);

  console.log(data);

  if (data.ok) {
    document.getElementById('nombre').value = data.data.nombre;
    document.getElementById('apellido').value = data.data.apellido;
    document.getElementById('numeroMatricula').value = data.data.numeroMatricula;
  } else {
    alert('Error al cargar los datos del odontólogo');
  }
}

window.addEventListener('load', loadDataOdontologo);


async function updateOdontologo() {
  const odontologoID = window.location.search.split('=')[1];
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const numeroMatricula = document.getElementById('numeroMatricula').value;

  const URLOdontologos = `/odontologos/${odontologoID}`;
  const data = await dataOdontologos(URLOdontologos, null, 'PUT', {
    nombre,
    apellido,
    numeroMatricula
  });

  if (data && data.ok) {
    alert('Odontólogo actualizado correctamente');
  } else {
    alert('Error al actualizar el odontólogo: ' + (data ? data.message : 'Unknown error'));
  }
}

const formupdate = document.getElementById('form-update-odontologo');
formupdate.addEventListener('submit', async (e) => {
  e.preventDefault();
  updateOdontologo();
});