/* ---------------------------------- texto --------------------------------- */
function validarTexto(texto) {
    let textoNormalizado = normalizarTexto(texto);
    let regexText = new RegExp(/[a-zA-Z\t\h]+|(^$)/);
    let textoValido = regexText.test(textoNormalizado);
        if(!textoValido){
            mostrarMensajeError("Datos de texto incorrectos")
        } else {
            return textoValido;
        }
}

function normalizarTexto(texto) {
    return texto.trim();
}

/* ---------------------------------- email --------------------------------- */
function validarEmail(email) {
    let mailNormalizado = normalizarEmail(email);
    let emailRegEx = new RegExp(/^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i)
    let emailValidado = emailRegEx.test(mailNormalizado);
        if(!emailValidado){
        mostrarMensajeError("email incorrecto");
    }else{
        return emailValidado;
    }
}

function normalizarEmail(email) {
    return email.toLowerCase();
}

/* -------------------------------- password -------------------------------- */
function validarContrasenia(contrasenia) {
    
    let passValida = contrasenia.length >= 8;
    
    if(!passValida){
        mostrarMensajeError("La contrasenia es invalida");
    } else{
        return passValida;
    }
}


function compararContrasenias(contrasenia_1, contrasenia_2) {
    let passIguales = contrasenia_1 === contrasenia_2;
    
    if(!passIguales){
        mostrarMensajeError("Las contrasenias no coinciden")
    }else{
        return passIguales;

    }
}

function mostrarMensajeError(mensaje) {
    alert(mensaje)
    }
    
    function mostrarMensaje(mensaje) {
    let mensajeExito =document.querySelector('#mensaje-exito')
    mensajeExito.innerHTML = `<h3>${mensaje}</h3>`
    
    }