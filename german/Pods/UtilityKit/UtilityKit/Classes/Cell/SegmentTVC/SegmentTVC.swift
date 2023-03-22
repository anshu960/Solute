//
//  SegmentTVC.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 08/09/19.
//

import UIKit

class SegmentTVC: BaseTVC {
    
    public static let nibName = "SegmentTVC"
    public static let identifier = "SegmentTVC"
    public static let nib = SegmentTVC.getNib()
    public static let cellHeight : CGFloat = 150
    class func getNib() -> UINib {
        return UINib(nibName: SegmentTVC.nibName, bundle: Bundle(for: SegmentTVC.self))
    }
    
    
    @IBOutlet weak var titlelbl: UILabel!
    
    @IBOutlet weak var segmentControl: UISegmentedControl!
    
    @IBAction func onChangeSegmentControl(_ sender: UISegmentedControl) {
       self.field.value = self.options[sender.selectedSegmentIndex]
    }
    
    var options : [String] = []
    
    var formField : Field!{
        didSet{
            self.segmentControl.removeAllSegments()
            self.titlelbl.text = self.field.title
            if let providedOptions = field.options as? [String]{
                self.options = providedOptions
                var count = 0
                for item in providedOptions{
                    self.segmentControl.insertSegment(withTitle: item, at: count, animated: false)
                    if let val = self.field.value as? String,val == item{
                        self.segmentControl.selectedSegmentIndex = count
                    }
                    count+=1
                }
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
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
}
