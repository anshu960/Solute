//
//  LoginVC.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 15/03/23.
//

import UtilityKit

class LoginVC: UtilityViewController {

    @IBOutlet weak var mobileField: UITextField!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

    @IBAction func onClickSentOtp(_ sender: UIButton) {
        if let mobile = self.mobileField.text,mobile.count == 10{
            self.startActivity()
            AuthenticationViewModel.shared.onOtpSent={message in
                DispatchQueue.main.async {
                    self.stopActivity()
                    if(message == "sent"){
                        self.performSegue(withIdentifier: "GoToOTP", sender: self)
                    }else{
                        self.alert(with: message)
                    }
                }
            }
            if(mobile.trim() == "9455068676"){
                AuthenticationViewModel.shared.sendOtp(mobile: "+91" + mobile)
            }else{
                AuthenticationViewModel.shared.sendOtp(mobile: "+49" + mobile)
            }
            
        }else{
            alert(with: "Please enter valid mobile number")
        }
    }
}
