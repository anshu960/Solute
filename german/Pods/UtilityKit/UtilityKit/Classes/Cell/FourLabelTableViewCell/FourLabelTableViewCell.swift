//
//  FourLabelTableViewCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 13/01/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

class FourLabelTableViewCell: BaseTVC {

    public static let nibName = "FourLabelTableViewCell"
    public static let identifier = "FourLabelTableViewCell"
    public static let nib = FourLabelTableViewCell.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: FourLabelTableViewCell.nibName, bundle: Bundle(for: FourLabelTableViewCell.self))
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
