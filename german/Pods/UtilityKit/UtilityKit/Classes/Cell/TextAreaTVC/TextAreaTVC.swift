//
//  TextAreaTableViewCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 12/01/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

public class TextAreaTVC: BaseTVC {

    public static let nibName = "TextAreaTVC"
    public static let identifier = "TextAreaTVC"
    public static let nib = TextAreaTVC.getNib()
    public static let cellHeight : CGFloat = 150
    class func getNib() -> UINib {
        return UINib(nibName: TextAreaTVC.nibName, bundle: Bundle(for: TextAreaTVC.self))
    }
    
    
    @IBOutlet weak var textArea: UITextView!
    
    var onChnage : ((String)->Void)?
    
    var placeholder = "Enter Text Here"{
        didSet{
            self.textArea.text = self.placeholder
            self.textArea.textColor = UIColor.gray
        }
    }
    
    var value : String = ""{
        didSet{
            if(value != ""){
             self.textArea.text = self.value
            self.textArea.textColor = UIColor.black
            }else{
              self.textArea.text = placeholder
                self.textArea.textColor = UIColor.gray
            }
        }
    }
    
    var isEditable : Bool{
        get{
            return self.textArea.isEditable
        }set{
            self.textArea.isEditable = newValue
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
        self.textArea.delegate = self
    }

}
extension TextAreaTVC:UITextViewDelegate{
    public func textViewDidBeginEditing(_ textView: UITextView) {
        if textView.text.isEmpty{
            self.textArea.text = ""
            self.textArea.textColor = UIColor.gray
        }else if let text = textView.text{
            if(text == placeholder){
                self.textArea.text = ""
                self.textArea.textColor = UIColor.black
            }
        }
    }
    public func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text.isEmpty{
            self.textArea.text = self.placeholder
            self.textArea.textColor = UIColor.gray
        }else{

        }
    }
    public func textViewDidChange(_ textView: UITextView) {
        
        if(self.textArea.text ?? "" != placeholder){
            self.value = textArea.text ?? ""
            self.onChnage?(self.value)
        }
    }
}
