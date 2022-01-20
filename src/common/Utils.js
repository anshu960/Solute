const getUniqueId = () => {
    var navigator_info = window.navigator;
    var screen_info = window.screen;
    var uid = navigator_info.mimeTypes.length;
    uid += navigator_info.userAgent.replace(/\D+/g, '');
    uid += navigator_info.plugins.length;
    uid += screen_info.height || '';
    uid += screen_info.width || '';
    uid += screen_info.pixelDepth || '';
    console.log(uid);
    return uid
}

const getParameterByName = (...name) => {
    const paramObj = {};
    name.map((key) => {
        const match = RegExp(`[?&]${key}=([^&]*)`).exec(window.location.hash);
        paramObj[key] = match && decodeURIComponent(match[1].replace(/\+/g, ' '));
    });
    return paramObj;
}

export{
    getUniqueId,
    getParameterByName,
}