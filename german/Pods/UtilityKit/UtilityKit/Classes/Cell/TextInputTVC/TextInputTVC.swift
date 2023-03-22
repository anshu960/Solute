//
//  TextInputTableViewCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 14/04/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

public class TextInputTVC: BaseTVC {
    
    public static let nibName = "TextInputTVC"
    public static let identifier = "TextInputTVC"
    public static let nib = TextInputTVC.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: TextInputTVC.nibName, bundle: Bundle(for: TextInputTVC.self))
    }
    
    
    var isEditable = true
    var placeholder = "Enter Text Here"{
        didSet{
            self.inputField.placeholder = self.placeholder
        }
    }
    var value = ""{
        didSet{
            self.inputField.placeholder = self.formField.placeholder
            self.inputField.text = self.formField.value as? String ?? ""
        }
    }
    
    
    @IBOutlet weak var titleLbl: UILabel!
    @IBOutlet weak var inputField: UITextField!
    
    
    var formField : Field!{
        didSet{
            self.titleLbl.text = self.field.title
            self.inputField.placeholder = self.field.placeholder
            self.inputField.text = self.field.value as? String ?? ""
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
    override public func didMoveToSuperview() {
        self.inputField.addTarget(self, action: #selector(didChange(_:)), for: .allEditingEvents)
    }
    
    @objc func didChange(_ textField : UITextField) {
        self.value = textField.text ?? ""
        self.field.value = self.value
        super.onChange?(self.field)
    }
}
