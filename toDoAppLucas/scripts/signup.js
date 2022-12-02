window.addEventListener('load', function() {
    /* ---------------------- obtenemos variables globales ---------------------- */
    const URI_BASE = 'http://todo-api.ctd.academy:3000/v1';




    /* -------------------------------------------------------------------------- */
    /*            FUNCIÓN 1: Escuchamos el submit y preparamos el envío           */
    /* -------------------------------------------------------------------------- */
    const form = document.querySelector('form');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        let usuario = {
            firstName: document.querySelector('#inputNombre').value,
            lastName: document.querySelector('#inputApellido').value,
            email: document.querySelector('#inputEmail').value,
            password: document.querySelector('#inputPassword').value,
            
        }
        console.log(usuario);
        let password2 = document.querySelector('#inputPasswordRepetida').value;
        
        // Validar todos los datos antes de llamar a la API
        if(validarEmail(usuario.email) && validarTexto(usuario.firstName) && validarTexto(usuario.lastName) && validarContrasenia(usuario.password) && compararContrasenias(usuario.password, password2)) {
        
            //mostrarMensaje("Registro Exitoso")
            realizarRegister(usuario);
            setTimeout(() => {
                location.href = "http://127.0.0.1:5500/index.html"
            }, 2000)
            } 
            else {
                mostrarMensajeError("Debe correegir sus datos");
            }
        // redirigir a login en caso de éxito
    });

    /* -------------------------------------------------------------------------- */
    /*                    FUNCIÓN 2: Realizar el signup [POST]                    */
    /* -------------------------------------------------------------------------- */
    function realizarRegister(usuario) {
        const configuracion = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuario)
        };

        fetch(`${URI_BASE}/users`, configuracion)
            .then(respuesta => respuesta.json())
            .then(datos => console.log(datos));

    };


});