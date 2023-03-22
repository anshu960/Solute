//
//  BarGraphTVC.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 31/08/19.
//

import UIKit
public struct BarData {
    let value : Int
    let title : String
    let color : UIColor
    public init(title:String,value:Int,color:UIColor) {
        self.value = value
        self.title = title
        self.color = color
    }
}

open class BarGraphTVC: BaseTVC {
    public static let nibName = "BarGraphTVC"
    public static let identifier = "BarGraphTVC"
    public static let nib = BarGraphTVC.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: BarGraphTVC.nibName, bundle: Bundle(for: BarGraphTVC.self))
    }
    
    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var barContainerView: UIView!
    @IBOutlet weak var xAxisView: UIView!
    
    @IBOutlet weak var titleLbl: UILabel!
    
    public var title = ""{
        didSet{
            self.titleLbl.text = self.title
        }
    }
   public var barData : [BarData] = []{
        didSet{
            DispatchQueue.main.async {
                  self.prepareBars()
            }
        }
    }
    
    override open func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        self.containerView.shadow()
    }
    
    
    
    func prepareBars(){
        for v in self.barContainerView.subviews{
            v.removeFromSuperview()
        }
        for v in self.xAxisView.subviews{
            v.removeFromSuperview()
        }
        let gap : CGFloat = 8
        let barWithMaxval = self.barData.max { (lhs, rhs) -> Bool in lhs.value < rhs.value}
        guard let maxBar = barWithMaxval else{return}
        let maxValue = Double(maxBar.value) * 1.2
        let maxHeight = self.barContainerView.bounds.height - 21
        let maxWidth = self.barContainerView.bounds.width
        let barWidth = (maxWidth / CGFloat(self.barData.count)) - (gap)
        
        var count : CGFloat = 0
        for item in self.barData{
            let singleBarContainer = UIView()
            let valLabel = UILabel()
            valLabel.textColor = UIColor.black
            valLabel.textAlignment = .center
            valLabel.adjustsFontSizeToFitWidth = true
            valLabel.text = item.value.description
            
            let barTitleLabel = UILabel()
            barTitleLabel.textColor = UIColor.black
            barTitleLabel.textAlignment = .center
            barTitleLabel.adjustsFontSizeToFitWidth = true
            barTitleLabel.text = item.title
            
            
            let percentage = maxValue > 0 ? (Double(item.value) * 100)/maxValue : 0
            let singleBarView = UIView()
            
            let x = (barWidth * count) + (gap * count)
            let barHeight = (maxHeight - 21) * CGFloat(percentage/100)
            let y = self.barContainerView.bounds.height - barHeight
            
            singleBarContainer.frame = CGRect(x: x, y: 0, width: barWidth, height: self.barContainerView.bounds.height)

            singleBarView.frame = CGRect(x: 0, y:maxHeight, width: barWidth, height: 0)

            valLabel.frame = CGRect(x: 0, y:singleBarView.frame.minY - 21, width: barWidth, height: 21)

            barTitleLabel.frame = CGRect(x: x, y:0, width: barWidth, height: xAxisView.bounds.height)

            singleBarContainer.backgroundColor = UIColor.clear

            singleBarView.backgroundColor = item.color

            singleBarContainer.addSubview(singleBarView)

            singleBarContainer.addSubview(valLabel)

            self.barContainerView.addSubview(singleBarContainer)

            self.xAxisView.addSubview(barTitleLabel)

            UIView.animate(withDuration: 2) {

            }

            UIView.animate(withDuration: 2, animations: {
                singleBarView.frame = CGRect(x: 0, y:y, width: barWidth, height: barHeight)
                valLabel.frame = CGRect(x: 0, y:singleBarView.frame.minY - 21, width: barWidth, height: 21)
            }) { (isCompleted) in

            }

            count += 1
            
        }
    }
    
    
    override open func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
}
