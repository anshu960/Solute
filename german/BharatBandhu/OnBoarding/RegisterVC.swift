//
//  RegisterVC.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 15/03/23.
//

import UtilityKit

class RegisterVC: UtilityViewController {

    @IBOutlet weak var nameField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
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

    @IBAction func onClickRegister(_ sender: UIButton) {
        if let name = self.nameField.text,name.count >= 3{
            AuthenticationViewModel.shared.registerUser(name: name)
        }
    }
}
