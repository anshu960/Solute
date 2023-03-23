//
//  AccountVC.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 18/03/23.
//

import UIKit

class AccountVC: UIViewController {

    @IBOutlet weak var nameLbl: UILabel!
    
    @IBOutlet weak var mobileLbl: UILabel!
    
    @IBOutlet weak var barcodeImage: UIImageView!
    
    @IBOutlet weak var barcodeLbl: UILabel!
    
    @IBOutlet weak var logoutBtn: UIButton!
    
    
    @IBAction func onClockLogout(_ sender: UIButton) {
        Preferences.shared.remove(key: Key.userCredentials)
        if let vc = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "OnBoardingNavigationVC") as? UINavigationController{
            ToastService.shared.window?.rootViewController = vc
        }
    }
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let cred = Preferences.shared.dictionary(forKey: Key.userCredentials)
        if let name = cred[Key.name] as? String{
            nameLbl.text = name
        }
        if let mobile = cred[Key.mobileNumber] as? String{
            mobileLbl.text = mobile
        }
        if let img = CustomerViewModel.shared.barcode{
            self.barcodeImage.isHidden = false
            self.barcodeImage.image = img
            self.barcodeLbl.text = CustomerViewModel.shared.membershipId
        }else{
            CustomerViewModel.shared.loadMembership()
            self.barcodeImage.isHidden = true
            self.barcodeLbl.text = "Membership Details not found, Please wait for sometime or visit our nearest store in your city to get membership"
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

}
