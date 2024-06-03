window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de odontologos con el método GET
      //nos devolverá un JSON con una colección de odontologos
      const url = "/odontologos";
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de odontologos del JSON
         for(odontologo of data.data){
            //por cada odontologo armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la odontologo
            const table = document.getElementById("odontologoTable");
            let odontologoRow =table.insertRow();
            let elementoHTML = `<td>`+
            `<button id="btn_id_${odontologo.odontologoID}" type="button" onclick="findBy(${odontologo.odontologoID})" class="btn btn-info btn_id">${odontologo.odontologoID}</button>`+
            `</td>`+
            `<td class="td_numeroMatricula">${odontologo.numeroMatricula}</td>`+
            `<td class="td_nombre">${odontologo.nombre}</td>`+
            `<td class="td_apellido">${odontologo.apellido}</td>`+
            `<td><button id="btn_delete_${odontologo.odontologoID}" type="button" onclick="deleteBy(${odontologo.odontologoID})" class="btn btn-danger btn_delete">×</button></td>`

            odontologoRow.innerHTML = elementoHTML;

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "./get_odontologos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })