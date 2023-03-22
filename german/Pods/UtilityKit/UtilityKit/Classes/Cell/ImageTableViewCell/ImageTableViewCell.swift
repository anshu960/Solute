//
//  ImageTableViewCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 12/01/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

class ImageTableViewCell: BaseTVC {
    
    public static let nibName = "ImageTableViewCell"
    public static let identifier = "ImageTableViewCell"
    public static let nib = ImageTableViewCell.getNib()
    public static let cellHeight : CGFloat = 150
    class func getNib() -> UINib {
        return UINib(nibName: ImageTableViewCell.nibName, bundle: Bundle(for: ImageTableViewCell.self))
    }
    
//    let imageCollectionView = ImageCollectionView.viewFromNib()
//    var images : [Any] = []{
//        didSet{
//            self.imageCollectionView.images = self.images
//        }
//    }
//    
//    override func awakeFromNib() {
//        super.awakeFromNib()
//        // Initialization code
//        self.imageCollectionView.frame = self.contentView.bounds
//        self.contentView.addSubview(self.imageCollectionView)
//        self.imageCollectionView.images = self.images
//    }
//    
//    override func willMove(toSuperview newSuperview: UIView?) {
//        self.imageCollectionView.images = self.images
//    }
//    
//    override func setSelected(_ selected: Bool, animated: Bool) {
//        super.setSelected(selected, animated: animated)
//        
//        // Configure the view for the selected state
//    }
    
}
