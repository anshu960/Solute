//
//  AddNewTVCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 30/04/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

class AddNewTVCell: BaseTVC {

    public static let nibName = "AddNewTVCell"
    public static let identifier = "AddNewTVCell"
    public static let nib = AddNewTVCell.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: AddNewTVCell.nibName, bundle: Bundle(for: AddNewTVCell.self))
    }
    
    
    
    @IBOutlet weak var addImageView: UIImageView!
    @IBOutlet weak var titleLable: UILabel!
    
    
    var id = ""
    var addImage : UIImage?{
        didSet{
            self.addImageView.image = self.addImage!
        }
    }
    
    var title : String{
        get{
            return self.titleLable.text ?? ""
        }
        set{
            self.titleLable.text = newValue
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
