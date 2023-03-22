//
//  StepperTableViewCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 14/04/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

class StepperTableViewCell: BaseTVC {
    
    public static let nibName = "StepperTableViewCell"
    public static let identifier = "StepperTableViewCell"
    public static let nib = StepperTableViewCell.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: StepperTableViewCell.nibName, bundle: Bundle(for: StepperTableViewCell.self))
    }
    
    var title = ""{
        didSet{
            self.titleLbl.text = self.title
        }
    }
    
    var value = 0{
        didSet{
            self.inputField.text = self.value.description
           let val = Double(self.value)
            if(val <=  self.maxLimit && val >= self.minLimit){
                self.stepper.value = val
            }
            if(val < self.minLimit){
                self.stepper.value = val
            }
        }
    }
    
    var maxLimit : Double = 1{
        didSet{
            self.stepper.maximumValue = self.maxLimit
        }
    }
    var minLimit : Double = 0
    var isEnabled = true
    var onChnage : ((Int)->Void)?
    
    @IBOutlet weak var titleLbl: UILabel!
    @IBOutlet weak var inputField: UITextField!
    @IBOutlet weak var stepper: UIStepper!
    
    
    @IBAction func stepperAction(_ sender: UIStepper) {
        self.value = Int(sender.value)
        self.onChnage?(self.value)
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        self.titleLbl.text = self.title
        self.inputField.text = self.value.description
        self.stepper.value = Double(self.value)
        self.isUserInteractionEnabled = self.isEnabled
        self.titleLbl.textColor = UIColor.black
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        self.selectedBackgroundView = UIView(frame: CGRect.zero)
        self.selectedBackgroundView?.backgroundColor = self.contentView.backgroundColor
        // Configure the view for the selected state
    }
    
    override func didMoveToSuperview() {
        self.inputField.addTarget(self, action: #selector(didChange(_:)), for: .allEditingEvents)
    }
    
    @objc func didChange(_ textField : UITextField) {
        if let val = Int(textField.text ?? "0"){
            self.value = val
            self.stepper.value = Double(val)
            self.onChnage?(val)
        }else{
            self.value = Int(self.stepper.value)
            self.onChnage?(Int(self.stepper.value))
        }
    }
}
