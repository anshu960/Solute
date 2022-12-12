//
//  SwitchTVC.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 07/09/19.
//

import UIKit

public class SwitchTVC: BaseTVC {
    
    public static let nibName = "SwitchTVC"
    public static let identifier = "SwitchTVC"
    public static let nib = SwitchTVC.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: SwitchTVC.nibName, bundle: Bundle(for: SwitchTVC.self))
    }
    
    @IBOutlet weak var titleLbl: UILabel!
    
    @IBOutlet weak var formSwitch: UISwitch!
    
    
    @IBAction func toggleSwitch(_ sender: UISwitch) {
        self.field.value = sender.isOn
    }
    
    var formField : Field!{
        didSet{
            self.titleLbl.text = self.field.title
            self.formSwitch.isOn = self.field.value as? Bool ?? false
        }
    }
    public override var field: Field!{
        get{
            return self.formField
        }
        set{
            self.formField = newValue
        }
    }
    
    
    override public func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override public func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
}
