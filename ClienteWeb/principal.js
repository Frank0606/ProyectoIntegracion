import { URL_WS } from "./constantes.js";

function getCookie(name) {
    const cookieValue = document.cookie.match('(^|[^;]+)\\s*' + name + '\\s*=\\s*([^;]+)');
    return cookieValue ? cookieValue.pop() : null;
}

document.addEventListener('DOMContentLoaded', () => {

    const numeroGuia = getCookie('noG')

    if (numeroGuia !== null && numeroGuia !== '0' && numeroGuia !== undefined && numeroGuia !== '') {

        document.getElementById('labelNumeroGuia').textContent = `Guia: ${numeroGuia}`
        buscarNumeroGuia(numeroGuia).then(envioJson => {

            const envio = envioJson[0]

            document.getElementById('labelOrigen').textContent = envio.origen
            document.getElementById('labelDestino').textContent = `Calle ${envio.calle} #${envio.numeroCasa}. ${envio.colonia}. ${envio.cp}. ${envio.ciudad}. ${envio.estado}`
            
        })

    } else {
        window.location.href = 'index.html'
    }
})

async function buscarNumeroGuia(numeroGuia) {
    try {
        const response = await fetch(`${URL_WS}envio/numero-guia/${numeroGuia}`, {
            method: 'GET'
        });
        const data = await response.json();

        if (data.length > 0 && data[0].idEnvio !== -1) {
            return data;
        } else {
            return null;
        }
    } catch (error) {
        return null;
    }
}

async function buscarPaquetesEnvio(numeroGuia) {
    try {
        const response = await fetch(`${URL_WS}envio/numero-guia/${numeroGuia}`, {
            method: 'GET'
        });
        const data = await response.json();

        if (data.length > 0 && data[0].idEnvio !== -1) {
            return data;
        } else {
            return null;
        }
    } catch (error) {
        return null;
    }
}