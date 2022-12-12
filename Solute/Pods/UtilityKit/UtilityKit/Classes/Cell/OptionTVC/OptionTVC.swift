//
//  OptionTVC.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 13/09/19.
//

import UIKit

public class OptionTVC: BaseTVC {
    
    public static let nibName = "OptionTVC"
    public static let identifier = "OptionTVC"
    public static let nib = OptionTVC.getNib()
    public static let cellHeight : CGFloat = 150
    class func getNib() -> UINib {
        return UINib(nibName: OptionTVC.nibName, bundle: Bundle(for: OptionTVC.self))
    }
    
    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var titleLbl: UILabel!
    @IBOutlet weak var valueLbl: UILabel!
    
//    public var viewController : UIViewController?
//    
//    public override var controlelr: UIViewController?{
//        get{
//            return self.viewController
//        }
//        set{
//            self.viewController = newValue
//        }
//    }
    
    
    var formField : Field!{
        didSet{
            self.titleLbl.text = self.field.title
            self.valueLbl.text = self.field.placeholder
            if(self.field.value as? String ?? "" != ""){
                self.valueLbl.textColor = UIColor.black
            }else if let key = field.valueKey,let dict = self.field.value as? [String:Any],let val = dict[key] as? String{
                self.valueLbl.text = val
                self.valueLbl.textColor = UIColor.black
            }else{
                self.valueLbl.text = field.placeholder
                self.valueLbl.textColor = UIColor.lightGray
            }
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
