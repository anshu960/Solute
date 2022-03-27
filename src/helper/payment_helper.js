export const loadScript=(src) =>{
    return new Promise((resolve) => {
        const script = document.createElement("script");
        script.src = src;
        script.onload = () => {
            resolve(true);
        };
        script.onerror = () => {
            resolve(false);
        };
        document.body.appendChild(script);
    });
}

export const loadRazorpay= ()=>{
         loadScript(
            "https://checkout.razorpay.com/v1/checkout.js"
        ).then((res)=>{
            window.razorpayScriptLoadResult = res;
        }).catch((error)=>{
            window.razorpayScriptLoadError = error;
        })
        
}