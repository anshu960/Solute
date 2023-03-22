//
//  OTPVC.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 15/03/23.
//

import UtilityKit

class OTPVC: UtilityViewController {

    @IBOutlet weak var otpField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        AuthenticationHandler.shared.onStateChange={msg in
            DispatchQueue.main.async {
                if(msg == "No Account"){
                    self.performSegue(withIdentifier: "GoToRegister", sender: self)
                }else if(msg == ""){
                    let vc = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "MainTab")
                    UIApplication.shared.keyWindow?.rootViewController = vc
                }else{
                    self.alert(with: msg)
                }
            }
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    @IBAction func onClickVerify(_ sender: UIButton) {
        if let otp = otpField.text,otp.count == 6{
            self.startActivity()
            AuthenticationViewModel.shared.onOtpVerify={err in
                DispatchQueue.main.async {
                    if(err.isEmpty){
//                        self.performSegue(withIdentifier: "GoToOTP", sender: self)
                    }else{
                        self.stopActivity()
                        self.alert(with: err)
                    }
                }
            }
            AuthenticationViewModel.shared.verifyOtp(otp: otp)
        }else{
            alert(with: "Please enter valid OTP")
        }
    }
    
}
