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

  if (data.ok && data.data != 'Empty list') {
    document.getElementById('nombre').value = data.data.nombre;
    document.getElementById('apellido').value = data.data.apellido;
    document.getElementById('numeroMatricula').value = data.data.numeroMatricula;
  } else {
    Swal.fire({
      icon: 'error',
      title: 'Error...',
      text: data ? data.message : 'Unknown error'
    });
  }
}

async function updateOdontologo() {
  const odontologoID = window.location.search.split('=')[1];
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const numeroMatricula = document.getElementById('numeroMatricula').value;

  const body = {
    nombre,
    apellido,
    numeroMatricula
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
      const URLOdontologos = `/odontologos/${odontologoID}`;
      const data = await dataOdontologos(URLOdontologos, null, 'PUT', body);

      if (data.ok) {
        Swal.fire("Actualizado!", "", "success").then(() => {
          window.location.href = '../../routes/odontologos/list.html';
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
        window.location.href = '../../routes/odontologos/list.html';
      });
    }
  });
}

window.addEventListener('load', loadDataOdontologo);

const formupdate = document.getElementById('form-update-odontologo');
formupdate.addEventListener('submit', async (e) => {
  e.preventDefault();
  updateOdontologo();
});