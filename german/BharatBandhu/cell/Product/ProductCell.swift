//
//  ProductCellTableViewCell.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 18/03/23.
//

import UIKit
import SwiftUI
import SDWebImage
class ProductCell: UITableViewCell {
    
    public static let nibName = "ProductCell"
    public static let identifier = "ProductCell"
    public static let nib = ProductCell.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: ProductCell.nibName, bundle: Bundle(for: ProductCell.self))
    }
    
    @IBOutlet weak var productImage: UIImageView!
    
    @IBOutlet weak var nameLbl: UILabel!
    
    @IBOutlet weak var descriptionLbl: UILabel!
    
    @IBOutlet weak var mrpLbl: UILabel!
    
    @IBOutlet weak var discountbl: UILabel!
    
    
    @IBOutlet weak var priceLbl: UILabel!
    
    @IBOutlet weak var stepper: UIStepper!
    
    @IBOutlet weak var cartBtn: UIButton!
    
    @IBAction func onClickAddToCart(_ sender: UIButton) {
        ToastService.shared.toas(msg: "No Near By Stores found, we are expanding so please be patient        ")
    }
    
    init(style: UITableViewCell.CellStyle, reuseIdentifier: String) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    public func sutupCell(prd : Product) {
        nameLbl.text = prd.name
        descriptionLbl.text = prd.description
        if let mrp = prd.mrp{
            mrpLbl.text = "€\(String(describing: mrp))"
            mrpLbl.isHidden = false
        }else{
            mrpLbl.isHidden = true
        }
        if let discount = prd.discount,discount>0{
            discountbl.text = "€\(String(describing: discount))"
            discountbl.isHidden = false
        }else{
            discountbl.isHidden = true
        }
        if let finalPrice = prd.finalPrice,let price = prd.price,price != finalPrice{
            priceLbl.text = "€\(String(describing: finalPrice))"
            priceLbl.isHidden = false
        }else{
            priceLbl.isHidden = true
        }
        self.productImage.image = nil
        if let id = prd.id,let file = MediaFilesViewModel.shared.findByFeatureId(id: id),let urlString = file.fileURL,let url = URL(string: urlString){
            self.productImage.sd_setImage(with: url, placeholderImage: UIImage(named: "image"))

//            self.productImage.image = AsyncImage(url: URL(string: "https://hws.dev/paul.jpg"))
        }
    }
    
}
