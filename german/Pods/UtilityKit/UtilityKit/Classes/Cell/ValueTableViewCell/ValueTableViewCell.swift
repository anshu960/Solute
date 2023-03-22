//
//  TwoLabelTableViewCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 13/01/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

public class ValueTableViewCell: BaseTVC {
    
    public static let nibName = "ValueTableViewCell"
    public static let identifier = "ValueTableViewCell"
    public static let nib = ValueTableViewCell.getNib()
    public static let cellHeight : CGFloat = 150
    class func getNib() -> UINib {
        return UINib(nibName: ValueTableViewCell.nibName, bundle: Bundle(for: ValueTableViewCell.self))
    }
    
    
    @IBOutlet var titleLabel: UILabel!
    
    @IBOutlet var valueField: UITextField!
    
    var isEditable : Bool{
        get{
           return self.valueField.isEnabled
        }set{
           self.valueField.isEnabled = newValue
        }
    }
    
    var title:String = "" {
        didSet{
            self.titleLabel.text = self.title
        }
    }
    
    var value : Any?{
        get{
            return valueField.text as Any
        }set{
            if newValue != nil{
                self.valueField.text = newValue as? String ?? ""
            }else{
                self.valueField.text = nil
            }
            
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
