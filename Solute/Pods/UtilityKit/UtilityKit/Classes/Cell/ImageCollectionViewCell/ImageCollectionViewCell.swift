//
//  ImageCollectionViewCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 10/04/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

class ImageCollectionViewCell: BaseTVC {

    public static let nibName = "ImageCollectionViewCell"
    public static let identifier = "ImageCollectionViewCell"
    public static let nib = ImageCollectionViewCell.getNib()
    @IBOutlet weak var utilityImageView: UtilityImageView!
    class func getNib() -> UINib {
        return UINib(nibName: ImageCollectionViewCell.nibName, bundle: Bundle(for: ImageCollectionViewCell.self))
    }
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

}
